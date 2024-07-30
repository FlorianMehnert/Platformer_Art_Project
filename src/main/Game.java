package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWJoystickCallback;

import audio.AudioPlayer;
import gamestates.Credits;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;

import static utilz.Constants.*;


public class Game implements Runnable {

    private GamePanel gamePanel1;
    private GamePanel gamePanel2;
    private Thread gameThread;
    private long windowHandle;

    private Playing playing;
    private Menu menu;
    private Credits credits;
    private AudioPlayer audioPlayer;
    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    //public final static int TILES_IN_WIDTH = 15;//30;//26;
    //public final static int TILES_IN_HEIGHT = 16;//16;//14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = 1920;//TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = 1080;//TILES_SIZE * TILES_IN_HEIGHT;

    private final boolean SHOW_FPS_UPS = false;
    private static GLFWJoystickCallback joystickCallback;
    public GameWindow gameWindow;

    public Game() {

        System.out.println("size: " + GAME_WIDTH + " : " + GAME_HEIGHT);
        initClasses();
        initControllerInput();
        gamePanel1 = new GamePanel(this, true);
        gamePanel2 = new GamePanel(this, false);
        new GameWindow(gamePanel1, gamePanel2);
        gamePanel1.requestFocusInWindow();
        gamePanel2.requestFocusInWindow();
        startGameLoop();
    }

    private void initClasses() {
        audioPlayer = new AudioPlayer();
        menu = new Menu(this);
        playing = new Playing(this);
        credits = new Credits(this);


    }

    private void initControllerInput() {
        // joystick listener
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }

        // Set the joystick callback
        joystickCallback = new GLFWJoystickCallback() {
            @Override
            public void invoke(int jid, int event) {
                if (event == GLFW.GLFW_CONNECTED) {
                    System.out.println("Controller connected: " + jid);
                } else if (event == GLFW.GLFW_DISCONNECTED) {
                    System.out.println("Controller disconnected: " + jid);
                }
            }
        };
        GLFW.glfwSetJoystickCallback(joystickCallback);
    }

    public void keyPressed(int key) {
        switch (Gamestate.state) {
            case MENU -> menu.keyPressed(key);
            case PLAYING -> playing.keyPressed(key);
        }
    }

    public void keyReleased(int key) {
        switch (Gamestate.state) {
            case MENU -> menu.keyReleased(key);
            case PLAYING -> playing.keyReleased(key);
        }
    }

    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update() {
        // joystick listener
        GLFW.glfwPollEvents();
        switch (Gamestate.state) {
            case MENU -> menu.update();
            case PLAYING -> playing.update();
            case CREDITS -> credits.update();
            case QUIT -> System.exit(0);
        }
    }


    public void render(Graphics g, boolean isPlayer1) {
        switch (Gamestate.state) {
            case MENU -> {
                menu.draw(g, isPlayer1);
            }
            case PLAYING -> {
                playing.draw(g, isPlayer1);
            }
            case CREDITS -> {
                credits.draw(g, isPlayer1);
            }
            default -> System.out.println("Unknown state: " + Gamestate.state);
        }
    }


    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 60;  // FPS_SET assumed as 60
        double timePerUpdate = 1000000000.0 / 60; // UPS_SET assumed as 60

        long previousTime = System.nanoTime();
        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel1.repaint();
                gamePanel2.repaint();
                deltaF--;
            }

            try {
                Thread.sleep(1); // Small sleep to prevent 100% CPU usage
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void cleanup() {
        GLFW.glfwDestroyWindow(windowHandle);
        GLFW.glfwTerminate();
    }

    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING) {
            playing.getPlayer1().resetDirBooleans();
            playing.getPlayer2().resetDirBooleans();

        }
    }

    public Menu getMenu() {
        return menu;
    }

    public Playing getPlaying() {
        return playing;
    }

    public Credits getCredits() {
        return credits;
    }


    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

}