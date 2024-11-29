package pages;

import components.*;
import Grapics.ScoreBackground;
import Grapics.ScoreForeground;
import Section.CreateSection;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Scorepage extends JFrame {
    // public static double measure = 1.0; //  나중에 foreground에서 그리기 때 사용
    // public static double remainMeasure = 1.0;
    public static final Point s1Start = new Point(94, 140);
    public static final Point s1End = new Point(810, 140);
    public static final Point s2Start = new Point(904, 140);
    public static final Point s2End = new Point(1620, 140);
    public static final int smallGap = 8;
    public static final int largeGap = 60;
    public static double insertBeat = 2.0; // 0 초과 1 이하
    public static double insertMelodyForPlay = -1.0;
    public static double[] insertMelodyForDraw = new double[]{-1.0, 0.0};
    public static int tempo;
    public static ScoreForeground scoreForeground;
    public static List<List<Element>> elements = new ArrayList<>();

    public static HashMap<Double, BeatAttributes> beatSettings = new HashMap<Double, BeatAttributes>() {{
//     public BeatAttributes(boolean isFilled,int tailNum,boolean spot)
        put(1.0, new BeatAttributes(false, 0,false));
        put(0.75, new BeatAttributes(false,0,true));
        put(0.5, new BeatAttributes(false, 0,false));
        put(0.375, new BeatAttributes(true,0,true ));
        put(0.25, new BeatAttributes(true, 0,false));
        put(0.1875, new BeatAttributes(true,1,true ));
        put(0.125, new BeatAttributes(true, 1,false));
        put(0.09375, new BeatAttributes(true, 2,true));
        put(0.0625, new BeatAttributes(true, 2,false));
    }};
    public static HashMap<Double, RestAttributes> restSetiing = new HashMap<Double, RestAttributes>() {{
        // public RestAttributes(int[] hat,boolean curl,int[] hook,boolean spot){
        put(1.0, new RestAttributes(new int[]{1,0},false,new int[]{0,0},false));
        put(0.75, new RestAttributes(new int[]{1,1},false,new int[]{0,0},true));
        put(0.5, new RestAttributes(new int[]{1,1}, false,new int[]{0,0},false));
        put(0.375, new RestAttributes(new int[]{0,0},true,new int[]{0,0} ,true));
        put(0.25, new RestAttributes(new int[]{0,0}, true,new int[]{0,0},false));
        put(0.1875, new RestAttributes(new int[]{0,0},false,new int[]{1,1},true ));
        put(0.125, new RestAttributes(new int[]{0,0}, false,new int[]{1,1},false));
        put(0.09375, new RestAttributes(new int[]{0,0}, false,new int[]{1,2},true));
        put(0.0625, new RestAttributes(new int[]{0,0}, false,new int[]{1,2},false));
    }};
    public static HashMap<Double, MelodyAttributes> melodySettings = new HashMap<Double, MelodyAttributes>() {{
        // public MelodyAttributes(boolean isUpward,int[][] lineInformation)
        // 0: line number,  1: line position;
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
        put(15.5, "./sounds/Rest.wav");
        put(16.0, "./sounds/Rest.wav");
        put(16.5, "./sounds/Rest.wav");
    }};
    // `elements` 초기화
    public static void initializeElements() {
        for (int i = 0; i < 64; i++) {
            elements.add(new ArrayList<>());
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
        System.out.println("더 이상 되돌릴 작업이 없습니다.");
        return false;
    }
    public static boolean Checking() {
        if (getNowRemainMeasure() < insertBeat) {
            System.out.println("Fail: remainMeasure < insertBeat");
            return false;
        }
        if (!originalMelodyHashmap.containsKey(insertMelodyForPlay)) {
            System.out.println("Fail: insertMelody not in nowMelodyHashmap");
            return false;
        }
        System.out.println("--------------------------");
        // System.out.println("Before: remainMeasure = "+getNowRemainMeasure()+" insertBeat = "+insertBeat);
        setPositionAndAddingMelody();
        // System.out.println("After: remainMeasure = "+getNowRemainMeasure()+" insertBeat = "+insertBeat);
        System.out.println("--------------------------");
        scoreForeground.updateShapes();
        scoreForeground.revalidate();
        scoreForeground.repaint();
        return true;
    }
    public static double getNowRemainMeasure(){
        for (int i = elements.size() - 1; i >= 0; i--) {
            List<Element> measure = elements.get(i);
            if (!measure.isEmpty()) {
                double remainMeasure =0.0;
                for(int j=0;j<measure.size();j++){
                    remainMeasure+=measure.get(j).getBeat();
                    // System.out.println(j+" NowRemainMeasure = "+remainMeasure);
                }
                // System.out.println("total remainMeasure = "+(1.0-remainMeasure));
                if(remainMeasure==1.0){
                    return 1.0;
                }
                return 1.0-remainMeasure;
            }
        }
        return 1.0; // empty 상황
    }

    public static void setPositionAndAddingMelody(){
        Element lastElement;
        Element insertElement;
        Point startPosition;

        for (int i = elements.size() - 1; i >= 0; i--) {
            List<Element> measure = elements.get(i);
            if (!measure.isEmpty()) {
                // lastElement 찾기
                lastElement = measure.get(measure.size()-1);
                System.out.println("measure's i = "+i+", lastElement = "+lastElement);
                double total =0.0;
                for(int j=0;j<measure.size();j++){
                    total+=measure.get(j).getBeat();
                    System.out.println("total = "+total);
                }
                if(total==1.0){
                    // 현재 measure에 element를 넣을 수 없을 때
                    System.out.println("element will be inserted to next measure");
                    int x =i%4;
                    int page =(i/32);
                    // remainMeasure = 1.0;
                    System.out.println("x = "+x+", y="+page);

                    if(x!=3){
                        // 1) 그냥 바로 옆 measure에 넣으면 된다
                        System.out.println("insert case 1");
                        startPosition = new Point(lastElement.getLastPosition().x+4,lastElement.getStartPosition().y);
                        insertElement = new Element(insertBeat,insertMelodyForPlay,startPosition,insertMelodyForDraw);
                    }
                    else{
                        // 2) 근데 다음 마디가 줄을 바꿔야 한다
                        if(page==0){
                            System.out.println("insert case 2");
                            // lastElement.getLastPosition will be 177
                            System.out.println("lastElement.getLastPosition = "+lastElement.getLastPosition().x);
                            startPosition = new Point(s1Start.x+2,lastElement.getLastPosition().y+(4 * smallGap + largeGap));
                            insertElement = new Element(insertBeat,insertMelodyForPlay,startPosition,insertMelodyForDraw);
                        }
                        else{
                            // 3) 근데 다음 마디가 page 2의 첫마디다
                            System.out.println("insert case 3");
                            int buttonY = (s2Start.y + smallGap * 6 + (int) (smallGap / 2));
                            startPosition = new Point(s2Start.x+2,(int) (buttonY - 4 * insertMelodyForDraw[0]));
                            insertElement = new Element(insertBeat,insertMelodyForPlay,startPosition,insertMelodyForDraw);
                        }
                    }
                    if (i + 1 < elements.size()) {
                        elements.get(i + 1).add(insertElement);
                    } else {
                        List<Element> newMeasure = new ArrayList<>();
                        newMeasure.add(insertElement);
                        elements.add(newMeasure);
                    }
                }
                else{
                    // 현재 measure에 element를 넣을 수 있을 때
                    System.out.println("insert case 4");
                    System.out.println("element will be inserted to this measure");
                    System.out.println("lastElement.getLastPosition().x = "+lastElement.getLastPosition().x+ " lastElement.getLastPosition().y = "+lastElement.getLastPosition().y);
                    startPosition = new Point(lastElement.getLastPosition().x+1,lastElement.getLastPosition().y);
                    insertElement = new Element(insertBeat,insertMelodyForPlay,startPosition,insertMelodyForDraw);
                    measure.add(insertElement);
                }
                // remainMeasure-=insertBeat;
                // System.out.println("in Scorepage, remainMeasure = "+remainMeasure);
                return;
            }
        }
        int buttonY = (s1Start.y + smallGap * 6 + (int) (smallGap / 2));
        insertElement = new Element(insertBeat,insertMelodyForPlay,new Point(s1Start.x+2+10,(int) (buttonY - 4 * insertMelodyForDraw[0])),insertMelodyForDraw);
        elements.get(0).add(insertElement);
    }


    public Scorepage(String name, String composer, int n, int m, int tempo) {
        this.tempo = tempo;
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1690, 900);
        setLayout(null);

        initializeElements();



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

    public static void main(String[] args) {
        new Scorepage("Drama", "IU", 4, 4, 107);
    }
}