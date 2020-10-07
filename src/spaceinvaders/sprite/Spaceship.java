package spaceinvaders.sprite;

import spriteframework.utils.Direction;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;

public class Spaceship extends Player {
    public Spaceship() {
        super(Direction.HORIZONTAL);
    }

    @Override
    public void act() {
        super.act();
    }

    @Override
    public Image getImage() {
        return new ImageIcon("src/spaceinvaders/images/player.png").getImage();
    }
}
