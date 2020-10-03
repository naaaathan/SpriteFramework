package freezemonster;


import freezemonster.sprites.*;
import spriteframework.board.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Shot;
import spriteframework.sprite.command.impl.ShotCommand;
import spriteframework.utils.Commons;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class FreezeMonstersBoard extends AbstractBoard {

    private Shot shot;
    private LinkedList<MonsterSprite> monsterSprites;

    @Override
    protected void createBadSprites() {
        // TODO LIMITAR PARA GERAR O MONSTRO DENTRO DO FRAME
        monsterSprites = new LinkedList(Arrays.asList(
                new BlueMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new CyanMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new PurpleMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new RedMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT))
        ));
        badSprites.addAll(monsterSprites);
    }

    @Override
    protected void gameInit() {
        addPlayer(new Woody());
        numberPlayers = 1;
        badSprites = new LinkedList<BadSprite>();
        createBadSprites();
        createOtherSprites();
        shot = new FreezeRay();
    }

    @Override
    protected void createOtherSprites() {
        shot = new FreezeRay();
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();
        int key = e.getKeyCode();

        Shot shotImpl = new FreezeRay(x, y);
        ShotCommand shotCommand = new ShotCommand(shot, shotImpl, key);

        if (inGame) {
            shotCommand.execute();
        }

        shot = shotCommand.getShot();
    }

    @Override
    protected void update() {
        // TODO PERFORM UPDATE (MUITA COISA)
        this.players.forEach(Player::act);

        if (shot.isVisible()) {
            int y = shot.getY();
            y -= 4;

            if (y < 0) {
                shot.die();
            } else {
                shot.setY(y);
            }
        }
    }

}
