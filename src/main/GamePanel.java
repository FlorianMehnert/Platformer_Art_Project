package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements KeyListener {
	private final Game game;
	private final boolean isPlayer1;

	public GamePanel(Game game, boolean isPlayer1) {
		this.game = game;
		this.isPlayer1 = isPlayer1;
		setFocusable(true);
		addKeyListener(this);
		setPanelSize();
	}

	private void setPanelSize() {
		Dimension size = new Dimension(GAME_WIDTH/2, GAME_HEIGHT);
		setPreferredSize(size);
	}

	public void updateGame() {

	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		game.render(g, isPlayer1);
	}

	public Game getGame() {
		return game;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e.getKeyCode());


	}

	@Override
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e.getKeyCode());
	}
}