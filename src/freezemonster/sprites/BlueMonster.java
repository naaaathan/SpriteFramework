package freezemonster.sprites;

import javax.swing.*;

public class BlueMonster extends MonsterSprite {

    public BlueMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    void initMonster(int c1, int c2) {

        this.x = c1;
        this.y = c2;

        setGoop(new Goop(c1,c2));

        String blueMonsterImg = "images/monster1.png";
        ImageIcon ii = new ImageIcon(blueMonsterImg);

        setImage(ii.getImage());

    }
}
