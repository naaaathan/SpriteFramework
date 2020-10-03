package freezemonster.sprites;

import spriteframework.sprite.Player;
import spriteframework.utils.UtilCommons;

import javax.swing.*;
import java.awt.*;

public class Woody extends Player {

    public Woody() {
        super();
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Image getImagePath() {
        return UtilCommons.getScaledImage(new ImageIcon("src/freezemonster/images/woody.png").getImage(), 50, 115);
    }
}
