package entities;

import java.util.HashMap;
import java.util.Map;

public class InputHandler {
    private Map<String, Command> commands;

    public InputHandler(MovementComponent movementComponent) {
        commands = new HashMap<>();
        commands.put("JUMP", new JumpCommand(movementComponent));
        commands.put("LEFT", new MoveLeftCommand(movementComponent));
        commands.put("RIGHT", new MoveRightCommand(movementComponent));
        commands.put("STOP", new StopMovingCommand(movementComponent));
    }

    public void handleInput(String input) {
        Command command = commands.get(input);
        if (command != null) {
            command.execute();
        }
    }
}