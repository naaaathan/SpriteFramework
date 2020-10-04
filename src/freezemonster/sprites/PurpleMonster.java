package freezemonster.sprites;

public class PurpleMonster extends MonsterSprite {

    public PurpleMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    public String getFreezeImagePath() {
        return "src/freezemonster/images/monster7bg.png";
    }

    @Override
    public String getImagePath() {
        return "src/freezemonster/images/monster7.png";
    }
}
