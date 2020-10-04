package freezemonster.sprites;

public class RedMonster extends MonsterSprite{
    public RedMonster(int c1, int c2) {
        super(c1, c2);
    }

    @Override
    public String getFreezeImagePath() {
        return "src/freezemonster/images/monster6bg.png";
    }

    @Override
    public String getImagePath() {
        return "src/freezemonster/images/monster6.png";
    }
}
