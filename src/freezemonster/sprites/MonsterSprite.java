package freezemonster.sprites;


import spriteframework.sprite.BadSprite;
import spriteframework.sprite.BadnessBoxSprite;
import spriteframework.sprite.MoveDirection;
import spriteframework.utils.UtilCommons;

import javax.swing.*;
import java.util.LinkedList;

public abstract class MonsterSprite extends BadnessBoxSprite {

    private Goop goop;
    private int monsterWidth;
    private int monsterHeight;
    private MoveDirection monsterDirection;
    private boolean freezed;

    public abstract String getFreezeImagePath();
    public abstract String getImagePath();

    public void freezeMonster() {
        ImageIcon ii = new ImageIcon(getFreezeImagePath());
        setImage(UtilCommons.getScaledImage(ii.getImage(), 30, 30));
    }

    public void initMonster(int c1, int c2) {
        this.x = c1;
        this.y = c2;

        setGoop(new Goop(c1,c2));

        ImageIcon ii = new ImageIcon(getImagePath());

        setImage(UtilCommons.getScaledImage(ii.getImage(), 30, 30));
    }

    public MoveDirection getMonsterDirection() {
        return monsterDirection;
    }

    public void setMonsterDirection(MoveDirection monsterDirection) {
        this.monsterDirection = monsterDirection;
    }

    public void freeze() {
        setFreezed(true);
        this.freezeMonster();
    }

    public boolean isFreezed() {
        return freezed;
    }

    public void setFreezed(boolean freezed) {
        this.freezed = freezed;
    }

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
        this.freezed = false;
        this.setMonsterDirection(MoveDirection.TOP);
        initMonster(c1, c2);
    }

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
