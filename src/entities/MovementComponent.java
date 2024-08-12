package entities;

import gamestates.Playing;
import main.Game;
import utilz.Constants;

import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsEntityOnFloor;

public class MovementComponent implements Component {
    private float xSpeed;
    private float ySpeed;
    private boolean inAir;
    private float airSpeed;
    private int jumpsDone;
    private float hitboxX, hitboxY, hitboxWidth, hitboxHeight;
    private int flipX, flipW;
    private float walkSpeed;
    private Constants.TetrisTileConstants.DashState dashState;
    private long dashStartTime;
    private boolean powerAttackActive;
    private float startTimeInAir;
    private int[][] lvlData;
    private Playing playing;

    private static final float GRAVITY = 0.04f * Game.SCALE;
    private static final int MAX_ALLOWED_JUMPS = 2;
    private static final float COYOTE_TIME = 0.2f;
    private static final float jumpSpeed = -2.25f * Game.SCALE;

    public MovementComponent(Playing playing, int[][] lvlData) {
        this.playing = playing;
        this.lvlData = lvlData;
        // Initialize other variables
    }

    @Override
    public void update() {
        if (playing.getLoading()) {
            return;
        }

        boolean startInAir = inAir;

        if (!powerAttackActive) {
            updatePos();
        }

        if (!startInAir && inAir) {
            startTimeInAir = playing.getGameTimeInSeconds();
        }
    }

    public void jump() {
        boolean jumping = airSpeed < 0;
        if (!inAir || (!jumping && (playing.getGameTimeInSeconds() - startTimeInAir < COYOTE_TIME) || jumpsDone < MAX_ALLOWED_JUMPS)) {
            inAir = true;
            startTimeInAir = playing.getGameTimeInSeconds();
            if (jumpsDone == 0) {
                airSpeed = jumpSpeed;
            } else {
                airSpeed = 2 * jumpSpeed - airSpeed;
            }
            jumpsDone++;
        }
    }

    public void moveLeft() {
        xSpeed = -walkSpeed;
        flipX = (int) hitboxWidth;
        flipW = -1;
    }

    public void moveRight() {
        xSpeed = walkSpeed;
        flipX = 0;
        flipW = 1;
    }

    public void stopMoving() {
        if (dashState.equals(Constants.TetrisTileConstants.DashState.NOTHING)) {
            xSpeed = 0;
        }
    }

    private void updatePos() {
        if (!inAir) {
            jumpsDone = 0;
            dashState = Constants.TetrisTileConstants.DashState.NOTHING;
            Rectangle2D.Float hitBox = new Rectangle2D.Float();
            hitBox.x = hitboxX;
            hitBox.y = hitboxY;
            hitBox.width = hitboxWidth;
            hitBox.height = hitboxHeight;
            if (!IsEntityOnFloor(hitBox, lvlData))
                inAir = true;
        }

        if (inAir) {
            if (CanMoveHere(hitboxX, hitboxY + airSpeed, hitboxWidth, hitboxHeight, lvlData)) {
                hitboxY += airSpeed;
                airSpeed += GRAVITY;
                updateXPos(xSpeed, lvlData);
            } else {
                float fallSpeedAfterCollision = 0.5f * Game.SCALE;
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed, lvlData);
            }
        } else {
            updateXPos(xSpeed, lvlData);
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private void updateXPos(float xSpeed, int[][] lvlData) {
        // Implement x position update logic
    }
}
