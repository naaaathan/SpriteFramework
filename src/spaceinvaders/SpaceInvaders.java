package spaceinvaders;

import spriteframework.board.AbstractBoard;
import spriteframework.sprite.MainFrame;

import java.awt.EventQueue;

public class SpaceInvaders extends MainFrame {

    public SpaceInvaders() {
        super("Space Invaders");
    }

    protected AbstractBoard createBoard() {
        return new SpaceInvadersBoard();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(SpaceInvaders::new);
    }
}
