package spriteframework.sprite;

import spriteframework.sprite.command.Command;
import spriteframework.sprite.command.impl.MoveCommand;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;

    protected Direction moveDirection = Direction.BOTH;
    protected MoveDirection playerDirection = MoveDirection.TOP;

    private Command moveCommand;

    private String imagePath;

    /* TODO
        Quando estender a classe player injetar o moveCommand via construtor para desacoplar do framework detalhers do game
    */

    public Player() {
        initPlayer();
    }

    private void initPlayer() {

        var playerImg = getImage();

        moveCommand = new MoveCommand(this, Direction.HORIZONTAL);

        width = playerImg.getWidth(null);
        setImage(playerImg);

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }

    public void act() {
        moveCommand.execute();
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (getMoveDirection().equals(Direction.HORIZONTAL)) {
            moveHorizontal(key, 2);
        } else if (getMoveDirection().equals(Direction.VERTICAL)) {
            moveVertical(key, 2);
        } else if (getMoveDirection().equals(Direction.BOTH)) {
            moveHorizontal(key, 2);
            moveVertical(key, 2);
        }
    }

    private void moveVertical(int key, int speed) {
        if (key == KeyEvent.VK_DOWN) {
            setSpeedY(speed);
            setPlayerDirection(MoveDirection.BOTTOM);
        }
        if (key == KeyEvent.VK_UP) {
            setSpeedY(-speed);
            setPlayerDirection(MoveDirection.TOP);
        }
    }

    private void moveHorizontal(int key, int speed) {

        if (key == KeyEvent.VK_LEFT) {
            setSpeedX(-speed);
            setPlayerDirection(MoveDirection.LEFT);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setSpeedX(speed);
            setPlayerDirection(MoveDirection.RIGHT);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setSpeedX(0);
        } else if (key == KeyEvent.VK_RIGHT) {
            setSpeedX(0);
        } else if(key == KeyEvent.VK_UP){
            setSpeedY(0);
        } else if(key == KeyEvent.VK_DOWN){
            setSpeedY(0);
        }
    }

    public Image getImage() {
        return new ImageIcon("src/spaceinvaders/images/player.png").getImage();
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Direction getMoveDirection() {
        return moveDirection;
    }

    public void setMoveDirection(Direction moveDirection) {
        this.moveDirection = moveDirection;
    }

    public MoveDirection getPlayerDirection() {
        return playerDirection;
    }

    public void setPlayerDirection(MoveDirection playerDirection) {
        this.playerDirection = playerDirection;
    }
}
