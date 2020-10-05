package spaceinvaders.sprite;

import spriteframework.sprite.Shot;

import javax.swing.*;
import java.awt.*;

public class ShotAlien extends Shot {

    public ShotAlien() {
    }

    public ShotAlien(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getImage() {
        return new ImageIcon("src/spaceinvaders/images/shot.png").getImage();
    }
}
