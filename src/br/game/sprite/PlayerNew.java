package com.zetcode.sprite;

import com.zetcode.CommonsNew;
import spriteframework.sprite.Sprite;

import javax.swing.ImageIcon;
import java.awt.event.KeyEvent;

public class PlayerNew extends Sprite {

    private int width;

    public PlayerNew() {

        initPlayer();
    }

    private void initPlayer() {

        var playerImg = "src/images/player.png";
        var ii = new ImageIcon(playerImg);

        width = ii.getImage().getWidth(null);
        setImage(ii.getImage());

        int START_X = 270;
        setX(START_X);

        int START_Y = 280;
        setY(START_Y);
    }

    public void act() {

        x += getSpeedX();

        if (x <= 2) {

            x = 2;
        }

        if (x >= CommonsNew.BOARD_WIDTH - 2 * width) {

            x = CommonsNew.BOARD_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            setSpeedX(-2);
        }

        if (key == KeyEvent.VK_RIGHT) {

            setSpeedX(2);
        }
    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {

            setSpeedX(0);
        }

        if (key == KeyEvent.VK_RIGHT) {

            setSpeedX(0);
        }
    }
}