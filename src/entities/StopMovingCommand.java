package entities;

public class StopMovingCommand implements Command {
    private MovementComponent movementComponent;

    public StopMovingCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        movementComponent.stopMoving();
    }
}