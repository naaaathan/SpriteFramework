package spriteframework.sprite.command.impl;

import com.zetcode.sprite.Shot;
import spriteframework.sprite.command.Command;

import java.awt.event.KeyEvent;

public class ShotCommand implements Command {

    private Shot shot;
    private int keyEvent;
    private Shot shotImpl;


    public ShotCommand(Shot shot, Shot shotImpl, int keyEvent) {

        this.shot = shot;
        this.shotImpl = shotImpl;
        this.keyEvent = keyEvent;


    }

    @Override
    public void execute() {

        if (keyEvent == KeyEvent.VK_SPACE) {

            if (!shot.isVisible()) {
                shot = shotImpl;
            }
        }


    }

    public Shot getShot() {
        return shot;
    }

    public void setShot(Shot shot) {
        this.shot = shot;
    }

}
