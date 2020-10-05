package spriteframework.sprite;

import java.awt.*;

public abstract class Shot extends Sprite {

    private MoveDirection shotDirection;

    public Shot() {
    }

    public Shot(int x, int y) {
        initShot(x, y);
    }

    public abstract Image getImage();

    private void initShot(int x, int y) {

        var shotImg = getImage();
        setImage(shotImg);

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }

    public MoveDirection getShotDirection() {
        return shotDirection;
    }

    public void setShotDirection(MoveDirection shotDirection) {
        this.shotDirection = shotDirection;
    }
}
