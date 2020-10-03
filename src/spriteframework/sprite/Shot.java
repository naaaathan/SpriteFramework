package spriteframework.sprite;

import java.awt.*;

public abstract class Shot extends Sprite {

    public Shot() {
    }

    public Shot(int x, int y) {
        initShot(x, y);
    }

    public abstract Image getImagePath();

    private void initShot(int x, int y) {

        var shotImg = getImagePath();
        setImage(shotImg);

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}
