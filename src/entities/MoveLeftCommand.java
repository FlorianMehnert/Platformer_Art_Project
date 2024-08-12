package entities;

public class MoveLeftCommand implements Command {
    private MovementComponent movementComponent;

    public MoveLeftCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        movementComponent.moveLeft();
    }
}