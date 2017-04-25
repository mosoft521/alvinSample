package com.gmail.mosoft521.poi_browser;

import javax.swing.*;
import java.awt.*;

/**
 * <p>Contains various (well, just one at the moment) static utility
 * methods.</p>
 */
public class Util {

    /**
     * <p>Makes a Swing component inverted by swapping its foreground
     * and background colors. Hint: Depending on your needs it might
     * also be a good idea to call <tt>c.setOpaque(true)</tt>.</p>
     */
    public static void invert(JComponent c) {
        Color invBackground = c.getForeground();
        Color invForeground = c.getBackground();
        c.setBackground(invBackground);
        c.setForeground(invForeground);
    }
}
