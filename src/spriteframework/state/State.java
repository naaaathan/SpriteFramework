package spriteframework.state;

import spriteframework.sprite.Sprite;

public abstract class State {

    protected Sprite sprite;

    public State(Sprite sprite) {
        this.sprite = sprite;
    }

    public abstract void onDeath();



}
