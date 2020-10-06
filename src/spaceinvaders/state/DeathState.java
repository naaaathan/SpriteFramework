package spaceinvaders.state;

import spriteframework.sprite.BadSprite;
import spriteframework.sprite.Sprite;
import spriteframework.state.State;

public class DeathState extends State {

    private BadSprite badSprite;

    public DeathState(Sprite sprite) {
        super(sprite);
        onDeath();
    }

    @Override
    public void onDeath() {
        sprite.die();
    }
}
