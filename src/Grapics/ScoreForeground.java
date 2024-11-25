package Grapics;

import components.*;
import pages.Scorepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;

public class ScoreForeground extends JPanel {
    public static final Point s1Start = Scorepage.s1Start;
    public static final Point s1End = Scorepage.s1End;
    public static final Point s2Start = Scorepage.s2Start;
    public static final Point s2End = Scorepage.s2End;
    public static final int smallGap = Scorepage.smallGap;
    public static final int largeGap = Scorepage.largeGap;
    // private static HashMap<Double, String> nowMelodyHashma = Scorepage.nowMelodyHashmap;
    private static final ArrayList<ShapeGroup> shapes = new ArrayList<>();
    private static int insertX;
    private static int buttonY;

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


    // public static Point now;

    public ScoreForeground() {
        setOpaque(false);
        insertX = s1Start.x + 100;
        buttonY = s1Start.y + smallGap * 6 + (int) (smallGap / 2);

        adding( 1.0, 1.0);
        adding( 2.0, 1.0);
        adding( 3.0, 1.0);
        adding( 4.0, 1.0);
        adding( 5.0, 1.0);
        adding( 6.0, 1.0);
        adding( 7.0, 1.0);
        adding( 8.0, 1.0);
        adding( 9.0, 1.0);

        // adding(100,10,7,4);
        //        adding(10,2,7,4);
//adding(111,2,7,4);
        repaint();
    }


    public static void adding(double melody, double beat) {
        // 현재
        // melody가 될 수 있는거
        // 0 ~ 14
        // melody => 1) 세로선 방향 (위, 아래) 2) 가로선 위치 및 방향, 갯수
        // beat => 1) 타원 색 채울지 안 채울지 2) 날개 0개 1개 2개 3) 점 유무
        drawing(melody, melodySettings.get(melody),beatSettings.get(beat));

    }
    public static void drawing(double melodyValue, MelodyAttributes melody, BeatAttributes beat) {
        // MelodyAttributes와 BeatAttributes 속성 가져오기
        boolean isUpward = melody.getisUpward();
        int[] lineInformation = melody.getLineInformation();
        boolean isFilled = beat.getisFilled();
        int tailNum = beat.getTailNum();
        boolean spot = beat.getSpot();

        // 타원의 중심 좌표 계산
        Point basicPoint = new Point(insertX, (int) (buttonY - 4 * melodyValue));

        // 타원 생성 및 회전 적용
        Ellipse2D ellipse = new Ellipse2D.Double(basicPoint.x - 6, basicPoint.y - ((smallGap / 2) - 0.5), 12, smallGap - 1);
        Shape rotatedEllipse = applyRotation(ellipse, basicPoint);

        // 세로선 생성
        Line2D verticalLine = createVerticalLine(basicPoint, isUpward);

        // 가로선 생성
        Line2D[] horizontalLines = createHorizontalLines(basicPoint, lineInformation);

        // 꼬리 생성
        GeneralPath tail = createTail(basicPoint, tailNum);

        // ShapeGroup에 추가
        shapes.add(new ShapeGroup(rotatedEllipse, horizontalLines, verticalLine, tail, isFilled, spot, basicPoint));

        // x 좌표 증가
        insertX += 50;
    }
    private static Shape applyRotation(Ellipse2D ellipse, Point basicPoint) {
        AffineTransform rotateTransform = AffineTransform.getRotateInstance(
                Math.toRadians(330), basicPoint.x, basicPoint.y);
        return rotateTransform.createTransformedShape(ellipse);
    }

    /**
     * 세로선 생성
     */
    private static Line2D createVerticalLine(Point basicPoint, boolean isUpward) {
        if (isUpward) {
            return new Line2D.Double(basicPoint.x + 6, basicPoint.y, basicPoint.x + 6, basicPoint.y - 30);
        } else {
            return new Line2D.Double(basicPoint.x + 6, basicPoint.y, basicPoint.x + 6, basicPoint.y + 30);
        }
    }

