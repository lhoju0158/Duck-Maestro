package pages;

import Grapics.ScoreBackground;
import Grapics.ScoreForeground;
import Section.CreateSection;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.ArrayList;


public class Scorepage extends JFrame {
    public static double measure = 1.0; //  나중에 foreground에서 그리기 때 사용
    public static double remainMeasure = 1.0;
    public static final Point s1Start = new Point(94, 140);
    public static final Point s1End = new Point(810, 140);
    public static final Point s2Start = new Point(904, 140);
    public static final Point s2End = new Point(1620, 140);
    public static final int smallGap = 8;
    public static final int largeGap = 60;
    public static double insertBeat = 2.0; // 0 초과 1 이하
    public static double insertMelody = -1.0;
    public static int tempo;
    public static ScoreForeground scoreForeground;

    public static final HashMap<Double, String> originalMelodyHashmap = new HashMap<Double, String>() {{
        put(0.0, "./sounds/G3.wav");
        put(0.5, "./sounds/G#3_Ab3.wav");
        put(1.0, "./sounds/A3.wav");
        put(1.5, "./sounds/A#3_Bb3.wav");
        put(2.0, "./sounds/B3.wav");
        put(3.0, "./sounds/C4.wav");
        put(3.5, "./sounds/C#4_Db4.wav");
        put(4.0, "./sounds/D4.wav");
        put(4.5, "./sounds/D#4_Eb4.wav");
        put(5.0, "./sounds/E4.wav");
        put(6.0, "./sounds/F4.wav");
        put(6.5, "./sounds/F#4_Gb4.wav");
        put(7.0, "./sounds/G4.wav");
        put(7.5, "./sounds/G#4_Ab4.wav");
        put(8.0, "./sounds/A4.wav");
        put(8.5, "./sounds/A#4_Bb4.wav");
        put(9.0, "./sounds/B4.wav");
        put(10.0, "./sounds/C5.wav");
        put(10.5, "./sounds/C#5_Db5.wav");
        put(11.0, "./sounds/D5.wav");
        put(11.5, "./sounds/D#5_Eb5.wav");
        put(12.0, "./sounds/E5.wav");
        put(13.0, "./sounds/F5.wav");
        put(13.5, "./sounds/F#5_Gb5.wav");
        put(14.0, "./sounds/G5.wav");
        put(15.5, "./sounds/Rest.wav");
        put(16.0, "./sounds/Rest.wav");
        put(16.5, "./sounds/Rest.wav");
    }};
    // public static HashMap<Double, String> nowMelodyHashmap = new HashMap<Double, String>();

//    public static boolean Checking() {
//
//        if ((remainMeasure < insertBeat) ||( measure < insertBeat) || (!nowMelodyHashmap.containsKey(insertMelody))) {
//            // 안됨
//            // 여기 다시 check
//            return false;
//        }
//        // 된다!
//        // 그러면 이제 melody 만들고 값 넣기
//
//        return true;
//    }
public static boolean Checking() {
    if (remainMeasure < insertBeat) {
        System.out.println("Fail: remainMeasure < insertBeat");
        return false;
    }
    if (measure < insertBeat) {
        System.out.println("Fail: measure < insertBeat");
        return false;
    }
    if (!originalMelodyHashmap.containsKey(insertMelody)) {
        System.out.println("Fail: insertMelody not in nowMelodyHashmap");
        return false;
    }
    // 이제 멜로디 그리고, 생성하기!

    return true;
}


    public Scorepage(String name, String composer, int n, int m, int tempo) {
        this.tempo = tempo;
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1690, 900);
        setLayout(null);

        Container c = getContentPane();

        ScoreBackground scoreBackground = new ScoreBackground(name, composer, n, m);
        scoreBackground.setLocation(0, 0);
        scoreBackground.setSize(1690, 900);


        scoreForeground = new ScoreForeground();
        scoreForeground.setLocation(0, 0);
        scoreForeground.setSize(1690, 900);


        CreateSection createSection = new CreateSection();
        createSection.setLocation(0, 0);
        createSection.setSize(1690, 900);

        c.add(scoreBackground);
        c.add(scoreForeground);
        c.add(createSection);

        c.setComponentZOrder(scoreBackground, c.getComponentCount() - 3);
        c.setComponentZOrder(scoreForeground, c.getComponentCount() - 2);
        c.setComponentZOrder(createSection, c.getComponentCount() - 1);

        setVisible(true);
    }

//    public static void settingNowAccidentals(ArrayList<double[]> accidentals) {
//        // originalMelodyHashmap = new HashMap<>(originalMelodyHashmap);
//        for (int i = 0; i < accidentals.size(); i++) {
//            double melody = accidentals.get(i)[0];
//            double accidental = accidentals.get(i)[1];
//            if (originalMelodyHashmap.containsKey(melody)) {
//                originalMelodyHashmap.put(melody, originalMelodyHashmap.get(melody + accidental));
//            }
//        }
//    }

    public static void main(String[] args) {
        new Scorepage("Drama", "IU", 4, 4, 107);

    }
}