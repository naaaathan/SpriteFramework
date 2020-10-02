package com.zetcode;

import com.zetcode.sprite.AlienNew;
import com.zetcode.sprite.PlayerNew;
import com.zetcode.sprite.ShotNew;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class BoardNew extends JPanel {

    private Dimension d;
    private List<AlienNew> alienNews;
    private PlayerNew playerNew;
    private ShotNew shotNew;
    
    private int direction = -1;
    private int deaths = 0;

    private boolean inGame = true;
    private String explImg = "src/images/explosion.png";
    private String message = "Game Over";

    private Timer timer;


    public BoardNew() {
        initBoard();
        // gameInit();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(com.zetcode.CommonsNew.BOARD_WIDTH, com.zetcode.CommonsNew.BOARD_HEIGHT);
        setBackground(Color.black);

        timer = new Timer(com.zetcode.CommonsNew.DELAY, new GameCycle());
        timer.start();

        gameInit();
    }


    private void gameInit() {

        alienNews = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 6; j++) {

                var alien = new AlienNew(com.zetcode.CommonsNew.ALIEN_INIT_X + 18 * j,
                        com.zetcode.CommonsNew.ALIEN_INIT_Y + 18 * i);
                alienNews.add(alien);
            }
        }

        playerNew = new PlayerNew();
        shotNew = new ShotNew();
    }

    private void drawAliens(Graphics g) {

        for (AlienNew alienNew : alienNews) {

            if (alienNew.isVisible()) {

                g.drawImage(alienNew.getImage(), alienNew.getX(), alienNew.getY(), this);
            }

            if (alienNew.isDying()) {

                alienNew.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {

        if (playerNew.isVisible()) {

            g.drawImage(playerNew.getImage(), playerNew.getX(), playerNew.getY(), this);
        }

        if (playerNew.isDying()) {

            playerNew.die();
            inGame = false;
        }
    }

    private void drawShot(Graphics g) {

        if (shotNew.isVisible()) {

            g.drawImage(shotNew.getImage(), shotNew.getX(), shotNew.getY(), this);
        }
    }

    private void drawBombing(Graphics g) {

        for (AlienNew a : alienNews) {

            AlienNew.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {

                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
    }

    private void doDrawing(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.green);

        if (inGame) {

            g.drawLine(0, com.zetcode.CommonsNew.GROUND,
                    com.zetcode.CommonsNew.BOARD_WIDTH, com.zetcode.CommonsNew.GROUND);

            drawAliens(g);
            drawPlayer(g);
            drawShot(g);
            drawBombing(g);

        } else {

            if (timer.isRunning()) {
                timer.stop();
            }

            gameOver(g);
        }

        Toolkit.getDefaultToolkit().sync();
    }

    private void gameOver(Graphics g) {

        g.setColor(Color.black);
        g.fillRect(0, 0, com.zetcode.CommonsNew.BOARD_WIDTH, com.zetcode.CommonsNew.BOARD_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, com.zetcode.CommonsNew.BOARD_WIDTH / 2 - 30, com.zetcode.CommonsNew.BOARD_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, com.zetcode.CommonsNew.BOARD_WIDTH / 2 - 30, com.zetcode.CommonsNew.BOARD_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(message, (com.zetcode.CommonsNew.BOARD_WIDTH - fontMetrics.stringWidth(message)) / 2,
                com.zetcode.CommonsNew.BOARD_WIDTH / 2);
    }

    private void update() {

        if (deaths == com.zetcode.CommonsNew.NUMBER_OF_ALIENS_TO_DESTROY) {

            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        // player
        playerNew.act();

        // shot
        if (shotNew.isVisible()) {

            int shotX = shotNew.getX();
            int shotY = shotNew.getY();

            for (AlienNew alienNew : alienNews) {

                int alienX = alienNew.getX();
                int alienY = alienNew.getY();

                if (alienNew.isVisible() && shotNew.isVisible()) {
                    if (shotX >= (alienX)
                            && shotX <= (alienX + com.zetcode.CommonsNew.ALIEN_WIDTH)
                            && shotY >= (alienY)
                            && shotY <= (alienY + com.zetcode.CommonsNew.ALIEN_HEIGHT)) {

                        var ii = new ImageIcon(explImg);
                        alienNew.setImage(ii.getImage());
                        alienNew.setDying(true);
                        deaths++;
                        shotNew.die();
                    }
                }
            }

            int y = shotNew.getY();
            y -= 4;

            if (y < 0) {
                shotNew.die();
            } else {
                shotNew.setY(y);
            }
        }

        // aliens

        for (AlienNew alienNew : alienNews) {

            int x = alienNew.getX();

            if (x >= com.zetcode.CommonsNew.BOARD_WIDTH - com.zetcode.CommonsNew.BORDER_RIGHT && direction != -1) {

                direction = -1;

                Iterator<AlienNew> i1 = alienNews.iterator();

                while (i1.hasNext()) {

                    AlienNew a2 = i1.next();
                    a2.setY(a2.getY() + com.zetcode.CommonsNew.GO_DOWN);
                }
            }

            if (x <= com.zetcode.CommonsNew.BORDER_LEFT && direction != 1) {

                direction = 1;

                Iterator<AlienNew> i2 = alienNews.iterator();

                while (i2.hasNext()) {

                    AlienNew a = i2.next();
                    a.setY(a.getY() + com.zetcode.CommonsNew.GO_DOWN);
                }
            }
        }

        Iterator<AlienNew> it = alienNews.iterator();

        while (it.hasNext()) {

            AlienNew alienNew = it.next();

            if (alienNew.isVisible()) {

                int y = alienNew.getY();

                if (y > com.zetcode.CommonsNew.GROUND - com.zetcode.CommonsNew.ALIEN_HEIGHT) {
                    inGame = false;
                    message = "Invasion!";
                }

                alienNew.act(direction);
            }
        }

        // bombs
        var generator = new Random();

        for (AlienNew alienNew : alienNews) {

            int shot = generator.nextInt(15);
            AlienNew.Bomb bomb = alienNew.getBomb();

            if (shot == com.zetcode.CommonsNew.CHANCE && alienNew.isVisible() && bomb.isDestroyed()) {

                bomb.setDestroyed(false);
                bomb.setX(alienNew.getX());
                bomb.setY(alienNew.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = playerNew.getX();
            int playerY = playerNew.getY();

            if (playerNew.isVisible() && !bomb.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + com.zetcode.CommonsNew.PLAYER_WIDTH)
                        && bombY >= (playerY)
                        && bombY <= (playerY + com.zetcode.CommonsNew.PLAYER_HEIGHT)) {

                    var ii = new ImageIcon(explImg);
                    playerNew.setImage(ii.getImage());
                    playerNew.setDying(true);
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {

                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= com.zetcode.CommonsNew.GROUND - com.zetcode.CommonsNew.BOMB_HEIGHT) {

                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private void doGameCycle() {

        update();
        repaint();
    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            doGameCycle();
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            playerNew.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            playerNew.keyPressed(e);

            int x = playerNew.getX();
            int y = playerNew.getY();

            int key = e.getKeyCode();

            if (key == KeyEvent.VK_SPACE) {

                if (inGame) {

                    if (!shotNew.isVisible()) {

                        shotNew = new ShotNew(x, y);
                    }
                }
            }
        }
    }
}
