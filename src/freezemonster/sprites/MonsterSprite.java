package freezemonster.sprites;


import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;

import java.util.LinkedList;

public abstract class MonsterSprite extends BadnessBoxSprite {

    private Goop goop;
    private int monsterWidth;
    private int monsterHeight;

    public int getMonsterWidth() {
        return monsterWidth;
    }

    public void setMonsterWidth(int monsterWidth) {
        this.monsterWidth = monsterWidth;
    }

    public int getMonsterHeight() {
        return monsterHeight;
    }

    public void setMonsterHeight(int monsterHeight) {
        this.monsterHeight = monsterHeight;
    }

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
