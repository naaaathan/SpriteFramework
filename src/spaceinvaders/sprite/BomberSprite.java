package spaceinvaders.sprite;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import javax.swing.*;
import java.util.LinkedList;

public class BomberSprite extends BadnessBoxSprite {
    private Bomb bomb;

    public BomberSprite(int x, int y) {
        initBomber(x, y);
    }

    private void initBomber(int x, int y) {

        this.x = x;
        this.y = y;

        bomb = new Bomb(x, y);

        String alienImg = "src/spaceinvaders/images/alien.png";
        ImageIcon ii = new ImageIcon(alienImg);

        setImage(ii.getImage());
    }

    public Bomb getBomb() {
        return bomb;
    }

    @Override
    public void act() {

    }

    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> aBomb = new LinkedList<BadSprite>();
        aBomb.add(bomb);
        return aBomb;
    }
}
