package spaceinvaders;

import spaceinvaders.sprite.Bomb;
import spaceinvaders.sprite.BomberSprite;
import spaceinvaders.sprite.ShotAlien;
import spriteframework.board.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Shot;
import spriteframework.utils.Commons;
import spriteframework.sprite.Player;
import spriteframework.sprite.command.impl.ShotCommand;
import spriteframework.utils.UtilCommons;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SpaceInvadersBoard extends AbstractBoard {

    //define sprites
    private List<BadSprite> aliens;
    private Shot shot;

    // define global control vars
    private int direction = -1;
    private int deaths = 0;


    private String explImg = "spaceinvaders/spaceinvaders/images/explosion.png";


    protected void createBadSprites() {  // create sprites
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                BomberSprite alien = new BomberSprite(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i);
                badSprites.add(alien);
            }
        }
    }

    protected void createOtherSprites() {
        shot = new ShotAlien();
    }

    private void drawShot(Graphics g) {

        if (shot.isVisible()) {

            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    // Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }

    protected void processOtherSprites(Player player, KeyEvent e) {
        /* TODO improve this */

        int x = player.getX();
        int y = player.getY();
        int key = e.getKeyCode();

        Shot shotImpl = new ShotAlien(x, y);
        ShotCommand shotCommand = new ShotCommand(shot, shotImpl, key);

        if (inGame) {
            shotCommand.execute();
        }

        shot = shotCommand.getShot();
    }


    protected void update() {
        if (deaths == Commons.NUMBER_OF_ALIENS_TO_DESTROY) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        for (Player player : players) {
            player.act();
        }

        // shot
        if (shot.isVisible()) {
            for (BadSprite alien : badSprites) {
                if (alien.isVisible() && shot.isVisible()) {
                    if (UtilCommons.checkContact(shot, alien)) {
                        ImageIcon ii = new ImageIcon(explImg);
                        alien.setImage(ii.getImage());
                        alien.setDying(true);
                        deaths++;
                        shot.die();
                    }
                }
            }

            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }

        // aliens

        for (BadSprite alien : badSprites) {

            int x = alien.getX();

            if (x >= Commons.BOARD_WIDTH - Commons.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<BadSprite> i1 = badSprites.iterator();

                while (i1.hasNext()) {
                    BadSprite a2 = i1.next();
                    a2.setY(a2.getY() + Commons.GO_DOWN);
                }
            }

            if (x <= Commons.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<BadSprite> i2 = badSprites.iterator();

                while (i2.hasNext()) {

                    BadSprite a = i2.next();
                    a.setY(a.getY() + Commons.GO_DOWN);
                }
            }
        }

        Iterator<BadSprite> it = badSprites.iterator();

        while (it.hasNext()) {

            BadSprite alien = it.next();

            if (alien.isVisible()) {

                int y = alien.getY();

                if (y > Commons.GROUND - Commons.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alien.moveX(direction);
            }
        }

        // bombs

        updateOtherSprites();
    }


    protected void updateOtherSprites() {
        Random generator = new Random();

        for (BadSprite alien : badSprites) {

            int shot = generator.nextInt(15);
            Bomb bomb = ((BomberSprite) alien).getBomb();

            if (shot == Commons.CHANCE && alien.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(alien.getX());
                bomb.setY(alien.getY());
            }
            if (players.get(0).isVisible() && !bomb.isDestroyed()) {
                if (UtilCommons.checkContact(bomb, players.get(0))) {
                    ImageIcon ii = new ImageIcon(explImg);
                    players.get(0).setImage(ii.getImage());
                    players.get(0).setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);
                if (bomb.getY() >= Commons.GROUND - Commons.BOMB_HEIGHT) {
                    bomb.setDestroyed(true);
                }
            }
        }
    }
}
