package entities;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private final Map<String, Command> commands;
    private MovementComponent movementComponent;

    public InputHandler(MovementComponent movementComponent) {
        this.movementComponent = movementComponent;
        commands = new HashMap<>();
        commands.put("JUMP", new JumpCommand(movementComponent));
        commands.put("MOVE", new MoveCommand(movementComponent, false, false));
        commands.put("DASH", new DashCommand(movementComponent));
        commands.put("POWER_ATTACK", new PowerAttackCommand(movementComponent));
    }

    public void handleInput(String input, boolean left, boolean right) {
        Command command = commands.get(input);
        if (command != null) {
            if (command instanceof MoveCommand) {
                ((MoveCommand) command).setLeft(left);
                ((MoveCommand) command).setRight(right);
            }
            command.execute();
        }
    }
}