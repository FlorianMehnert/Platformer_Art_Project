package entities;

import gamestates.Playing;
import main.Game;
import utilz.Constants;

import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.CanMoveHere;
import static utilz.HelpMethods.IsEntityOnFloor;

public class MovementComponent implements Component {
    private float xSpeed, ySpeed;
    private float airSpeed;
    private boolean inAir;
    private int jumpsDone;
    private float hitboxX, hitboxY, hitboxWidth, hitboxHeight;
    private int flipX, flipW;
    private float walkSpeed;
    private Constants.TetrisTileConstants.DashState dashState;
    private long dashStartTime;
    private boolean powerAttackActive;
    private int powerAttackTick;
    private float startTimeInAir;
    private int[][] lvlData;
    private final Playing playing;
    private boolean left;
    private boolean right;

    private static final float GRAVITY = 0.04f * Game.SCALE;
    protected static final int MAX_ALLOWED_JUMPS = 2;
    protected static final float COYOTE_TIME = 0.2f;
    protected static final float jumpSpeed = -2.25f * Game.SCALE;

    public MovementComponent(Playing playing, int[][] lvlData) {
        this.playing = playing;
        this.lvlData = lvlData;
    }

    @Override
    public void update() {
        if (playing.getLoading()) {
            return;
        }

        boolean startInAir = inAir;

        if (powerAttackActive) {
            powerAttackTick++;
            if (powerAttackTick >= 35) {
                powerAttackTick = 0;
                powerAttackActive = false;
            }
        }

        updatePos();

        if (!startInAir && inAir) {
            startTimeInAir = playing.getGameTimeInSeconds();
        }
    }

    private void updatePos() {
        if (!inAir) {
            jumpsDone = 0;
            dashState = Constants.TetrisTileConstants.DashState.NOTHING;
            xSpeed = 0;
            Rectangle2D.Float hitbox = new Rectangle2D.Float();
            hitbox.x = hitboxX;
            hitbox.y = hitboxY;
            hitbox.width = hitboxWidth;
            hitbox.height = hitboxHeight;

            if (!IsEntityOnFloor(hitbox, lvlData))
                inAir = true;
        }

        if (inAir && !powerAttackActive) {
            if (CanMoveHere(hitboxX, hitboxY + airSpeed, hitboxWidth, hitboxHeight, lvlData)) {
                hitboxY += airSpeed;
                airSpeed += GRAVITY * (isFastFall() ? 5 : 1);
                updateXPos(xSpeed);
            } else {
                float fallSpeedAfterCollision = 0.5f * Game.SCALE;
                if (airSpeed > 0)
                    resetInAir();
                else
                    airSpeed = fallSpeedAfterCollision;
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
            if (powerAttackActive && !CanMoveHere(hitboxX + xSpeed, hitboxY, hitboxWidth, hitboxHeight, lvlData)) {
                powerAttackActive = false;
                powerAttackTick = 0;
            }
        }
    }

    private void updateXPos(float xSpeed) {
        if (CanMoveHere(hitboxX + xSpeed, hitboxY, hitboxWidth, hitboxHeight, lvlData)) {
            hitboxX += xSpeed;
        }
    }

    private void resetInAir() {
        inAir = false;
        airSpeed = 0;
    }

    private boolean isFastFall() {
        // TODO: get key pressed down
        return false;
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

    public float getAirSpeed() {
        return airSpeed;
    }

    public Playing getPlaying() {
        return playing;
    }

    public float getStartTimeInAir() {
        return startTimeInAir;
    }


    private void updateXPos(float xSpeed, int[][] lvlData) {
        // Implement x position update logic
    }

    public int getJumpsDone() {
        return jumpsDone;
    }

    public void setInAir(boolean is) {
        inAir = is;
    }

    public boolean isInAir(){
        return inAir;
    }

    public void setStartTimeInAir(){
        startTimeInAir = playing.getGameTimeInSeconds();
    }

    public void setAirSpeed(float speed) {
        airSpeed = speed;
    }

    public void setJumpsDone(int i) {
        jumpsDone = i;
    }

    public float getWalkSpeed() {
        return walkSpeed;
    }

    public float getWidth() {
        return hitboxWidth;
    }

    public void setFlipX(float flipX) {
        this.flipX = (int) flipX;
    }

    public void setXSpeed(float xSpeed) {
        this.xSpeed = xSpeed;
    }

    public float getXSpeed() {
        return xSpeed;
    }

    public void setFlipW(int flipW) {
        this.flipW = flipW;
    }

    public int getFlipW() {
        return flipW;
    }

    public Constants.TetrisTileConstants.DashState getDashState() {
        return dashState;
    }

    public long getDashStartTime() {
        return dashStartTime;
    }

    public void setDashStartTime(long startTime) {
        dashStartTime = startTime;
    }

    public void setDashState(Constants.TetrisTileConstants.DashState dashState) {
        this.dashState = dashState;
    }

    public void setLeft(){
        left = true;
    }

    public void setRight(){
        right = true;
    }

    public boolean isLeft(){
        return left;
    }

    public boolean isRight(){
        return right;
    }

    public void setLvlData(int[][] lvlData){
        this.lvlData = lvlData;
    }
}
