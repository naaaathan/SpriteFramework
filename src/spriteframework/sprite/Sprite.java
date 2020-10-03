package spriteframework.sprite;

import java.awt.*;

public class Sprite {

    private boolean visible;
    private Image image;
    private boolean dying;

    private int speedX = 0;
    private int speedY = 0;

    public int x;
    public int y;


    public Sprite() {

        visible = true;
    }

    public void die() {

        visible = false;
    }

    public boolean isVisible() {

        return visible;
    }

    protected void setVisible(boolean visible) {

        this.visible = visible;
    }

    public void setImage(Image image) {

        this.image = image;
    }

    public Image getImage() {

        return image;
    }

    public void setX(int x) {

        this.x = x;
    }

    public void setY(int y) {

        this.y = y;
    }

    public int getY() {

        return y;
    }

    public int getX() {

        return x;
    }

    public void setDying(boolean dying) {

        this.dying = dying;
    }

    public boolean isDying() {

        return this.dying;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

//    @Override
//    public void move(Direction direction) {
//
//        if (direction.equals(Direction.BOTTOM) || direction.equals(Direction.TOP)) {
//            this.y += speedY;
//        } else if (direction.equals(Direction.RIGHT) || direction.equals(Direction.LEFT)) {
//            this.x += speedX;
//        }
//    }

    public void moveX(int x) {
        this.x += x;
    }
}
