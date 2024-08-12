package entities;

public class JumpCommand implements Command {
    private MovementComponent movementComponent;

    public JumpCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        movementComponent.jump();
    }
}
