package freezemonster;


import freezemonster.sprites.BlueMonster;
import spriteframework.board.AbstractBoard;
import spriteframework.utils.Commons;
import spriteframework.sprite.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class FreezeMonstersBoard extends AbstractBoard {


    @Override
    protected void createBadSprites() {

        BlueMonster monsterSprite = new BlueMonster(Commons.ALIEN_INIT_X + 18, Commons.ALIEN_INIT_Y + 18);
        badSprites.add(monsterSprite);


    }

    @Override
    public void initBoard() {

        setFocusable(true);
        d = new Dimension(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);
        setForeground(Color.green);
        setBackground(Color.green);

        timer = new Timer(Commons.DELAY, new GameCycle());
        timer.start();


        gameInit();

    }

    @Override
    protected void createOtherSprites() {

    }

    @Override
    protected void drawOtherSprites(Graphics g) {

    }

    @Override
    protected void update() {

    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {

    }
}
