package spriteframework.utils;

import spriteframework.sprite.MoveDirection;

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

    public static int randomByInterval(int x, int size) {
        return new Random().nextInt(size) + (x - size);
    }

    public static MoveDirection randomDirection()  {
        return MoveDirection.class.getEnumConstants()[new Random().nextInt(MoveDirection.class.getEnumConstants().length)];
    }
}