    /**
     * 가로선 생성
     */
    private static Line2D[] createHorizontalLines(Point basicPoint, int[] lineInformation) {
        int numLines = lineInformation[0];
        int offset = lineInformation[1];
        Line2D[] horizontalLines = new Line2D[numLines];
        for (int i = 0; i < numLines; i++) {
            horizontalLines[i] = new Line2D.Double(
                    basicPoint.x - 9,
                    basicPoint.y + offset * 4,
                    basicPoint.x + 9,
                    basicPoint.y + offset * 4);
            offset += 4;
        }
        return horizontalLines;
    }

    /**
     * 꼬리 생성
     */
    private static GeneralPath createTail(Point basicPoint, int tailNum) {
        GeneralPath tail = new GeneralPath();
        for (int i = 0; i < tailNum; i++) {
            Point flagPoint = new Point(basicPoint.x + 6, basicPoint.y - 30 + i * 9);

            tail.moveTo(flagPoint.x, flagPoint.y); // 세로선 끝
            tail.curveTo(flagPoint.x, flagPoint.y, flagPoint.x + 2, flagPoint.y + 4, flagPoint.x + 6, flagPoint.y + 6); // 부드러운 곡선
            tail.curveTo(flagPoint.x + 6, flagPoint.y + 6, flagPoint.x + 12, flagPoint.y + 12, flagPoint.x + 6, flagPoint.y + 20);
            tail.curveTo(flagPoint.x + 6, flagPoint.y + 20, flagPoint.x + 9, flagPoint.y + 12, flagPoint.x + 4, flagPoint.y + 10);
            tail.curveTo(flagPoint.x + 4, flagPoint.y + 10, flagPoint.x + 2, flagPoint.y + 9, flagPoint.x, flagPoint.y + 8);
        }
        return tail;
    }

//    public static void Draw() {
//    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (ShapeGroup group : shapes) {
            // 타원 채우기 및 점 추가
            handleEllipseAndSpot(g2, group.getRotatedEllipse(), group.isFilled(), group.isSpot(), group.getBasicPoint());

            // 가로선 그리기
            Line2D[] horizontalLines = group.getHorizontalLines();
            if (horizontalLines != null) {
                for (Line2D line : horizontalLines) {
                    g2.draw(line);
                }
            }

            // 세로선 그리기
            g2.draw(group.getVerticalLine());

            // 꼬리 그리기
            if (group.getTail() != null) {
                g2.fill(group.getTail());
            }
        }
    }

    /**
     * 타원의 채우기 및 점 추가
     */
    private static void handleEllipseAndSpot(Graphics2D g2, Shape rotatedEllipse, boolean isFilled, boolean spot, Point basicPoint) {
        if (isFilled) {
            g2.fill(rotatedEllipse); // 타원 내부를 채우기
        } else {
            g2.draw(rotatedEllipse); // 타원 외곽만 그리기
        }

        if (spot) {
            g2.fillOval(basicPoint.x + 15, basicPoint.y - 2, 4, 4); // 점 추가
        }
    }

    public boolean undoLastShape() {
        if (!shapes.isEmpty()) {
            shapes.remove(shapes.size() - 1); // 마지막 ShapeGroup 제거
            repaint();
            insertX-=50;
            return true;
        }
        return false;
    }
}

// 기존 ShapeGroup 클래스 유지
class ShapeGroup {
    private final Shape rotatedEllipse;
    private final Line2D[] horizontalLines;
    private final Shape verticalLine;
    private final Shape tail;
    private final boolean isFilled;
    private final boolean isSpot;
    private final Point basicPoint;

    public ShapeGroup(Shape rotatedEllipse, Line2D[] horizontalLines, Shape verticalLine, Shape tail, boolean isFilled, boolean isSpot, Point basicPoint) {
        this.rotatedEllipse = rotatedEllipse;
        this.horizontalLines = horizontalLines;
        this.verticalLine = verticalLine;
        this.tail = tail;
        this.isFilled = isFilled;
        this.isSpot = isSpot;
        this.basicPoint = basicPoint;
    }

    public Shape getRotatedEllipse() {
        return rotatedEllipse;
    }

    public Line2D[] getHorizontalLines() {
        return horizontalLines;
    }

    public Shape getVerticalLine() {
        return verticalLine;
    }

    public Shape getTail() {
        return tail;
    }

    public boolean isFilled() {
        return isFilled;
    }

    public boolean isSpot() {
        return isSpot;
    }

    public Point getBasicPoint() {
        return basicPoint;
    }
}
