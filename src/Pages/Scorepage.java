package Pages;

import Sections.*;
import Elements.*;
import Grapics.*;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.HashMap;
import java.util.List;

public class Scorepage extends JFrame {
    public static final Point s1Start = new Point(94, 140);
    public static final Point s1End = new Point(810, 140);
    public static final Point s2Start = new Point(904, 140);
    public static final Point s2End = new Point(1620, 140);
    public static final int smallGap = 8;
    public static final int largeGap = 60;
    public static double insertBeat = 2.0;
    public static double insertMelodyForPlay = -1.0;
    public static double[] insertMelodyForDraw = new double[]{-1.0, 0.0};
    public static int tempo;
    public static String songTitle = "";
    public static ScoreForeground scoreForeground;
    public static List<List<Element>> elements = new ArrayList<>();
    public static Clip currentClip;

    public static HashMap<Double, BeatAttributes> beatSettings = new HashMap<Double, BeatAttributes>() {{
        put(1.0, new BeatAttributes(false, 0, false));
        put(0.75, new BeatAttributes(false, 0, true));
        put(0.5, new BeatAttributes(false, 0, false));
        put(0.375, new BeatAttributes(true, 0, true));
        put(0.25, new BeatAttributes(true, 0, false));
        put(0.1875, new BeatAttributes(true, 1, true));
        put(0.125, new BeatAttributes(true, 1, false));
        put(0.09375, new BeatAttributes(true, 2, true));
        put(0.0625, new BeatAttributes(true, 2, false));
    }}; //https://statuscode.tistory.com/32
    public static HashMap<Double, RestAttributes> restSetting = new HashMap<Double, RestAttributes>() {{
        put(1.0, new RestAttributes(new int[]{1, 0}, false, new int[]{0, 0}, false));
        put(0.75, new RestAttributes(new int[]{1, 1}, false, new int[]{0, 0}, true));
        put(0.5, new RestAttributes(new int[]{1, 1}, false, new int[]{0, 0}, false));
        put(0.375, new RestAttributes(new int[]{0, 0}, true, new int[]{0, 0}, true));
        put(0.25, new RestAttributes(new int[]{0, 0}, true, new int[]{0, 0}, false));
        put(0.1875, new RestAttributes(new int[]{0, 0}, false, new int[]{1, 1}, true));
        put(0.125, new RestAttributes(new int[]{0, 0}, false, new int[]{1, 1}, false));
        put(0.09375, new RestAttributes(new int[]{0, 0}, false, new int[]{1, 2}, true));
        put(0.0625, new RestAttributes(new int[]{0, 0}, false, new int[]{1, 2}, false));
    }};
    public static HashMap<Double, MelodyAttributes> melodySettings = new HashMap<Double, MelodyAttributes>() {{
        put(0.0, new MelodyAttributes(true, new int[]{2, -1}));
        put(1.0, new MelodyAttributes(true, new int[]{2, 0}));
        put(2.0, new MelodyAttributes(true, new int[]{1, -1}));
        put(3.0, new MelodyAttributes(true, new int[]{1, 0}));
        put(4.0, new MelodyAttributes(true, new int[]{0, 0}));
        put(5.0, new MelodyAttributes(true, new int[]{0, 0}));
        put(6.0, new MelodyAttributes(true, new int[]{0, 0}));
        put(7.0, new MelodyAttributes(true, new int[]{0, 0}));
        put(8.0, new MelodyAttributes(true, new int[]{0, 0}));
        put(9.0, new MelodyAttributes(false, new int[]{0, 0}));
        put(10.0, new MelodyAttributes(false, new int[]{0, 0}));
        put(11.0, new MelodyAttributes(false, new int[]{0, 0}));
        put(12.0, new MelodyAttributes(false, new int[]{0, 0}));
        put(13.0, new MelodyAttributes(false, new int[]{0, 0}));
        put(14.0, new MelodyAttributes(false, new int[]{0, 0}));
    }};
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
        put(16.0, "./sounds/Rest.wav");
    }};
    public Scorepage(String name, String composer, int tempo) {
        // set container
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);

        // set variables of Scorepage
        initializeElementsAndResultSong();
        this.tempo = tempo;
        this.songTitle = name;

        // set components of container

        // container components - 1) scoreBackground
        ScoreBackground scoreBackground = new ScoreBackground(name, composer);
        scoreBackground.setLocation(0, 0);
        scoreBackground.setSize(1690, 900);

        // container components - 2) scoreForeground
        scoreForeground = new ScoreForeground();
        scoreForeground.setLocation(0, 0);
        scoreForeground.setSize(1690, 900);

        // container components - 3) createSection
        CreateSection createSection = new CreateSection();
        createSection.setLocation(0, 0);
        createSection.setSize(1690, 900);

        // container components - 4) playSection
        PlaySection playSection = new PlaySection();
        playSection.setLocation(150,15);
        playSection.setSize(100,105);

        c.add(scoreBackground);
        c.add(scoreForeground);
        c.add(createSection);
        c.add(playSection);
        c.setComponentZOrder(scoreBackground, 1); // https://blog.naver.com/celestialorb/40011002132
        c.setComponentZOrder(scoreForeground, 2);
        c.setComponentZOrder(createSection, 3);
        c.setComponentZOrder(playSection, 0);
        setSize(1690, 900);
        setLocationRelativeTo(null);
        setVisible(true);

    }
    public static void initializeElementsAndResultSong() {
        for (int i = 0; i < 64; i++) {
            elements.add(new ArrayList<>());
        }
        File outputFile = new File("./sounds/resultSong.wav");
        if (outputFile.exists()) {
            if (outputFile.delete()) {
                System.out.println("Existing file deleted: " + "./sounds/resultSong.wav");
            } else {
                System.out.println("Failed to delete existing file: " + "./sounds/resultSong.wav");
            }
        }
    }

    public static boolean undoLastElement() {
        for (int i = elements.size() - 1; i >= 0; i--) {
            List<Element> measure = elements.get(i);
            if (!measure.isEmpty()) {
                measure.remove(measure.size() - 1);
                scoreForeground.updateShapes();
                scoreForeground.revalidate();
                scoreForeground.repaint();
                return true;
            }
        }
        return false;
    }

    public static boolean Checking() {
        if (getNowRemainBeat() < insertBeat) {
            return false;
        }
        if (!originalMelodyHashmap.containsKey(insertMelodyForPlay)) {
            return false;
        }
        if (!setPositionAndAddingMelody()) {
            return false;
        }
        scoreForeground.updateShapes();
        scoreForeground.revalidate();
        scoreForeground.repaint();
        return true;
    }

    public static double getNowRemainBeat() {
        for (int i = elements.size() - 1; i >= 0; i--) {
            List<Element> measure = elements.get(i);
            if (!measure.isEmpty()) {
                double nowBeat = 0.0;
                for (int j = 0; j < measure.size(); j++) {
                    nowBeat += measure.get(j).getBeat();
                }
                if (nowBeat == 1.0) {
                    return 1.0;
                }
                return 1.0 - nowBeat;
            }
        }
        return 1.0;
    }

    public static boolean setPositionAndAddingMelody() {
        Element lastElement;
        Element insertElement;
        Point startPosition;

        for (int i = elements.size() - 1; i >= 0; i--) {
            List<Element> measure = elements.get(i);
            if (!measure.isEmpty()) {
                lastElement = measure.get(measure.size() - 1);
                double total = 0.0;
                for (int j = 0; j < measure.size(); j++) {
                    total += measure.get(j).getBeat();
                }
                if (total == 1.0) { // Case 1: insert Element to next measure
                    if ((i + 1) == 64) { // Case 1 (1): full List
                        return false;
                    }
                    if ((i + 1) != 32) { // Case 1 (2): not full List
                        if (((i + 1) % 4) != 0) { // Case 1 (2) (i): same line, next measure
                            startPosition = new Point(lastElement.getLastPosition().x + 4, lastElement.getStartPosition().y);
                            insertElement = new Element(insertBeat, insertMelodyForPlay, startPosition, insertMelodyForDraw);
                        } else {
                            if (((i + 1) / 32 )== 0) { // Case 1 (2) (ii) a: next line, first measure on first page
                                startPosition = new Point(s1Start.x, lastElement.getLastPosition().y + (4 * smallGap + largeGap));
                                insertElement = new Element(insertBeat, insertMelodyForPlay, startPosition, insertMelodyForDraw);
                            } else { // Case 1 (2) (ii) b: next line, first measure on second page
                                startPosition = new Point(s2Start.x , lastElement.getLastPosition().y + (4 * smallGap + largeGap));
                                insertElement = new Element(insertBeat, insertMelodyForPlay, startPosition, insertMelodyForDraw);
                            }
                        }
                    } else { // Case 1 (2) (iii): first measure on second page
                        startPosition = new Point(s2Start.x, (s2Start.y + smallGap * 6 + (int) (smallGap / 2)));
                        insertElement = new Element(insertBeat, insertMelodyForPlay, startPosition, insertMelodyForDraw);
                    }
                    elements.get(i + 1).add(insertElement);

                } else { // Case 2: insert Element to this measure
                    startPosition = new Point(lastElement.getLastPosition().x + 1, lastElement.getLastPosition().y);
                    insertElement = new Element(insertBeat, insertMelodyForPlay, startPosition, insertMelodyForDraw);
                    measure.add(insertElement);
                }
                return true;
            }
        }

        // Case 3: empty List
        insertElement = new Element(insertBeat, insertMelodyForPlay, new Point(s1Start.x  +11, (s1Start.y + smallGap * 6 + (int) (smallGap / 2))), insertMelodyForDraw);
        elements.get(0).add(insertElement);
        return true;
    }

    // 여기 나중에 지우기
    public static void main(String[] args) {
        new Scorepage("Drama", "IU", 107);
    }
}