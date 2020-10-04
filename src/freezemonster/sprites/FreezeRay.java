package freezemonster.sprites;

import spriteframework.sprite.Shot;
import spriteframework.utils.UtilCommons;

import javax.swing.*;
import java.awt.*;

public class FreezeRay extends Shot {

    public FreezeRay() {
    }

    public FreezeRay(int x, int y) {
        super(x, y);
    }

    @Override
    public Image getImagePath() {
        return UtilCommons.getScaledImage(new ImageIcon("src/freezemonster/images/ray.png").getImage(), 30, 30);
    }
}
