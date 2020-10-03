package spriteframework.sprite;

import spriteframework.sprite.command.Command;
import spriteframework.sprite.command.impl.MoveCommand;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player extends Sprite {

    private int width;

    protected Direction moveDirection = Direction.BOTH;

    private Command moveCommand;

    private String imagePath;

    /* TODO
        Quando estender a classe player injetar o moveCommand via construtor para desacoplar do framework detalhers do game
    */

    public Player() {
        initPlayer();
    }

    private void initPlayer() {

        var playerImg = getImagePath();
        var ii = new ImageIcon(playerImg);

        moveCommand = new MoveCommand(this, Direction.HORIZONTAL);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

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
        }
        if (key == KeyEvent.VK_UP) {
            setSpeedY(-speed);
        }
    }

    private void moveHorizontal(int key, int speed) {

        if (key == KeyEvent.VK_LEFT) {
            setSpeedX(-speed);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setSpeedX(speed);
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setSpeedX(0);
            moveHorizontal(key,0);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setSpeedX(0);
            moveHorizontal(key,0);
        }
        if(key == KeyEvent.VK_UP){
            setSpeedY(0);
            moveVertical(key,0);
        }
        if(key == KeyEvent.VK_DOWN){
            setSpeedY(0);
            moveVertical(key,0);
        }
    }

    /* TODO REMOVER ESSE PATH HARDCODED QUANDO TIVERMOS PELO MENOS UM PLAYER IMPLEMENTADO EM CADA JOGO */

    public String getImagePath() {
        return "src/images/player.png";
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
}
