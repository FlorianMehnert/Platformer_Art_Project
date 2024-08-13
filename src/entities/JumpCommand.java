package entities;

public class JumpCommand implements Command {
    private final MovementComponent movementComponent;

    public JumpCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        boolean jumping = movementComponent.getAirSpeed() < 0;
        if (!movementComponent.isInAir() ||
                (!jumping && (movementComponent.getPlaying().getGameTimeInSeconds() - movementComponent.getStartTimeInAir() < MovementComponent.COYOTE_TIME) ||
                        movementComponent.getJumpsDone() < MovementComponent.MAX_ALLOWED_JUMPS)) {
            movementComponent.setInAir(true);
            movementComponent.setStartTimeInAir();
            if (movementComponent.getJumpsDone() == 0) {
                movementComponent.setAirSpeed(MovementComponent.jumpSpeed);
            } else {
                movementComponent.setAirSpeed(2 * MovementComponent.jumpSpeed - movementComponent.getAirSpeed());
            }
            movementComponent.setJumpsDone(movementComponent.getJumpsDone() + 1);
        }
    }
}