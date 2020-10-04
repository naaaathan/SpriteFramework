package freezemonster.sprites;

public class BlueMonster extends MonsterSprite {

    public BlueMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    public String getFreezeImagePath() {
        return "src/freezemonster/images/monster1bg.png";
    }

    @Override
    public String getImagePath() {
        return "src/freezemonster/images/monster1.png";
    }
}
