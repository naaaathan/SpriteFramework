package freezemonster.sprites;

public class CyanMonster extends MonsterSprite {

    public CyanMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    public String getFreezeImagePath() {
        return "src/freezemonster/images/monster9bg.png";
    }

    @Override
    public String getImagePath() {
        return "src/freezemonster/images/monster9.png";
    }
}
