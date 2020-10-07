package freezemonster.sprites;

import spriteframework.utils.Direction;
import spriteframework.sprite.Player;
import spriteframework.utils.UtilCommons;

import javax.swing.*;
import java.awt.*;

public class Woody extends Player {

    public Woody() {
        super(Direction.BOTH);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Image getImage() {
        return UtilCommons.getScaledImage(new ImageIcon("src/freezemonster/images/woody.png").getImage(), 25, 55);
    }
}
