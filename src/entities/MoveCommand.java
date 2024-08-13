package entities;

import utilz.Constants;

public class MoveCommand implements Command {
    private final MovementComponent movementComponent;
    private boolean left;
    private boolean right;

    public MoveCommand(MovementComponent movementComponent, boolean left, boolean right) {
        this.movementComponent = movementComponent;
        this.left = left;
        this.right = right;
    }

    @Override
    public void execute() {
        if (left && !right) {
            movementComponent.setXSpeed(-movementComponent.getWalkSpeed());
            movementComponent.setFlipX(movementComponent.getWidth());
            movementComponent.setFlipW(-1);
        } else if (right && !left) {
            movementComponent.setXSpeed(movementComponent.getWalkSpeed());
            movementComponent.setFlipX(0);
            movementComponent.setFlipW(1);
        } else {
            if (movementComponent.getDashState().equals(Constants.TetrisTileConstants.DashState.NOTHING)) {
                movementComponent.setXSpeed(0);
            }
        }
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }
}