package spriteframework.utils;

import spriteframework.sprite.MoveDirection;
import spriteframework.sprite.Sprite;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class UtilCommons {

    public static Image getScaledImage(Image srcImg, int w, int h){
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }

    public static MoveDirection randomDirection()  {
        return MoveDirection.class.getEnumConstants()[new Random().nextInt(MoveDirection.class.getEnumConstants().length)];
    }

    public static Boolean outOfBoard(Sprite sprite) {
        Rectangle bounds = sprite.getBounds();
        return !(new Rectangle((int)bounds.getWidth(), (int)bounds.getHeight(),
                Commons.BOARD_WIDTH - (int)bounds.getWidth() * 2, Commons.BOARD_HEIGHT - (int)bounds.getHeight() * 3).intersects(sprite.getBounds()));
    }

    public static Boolean checkCollision(Sprite sprite1, Sprite sprite2) {
        return sprite1.getBounds().intersects(sprite2.getBounds());
    }
}
