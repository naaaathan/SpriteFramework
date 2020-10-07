package freezemonster;


import spriteframework.board.AbstractBoard;
import spriteframework.sprite.MainFrame;

import java.awt.*;

public class FreezeMonster extends MainFrame {

    public FreezeMonster() {
        super("Freeze Monsters");
    }

    protected AbstractBoard createBoard() {
        return new FreezeMonstersBoard();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(FreezeMonster::new);
    }
}

