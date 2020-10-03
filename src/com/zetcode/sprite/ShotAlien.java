package com.zetcode.sprite;

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
    public Image getImagePath() {
        return new ImageIcon("src/images/shot.png").getImage();
    }
}
