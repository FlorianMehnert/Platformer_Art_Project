package entities;

public class PowerAttackCommand implements Command {
    private final MovementComponent movementComponent;

    public PowerAttackCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        if ((!movementComponent.isLeft() && !movementComponent.isRight()) || (movementComponent.isLeft() && movementComponent.isRight())) {
            if (movementComponent.getFlipW() == -1)
                movementComponent.setXSpeed(-movementComponent.getWalkSpeed());
            else
                movementComponent.setXSpeed(movementComponent.getWalkSpeed());
        }
        movementComponent.setXSpeed(movementComponent.getXSpeed() * 3);
    }
}