package spaceinvaders.state;

import spriteframework.sprite.Sprite;
import spriteframework.state.State;

public class DeathState extends State {

    public DeathState(Sprite sprite) {
        super(sprite);
        onDeath();
    }

    @Override
    public void onDeath() {
        sprite.die();
    }
}
