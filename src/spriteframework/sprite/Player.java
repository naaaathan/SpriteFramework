package spriteframework.sprite;

import java.awt.event.KeyEvent;

public class Player extends Sprite {


    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            setSpeedX(-2);
        }

        if (key == KeyEvent.VK_RIGHT) {
            setSpeedX(2);
        }

        if (key == KeyEvent.VK_UP) {
            setSpeedY(-2);
        }

        if (key == KeyEvent.VK_DOWN) {
            setSpeedY(2);

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

        if (key == KeyEvent.VK_UP) {
            setSpeedY(0);
        }

        if (key == KeyEvent.VK_DOWN) {
            setSpeedY(0);

        }
    }


}
