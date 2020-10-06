package freezemonster.state;

import freezemonster.sprites.MonsterSprite;
import spriteframework.state.State;

public class DeathState extends State {

    private final MonsterSprite monsterSprite;

    public DeathState(MonsterSprite monsterSprite) {
        super(monsterSprite);
        this.monsterSprite = monsterSprite;
        onDeath();
    }

    @Override
    public void onDeath() {
        monsterSprite.freeze();
    }

}
