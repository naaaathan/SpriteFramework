package freezemonster.sprites;

import spriteframework.utils.UtilCommons;

import javax.swing.*;

public class CyanMonster extends MonsterSprite {

    public CyanMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    void initMonster(int c1, int c2) {
        this.x = c1;
        this.y = c2;

        setGoop(new Goop(c1,c2));

        String cyanMonsterImg = "src/freezemonster/images/monster9.png";
        ImageIcon ii = new ImageIcon(cyanMonsterImg);

        setImage(UtilCommons.getScaledImage(ii.getImage(), 30, 30));
    }
}
