package spriteframework.sprite.impl;

import spriteframework.sprite.Command;
import spriteframework.sprite.Commons;
import spriteframework.sprite.Direction;
import spriteframework.sprite.Player;

public class MoveCommand implements Command {
    private final Player player;

    private Direction direction;

    public MoveCommand(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    @Override
    public void execute() {

        this.player.x += player.getSpeedX();

        if (player.getMoveDirection().equals(Direction.HORIZONTAL)) {
            moveHorizontalDirection();
        }
        if (player.getMoveDirection().equals(Direction.VERTICAL)) {
            moveVerticalDirection();
        }

    }

    private void moveVerticalDirection() {

        if (this.player.y <= 2) {
            this.player.y = 2;
        }
        if (this.player.y >= Commons.BOARD_HEIGHT - 2 * player.getWidth()) {
            this.player.y = Commons.BOARD_HEIGHT - 2 * this.player.getWidth();
        }
    }

    private void moveHorizontalDirection() {

        if (this.player.x <= 2) {
            this.player.x = 2;
        }
        if (this.player.x >= Commons.BOARD_WIDTH - 2 * player.getWidth()) {
            this.player.x = Commons.BOARD_WIDTH - 2 * this.player.getWidth();
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
