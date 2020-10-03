package spriteframework.sprite;

import spriteframework.board.AbstractBoard;
import spriteframework.utils.Commons;

import javax.swing.*;

public abstract class MainFrame extends JFrame {

    protected abstract AbstractBoard createBoard();

    public MainFrame(String name) {

        add(createBoard());

        setTitle(name);
        setSize(Commons.BOARD_WIDTH, Commons.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
