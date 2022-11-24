package game.chess.util;

import java.awt.Color;

public class UsefulMethods
{
	/**
	 * Probably just copied from somewhere, I don't remember writing this...
	 * @param c
	 * @return a blended Color
	 */
	public static Color blend(Color... c) {
	    if (c == null || c.length <= 0) {
	        return null;
	    }
	    float ratio = 1f / ((float) c.length);

	    int a = 0;
	    int r = 0;
	    int g = 0;
	    int b = 0;

	    for (int i = 0; i < c.length; i++) {
	        int rgb = c[i].getRGB();
	        int a1 = (rgb >> 24 & 0xff);
	        int r1 = ((rgb & 0xff0000) >> 16);
	        int g1 = ((rgb & 0xff00) >> 8);
	        int b1 = (rgb & 0xff);
	        a += ((int) a1 * ratio);
	        r += ((int) r1 * ratio);
	        g += ((int) g1 * ratio);
	        b += ((int) b1 * ratio);
	    }

	    return new Color(a << 24 | r << 16 | g << 8 | b);
	}
}
