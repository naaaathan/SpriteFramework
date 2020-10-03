package freezemonster.sprites;


import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import java.util.LinkedList;

public abstract class MonsterSprite extends BadnessBoxSprite {

    private Goop goop;

    public MonsterSprite(int c1, int c2) {
        initMonster(c1, c2);
    }

    abstract void initMonster(int c1, int c2);

    public Goop getGoop() {
        return goop;
    }

    public void setGoop(Goop goop) {
        this.goop = goop;
    }


    @Override
    public void act() {

    }


    @Override
    public LinkedList<BadSprite> getBadnesses() {
        LinkedList<BadSprite> badSprites = new LinkedList<>();
        badSprites.add(goop);
        return badSprites;
    }
}
