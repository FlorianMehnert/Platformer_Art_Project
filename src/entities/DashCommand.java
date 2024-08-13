package entities;

import utilz.Constants;

public class DashCommand implements Command {
    private final MovementComponent movementComponent;

    public DashCommand(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
    }

    @Override
    public void execute() {
        switch (movementComponent.getDashState()) {
            case NOTHING, ACTIVATE1, RELEASE1 -> {
                // Normal movement handled by MoveCommand
            }
            case ACTIVATE2 -> {
                movementComponent.setDashStartTime(System.currentTimeMillis());
                movementComponent.setDashState(Constants.TetrisTileConstants.DashState.DASHING);
            }
            case DASHING -> {
                movementComponent.setXSpeed(movementComponent.getXSpeed() * 5);
                if ((System.currentTimeMillis() - movementComponent.getDashStartTime()) >= 200) {
                    movementComponent.setDashState(Constants.TetrisTileConstants.DashState.NOTHING);
                }
            }
        }
    }
}