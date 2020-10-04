package freezemonster.sprites;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.MoveDirection;
import spriteframework.utils.UtilCommons;

import javax.swing.*;

public class Goop extends BadSprite {

    private boolean destroyed;
    private MoveDirection goopDirection;

    // TODO PASSAR PARA O BADSPRITE
    private void initGoop(int x, int y) {
        setDestroyed(true);

        this.x = x;
        this.y = y;

        String bombImg = "src/freezemonster/images/gosma.png";
        ImageIcon ii = new ImageIcon(bombImg);
        setImage(UtilCommons.getScaledImage(ii.getImage(), 17, 17));
    }

    public Goop(int x, int y) {
        initGoop(x, y);
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public MoveDirection getGoopDirection() {
        return goopDirection;
    }

    public void setGoopDirection(MoveDirection goopDirection) {
        this.goopDirection = goopDirection;
    }
}
