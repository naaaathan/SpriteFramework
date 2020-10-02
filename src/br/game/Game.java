package com.zetcode;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Game extends JFrame  {

    public Game() {

        initUI();
    }

    private void initUI() {

        add(new com.zetcode.BoardNew());


        setTitle("Space Invaders");
        setSize(com.zetcode.CommonsNew.BOARD_WIDTH, com.zetcode.CommonsNew.BOARD_HEIGHT);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new Game();
            ex.setVisible(true);
        });
    }
}
