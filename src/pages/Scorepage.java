package pages;

import Grapics.ScoreBackground;
import Grapics.ScoreForeground;
import Section.CreateSection;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.HashMap;


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
    // public static JLabel selectedImage;

    public static HashMap<Double, String> melodyHashmap = new HashMap<Double, String>() {{
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

    public static boolean Checking() {
        // checking해야하는것
        // 1) nextSpotX에 오차 범위 내에 있나
        // 2) measure를 안넘나 => note인지 rest인지, beat인지 아닌지 확인하기
        // 3) 유효하지 않은 멜로디
        if ((remainMeasure < insertBeat) || measure < insertBeat || !melodyHashmap.containsKey(insertMelody)) {
            // 안됨
            // System.out.println("remainMeasure = "+remainMeasure+" insertBeat = "+insertBeat+" measure = "+measure);

            return false;
        }
        // 된다!
        // 그러면 이제 melody 만들고 값 넣기

        return true;
    }
//    public static void whichSelected(){
//        selectedImage.setIcon(new ImageIcon("./images/notes/note"+String.valueOf(insertBeat)+".png"));
//        selectedImage.repaint();
//        System.out.println("./images/notes/note"+String.valueOf(insertBeat)+".png");
//    }

    public Scorepage(String name, String composer, int n, int m) {

        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1690, 900);
        setLayout(null);

        Container c = getContentPane();

        ScoreBackground scoreBackground = new ScoreBackground(name, composer, n, m);
        scoreBackground.setBounds(0, 0, 1690, 900);


        ScoreForeground scoreForeground = new ScoreForeground();
        scoreForeground.setBounds(0, 0, 1690, 900);


        CreateSection createSection = new CreateSection();
        createSection.setBounds(0, 0, 1690, 900);

//        ImageIcon imageIcon = new ImageIcon("");
//
//        selectedImage = new JLabel(imageIcon);
//        selectedImage.setBounds(0,0,1690,900);
//        selectedImage.setPreferredSize(new Dimension(40,18));

        c.add(scoreBackground);
        c.add(scoreForeground);
        c.add(createSection);
        // c.add(selectedImage);


        c.setComponentZOrder(scoreBackground, c.getComponentCount() - 3);
        c.setComponentZOrder(scoreForeground, c.getComponentCount() - 2);
        c.setComponentZOrder(createSection, c.getComponentCount() - 1);
        // c.setComponentZOrder(selectedImage,c.getComponentCount()-1);

        setVisible(true);
    }

    public static void main(String[] args) {
        Scorepage s = new Scorepage("Drama", "IU", 4, 4);
    }
}