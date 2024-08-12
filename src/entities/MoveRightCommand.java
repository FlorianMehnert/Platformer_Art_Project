package entities;

public class MoveRightCommand implements Command {
    private MovementComponent movementComponent;

    public MoveRightCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        movementComponent.moveRight();
    }
}
