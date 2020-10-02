package spriteframework.sprite.impl;

import spriteframework.sprite.Command;
import spriteframework.sprite.Direction;
import spriteframework.sprite.Player;

public class MoveCommand implements Command {
    private Player player;
    private Direction direction;

    public MoveCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {
        this.player.move(direction);
    }
}
