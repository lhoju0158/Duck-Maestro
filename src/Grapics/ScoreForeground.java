package Grapics;

import components.*;
import components.Element;
import pages.Scorepage;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Grapics.ShapeUtils.*;

public class ScoreForeground extends JPanel {
    // public static final Point s1Start = Scorepage.s1Start;
    public static final Point s1End = Scorepage.s1End;
    public static final Point s2Start = Scorepage.s2Start;
    public static final Point s2End = Scorepage.s2End;
    // public static final int smallGap = Scorepage.smallGap;
    // public static final int largeGap = Scorepage.largeGap;
    private static ArrayList<ShapeGroup> shapes = new ArrayList<>();
    // private static int insertX;
    private static int buttonY;
    // private static Point startPosition = Scorepage.startPosition;

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
        put("B4", 9.0); // 여기부터 아래 방향
        put("C5", 10.0);
        put("D5", 11.0);
        put("E5", 12.0);
        put("F5", 13.0);
        put("G5", 14.0);
        put("Rest", 16.0);
    }};
    private static HashMap<Double, MelodyAttributes> melodySettings = new HashMap<Double, MelodyAttributes>() {{
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
    private static HashMap<Double, BeatAttributes> beatSettings = new HashMap<Double, BeatAttributes>() {{
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
    private static HashMap<Double, RestAttributes> restSetiing = new HashMap<Double, RestAttributes>() {{
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


    // public static Point now;

    public ScoreForeground() {
        setOpaque(false);
        // insertX = s1Start.x+7+10;
        // insertX = s1Start.x+7+179;

        // 원래 각 줄의 insertX는 94+7
        // 근데 첫번째는 4/4때문에 20더
        // buttonY = (s1Start.y + smallGap * 6 + (int) (smallGap / 2))+(4 * smallGap + largeGap);
        // buttonY = (s1Start.y + smallGap * 6 + (int) (smallGap / 2));
    }

    public void updateShapes() {
        shapes.clear(); // 기존 저장된 ShapeGroup 리스트 초기화

        // Scorepage.elements의 모든 measure 순회
        for (List<Element> measure : Scorepage.elements) {
            for (Element element : measure) {
                // Element에서 필요한 데이터를 가져와 ShapeGroup 생성
                ShapeGroup shapeGroup = element.getShapeGroup();

                // 생성된 ShapeGroup을 shapes 리스트에 추가
                shapes.add(shapeGroup);
            }
        }
        // System.out.println(shapes);
    }

    //    public void addingRest(double beat){
//        drawingRest(restSetiing.get(beat));
//    }

//    public void addingMelody(double melody, double beat,double modifyValue) {
//        drawingMelody(melody, melodySettings.get(melody),beat, beatSettings.get(beat),modifyValue);
//    }




//    public void drawingMelody(double melodyValue, MelodyAttributes melody, double beatValue, BeatAttributes beat,double modifyValue) {
//        // MelodyAttributes와 BeatAttributes 속성 가져오기
//        boolean isUpward = melody.getisUpward();
//        int[] lineInformation = melody.getLineInformation();
//        boolean isFilled = beat.getisFilled();
//        int tailNum = beat.getTailNum();
//        boolean spot = beat.getSpot();
//        // Point basicPoint = new Point();
//        if(modifyValue==0.5||modifyValue==-0.5){
//            // attribute 존재
//           basicPoint = new Point(insertX+15, (int) (buttonY - 4 * melodyValue));
//        }
//        else{
//            basicPoint = new Point(insertX, (int) (buttonY - 4 * melodyValue));
//        }
//
//        // 타원의 중심 좌표 계산
//
//
//        // 타원 생성 및 회전 적용
//        Ellipse2D ellipse = new Ellipse2D.Double(basicPoint.x - 6.5, basicPoint.y - ((smallGap / 2) - 0.5), 13, smallGap - 1);
//        Shape rotatedEllipse = applyRotation(ellipse, basicPoint);
//
//        // 세로선 생성
//        Line2D verticalLine = createVerticalLine(basicPoint, isUpward,beatValue);
//
//        // 가로선 생성
//        Line2D[] horizontalLines = createHorizontalLines(basicPoint, lineInformation);
//
//        // 꼬리 생성
//        GeneralPath tail = createTail(basicPoint, tailNum,isUpward);
//
//        GeneralPath attribute = createAttribute(basicPoint,modifyValue);
//
//
//        shapes.add(new ShapeGroup(rotatedEllipse, horizontalLines, verticalLine, tail, isFilled, spot,attribute, basicPoint));

        // insertX += 11;
        // x 좌표 증가


//    public void drawingRest(RestAttributes rest) {
//        int[] hatInfomation = rest.getHat();
//        boolean curlInformation = rest.getCurl();
//        int[] hookInformation = rest.getHook();
//        boolean spotInformation = rest.getSpot();
//        Point basicPoint = new Point(insertX,buttonY); // 여긴 나중에 다시
//
//        GeneralPath hat = createHat(basicPoint,hatInfomation,spotInformation);
//        GeneralPath curl = createCurl(basicPoint,curlInformation,spotInformation);
//        GeneralPath hook = createHook(basicPoint,hookInformation,spotInformation);
//        // GeneralPath spot = createSpot(basicPoint,spotInformation);
//        shapes.add(new ShapeGroup(hat, curl, hook, basicPoint));
//        insertX += 11;
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ShapeGroup group : shapes) {
            // 멜로디
            if (group.getRotatedEllipse() != null) {
                handleEllipseAndSpot(g2, group.getRotatedEllipse(), group.isFilled(), group.isSpot(), group.getBasicPoint());
            }
            if (group.getHorizontalLines() != null) {
                Line2D[] horizontalLines = group.getHorizontalLines();
                for (Line2D line : horizontalLines) {
                    g2.draw(line);
                }
            }
            if (group.getVerticalLine() != null) {
                g2.draw(group.getVerticalLine());
            }

            if (group.getTail() != null) {
                g2.fill(group.getTail());
            }
            // rest
            if(group.getHat()!=null){
                // System.out.println("(group.getBasicPoint() = "+(group.getBasicPoint()));
                g2.fill(group.getHat());
            }
            if(group.getCurl()!=null){
                g2.fill(group.getCurl());
            }
            if(group.getHook()!=null){
                g2.fill(group.getHook());
            }
            if(group.getAttribute()!=null){
                g2.fill(group.getAttribute());
            }
//            if(group.getSpot()!=null){
//                g2.draw(group.getSpot());
//            }
        }
    }
//
//
//    public boolean undoLastShape() {
//        if (!shapes.isEmpty()) {
//            shapes.remove(shapes.size() - 1); // 마지막 ShapeGroup 제거
//            repaint();
//            insertX-=10;
//            return true;
//        }
//        return false;
//    }
}



// 기존 ShapeGroup 클래스 유지

