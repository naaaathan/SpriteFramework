package com.zetcode.sprite;

import spriteframework.sprite.BadSprite;

import javax.swing.*;

public class Bomb extends BadSprite {

    private boolean destroyed;

    public Bomb(int x, int y) {
        initBomb(x, y);
    }

    private void initBomb(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        String bombImg = "src/images/bomb.png";
        ImageIcon ii = new ImageIcon(bombImg);
        setImage(ii.getImage());
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
    }
}
