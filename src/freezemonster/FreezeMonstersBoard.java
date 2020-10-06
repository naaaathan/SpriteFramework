package freezemonster;


import freezemonster.sprites.*;
import freezemonster.state.DeathState;
import spriteframework.board.AbstractBoard;
import spriteframework.sprite.BadSprite;
import spriteframework.sprite.MoveDirection;
import spriteframework.sprite.Shot;
import spriteframework.sprite.command.impl.ShotCommand;
import spriteframework.utils.Commons;
import spriteframework.sprite.Player;
import spriteframework.utils.UtilCommons;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class FreezeMonstersBoard extends AbstractBoard {

    private Shot shot;
    private LinkedList<MonsterSprite> monsterSprites;
    private int freezes = 0;
    private boolean firstTime = true;

    @Override
    protected void createBadSprites() {
        // TODO LIMITAR PARA GERAR O MONSTRO DENTRO DO FRAME (NUMBER_OF_MONSTERS_TO_FREEZE)
        monsterSprites = new LinkedList(Arrays.asList(
                new BlueMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new CyanMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new PurpleMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT)),
                new RedMonster(new Random().nextInt(Commons.BOARD_WIDTH), new Random().nextInt(Commons.BOARD_HEIGHT))
        ));
        badSprites.addAll(monsterSprites);
    }

    @Override
    public void gameInit() {
        addPlayer(new Woody());
        numberPlayers = 1;
        badSprites = new LinkedList<BadSprite>();
        createBadSprites();
        createOtherSprites();
        shot = new FreezeRay();
    }

    @Override
    protected void createOtherSprites() {
        shot = new FreezeRay();
    }

    private void drawShot(Graphics g) {
        if (shot.isVisible()) {
            g.drawImage(shot.getImage(), shot.getX(), shot.getY(), this);
        }
    }

    @Override
    protected void drawOtherSprites(Graphics g) {
        drawShot(g);
    }

    @Override
    protected void processOtherSprites(Player player, KeyEvent e) {
        int x = player.getX();
        int y = player.getY();
        int key = e.getKeyCode();

        Shot shotImpl = new FreezeRay(x, y);
        ShotCommand shotCommand = new ShotCommand(shot, shotImpl, key);

        if (inGame) {
            shotCommand.execute();
        }

        shot = shotCommand.getShot();
    }

    @Override
    protected void update() {
        // TODO AVALIAR SE É POSSIVEL SUBSTITUIR ESSA VALIDAÇÃO POR UM OBSERVER (HÁ DE SER IMPLEMENTADO NO FRAMEWORK)
        if (freezes == Commons.NUMBER_OF_MONSTERS_TO_FREEZE) {
            inGame = false;
            timer.stop();
            message = "Game won!";
        }

        this.players.forEach(Player::act);

        hitMonster();

        Iterator<MonsterSprite> it = monsterSprites.iterator();

        while (it.hasNext()) {
            MonsterSprite monster = it.next();

            if (!monster.isFreezed()) {
                if (new Random().nextInt(7) > 1) {
                    moveTo(monster, monster.getMonsterDirection());
                } else {
                    MoveDirection randomDirection = UtilCommons.randomDirection();
                    monster.setMonsterDirection(randomDirection);
                    moveTo(monster, randomDirection);
                }

            }
        }

        updateOtherSprites();
    }

    protected void updateOtherSprites() {
        Random generator = new Random();

        for (MonsterSprite monster : monsterSprites) {
            int shotChance = generator.nextInt(15);
            Goop goop = monster.getGoop();

            if (shotChance == Commons.CHANCE && monster.isVisible() && !monster.isFreezed() && goop.isDestroyed()) {
                goop.setDestroyed(false);
                goop.setX(monster.getX());
                goop.setY(monster.getY());
            }
            if (players.get(0).isVisible() && !goop.isDestroyed()) {
                if (UtilCommons.checkContact(goop, this.players.get(0))) {
                    players.get(0).setDying(true);
                    goop.setDestroyed(true);
                }
            }
            if (!goop.isDestroyed()) {
                if (shot.isVisible() && UtilCommons.checkContact(goop, shot)) {
                    goop.setDestroyed(true);
                    shot.setShotDirection(null);
                    shot.die();
                } else {
                    if (goop.getGoopDirection() == null) {
                        goop.setGoopDirection(monster.getMonsterDirection());
                    }

                    if (goop.getGoopDirection().equals(MoveDirection.BOTTOM)) {
                        if (goop.getY() >= Commons.BOARD_HEIGHT) {
                            goop.setGoopDirection(null);
                            goop.setDestroyed(true);
                        } else {
                            goop.setY(goop.getY() + 1);
                        }
                    } else if (goop.getGoopDirection().equals(MoveDirection.TOP)) {
                        if (goop.getY() < 0) {
                            goop.setGoopDirection(null);
                            goop.setDestroyed(true);
                        } else {
                            goop.setY(goop.getY() - 1);
                        }
                    } else if (goop.getGoopDirection().equals(MoveDirection.LEFT)) {
                        if (goop.getX() < 0) {
                            goop.setGoopDirection(null);
                            goop.setDestroyed(true);
                        } else {
                            goop.setX(goop.getX() - 1);
                        }
                    } else if (goop.getGoopDirection().equals(MoveDirection.RIGHT)) {
                        if (goop.getX() >= Commons.BOARD_WIDTH) {
                            goop.setGoopDirection(null);
                            goop.setDestroyed(true);
                        } else {
                            goop.setX(goop.getX() + 1);
                        }
                    }
                }
            }
        }
    }

    private void moveTo(MonsterSprite monster, MoveDirection randomDirection) {
        if (randomDirection.equals(MoveDirection.BOTTOM)) {
            monster.moveY(1);
        } else if (randomDirection.equals(MoveDirection.TOP)) {
            monster.moveY(-1);
        } else if (randomDirection.equals(MoveDirection.LEFT)) {
            monster.moveX(-1);
        } else if (randomDirection.equals(MoveDirection.RIGHT)) {
            monster.moveX(1);
        }
    }

    private void hitMonster() {
        if (shot.isVisible()) {
            for (MonsterSprite monster : monsterSprites) {
                if (monster.isVisible() && shot.isVisible()) {
                    if (UtilCommons.checkContact(shot, monster)) {
                        if (!monster.isFreezed()) {
                            freezes++;
                            new DeathState(monster);
                        }
                        shot.die();
                    }
                }
            }

            Player player = this.players.get(0);

            if (!firstTime && shot.getShotDirection() == null) {
                shot.setShotDirection(player.getPlayerDirection());
            }

            if (shot.getShotDirection() != null && (shot.getShotDirection().equals(MoveDirection.TOP) || shot.getShotDirection().equals(MoveDirection.BOTTOM))) {
                if (shot.getY() > 0 && shot.getY() < Commons.BOARD_HEIGHT) {
                    if (shot.getShotDirection().equals(MoveDirection.TOP)) {
                        shot.setY(shot.getY() - 4);
                    } else {
                        shot.setY(shot.getY() + 4);
                    }
                } else {
                    shot.setShotDirection(null);
                    shot.die();
                }
            } else if (shot.getShotDirection() != null && (shot.getShotDirection().equals(MoveDirection.LEFT) || shot.getShotDirection().equals(MoveDirection.RIGHT))) {
                if (shot.getX() > 0 && shot.getX() < Commons.BOARD_WIDTH) {
                    if (shot.getShotDirection().equals(MoveDirection.LEFT)) {
                        shot.setX(shot.getX() - 4);
                    } else if (shot.getShotDirection().equals(MoveDirection.RIGHT)) {
                        shot.setX(shot.getX() + 4);
                    }
                } else {
                    shot.setShotDirection(null);
                    shot.die();
                }
            }

            if (firstTime) {
                firstTime = false;
                shot.die();
            }
        }
    }

}
