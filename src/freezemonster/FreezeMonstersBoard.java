package freezemonster;


import freezemonster.sprites.BlueMonster;
import freezemonster.sprites.MonsterSprite;
import spriteframework.board.AbstractBoard;
import spriteframework.utils.Commons;
import spriteframework.sprite.Player;

import java.awt.*;
import java.awt.event.KeyEvent;

public class FreezeMonstersBoard extends AbstractBoard {


    @Override
    protected void createBadSprites() {

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {
                MonsterSprite monsterSprite = new BlueMonster(Commons.ALIEN_INIT_X + 18 * j,
                        Commons.ALIEN_INIT_Y + 18 * i);
                badSprites.add(monsterSprite);
            }
        }

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
