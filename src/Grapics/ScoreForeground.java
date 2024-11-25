package Grapics;

import pages.Scorepage;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScoreForeground extends JPanel {
    public static final Point s1Start = Scorepage.s1Start;
    public static final Point s1End = Scorepage.s1End;
    public static final Point s2Start = Scorepage.s2Start;
    public static final Point s2End = Scorepage.s2End;
    public static final int smallGap = Scorepage.smallGap;
    public static final int largeGap = Scorepage.largeGap;
    private static HashMap<Double, String> nowMelodyHashma = Scorepage.nowMelodyHashmap;

    protected static HashMap<String, Double> melodyPosition = new HashMap<String, Double>() {{
        put("G3", 0.0);
        put("A3", 1.0);
        put("B3", 2.0);
        put("C4", 3.0);
        put("D4", 4.0);
        put("E4", 5.0);
        put("F4", 6.0);
        put("G4", 7.0);
        put("A4", 8.0);
        put("B4", 9.0);
        put("C5", 10.0);
        put("D5", 11.0);
        put("E5", 12.0);
        put("F5", 13.0);
        put("G5", 14.0);
        put("Rest", 16.0);
    }};

    // public static Point now;

    public ScoreForeground() {
        setBackground(Color.cyan);
        setOpaque(true);
    }

}
