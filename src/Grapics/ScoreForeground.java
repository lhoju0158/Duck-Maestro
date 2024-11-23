package Grapics;

import pages.Scorepage;

import javax.swing.*;
import java.awt.*;

public class ScoreForeground extends JPanel {
    public static final Point s1Start = Scorepage.s1Start;
    public static final Point s1End = Scorepage.s1End;
    public static final Point s2Start = Scorepage.s2Start;
    public static final Point s2End = Scorepage.s2End;
    public static final int smallGap = Scorepage.smallGap;
    public static final int largeGap = Scorepage.largeGap;
    // public static Point now;

    public ScoreForeground() {
        setBackground(Color.cyan);
        setOpaque(false);


        // for dubugging
//        // setBackground(Color.cyan);
//
//        // 마우스 이벤트 추가
//        addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                // 더블클릭인지 확인
//                if (e.getClickCount() == 2) {
//                   Point clickPoint = e.getPoint();
//                    System.out.printf("Mouse Double-Clicked at: x=%d, y=%d%n", clickPoint.x, clickPoint.y);
//                }
//            }
//
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                // 드래그 후 마우스 버튼을 놓을 때 좌표 출력
//                Point releasePoint = e.getPoint();
//                System.out.printf("Mouse Released at in Foreground: x=%d, y=%d%n", releasePoint.x, releasePoint.y);
//            }
//        });
    }
//    public static void call(double beat,String noteOrRest){
//        Point mouseLocation = MouseInfo.getPointerInfo().getLocation(); // 현재 마우스 커서 위치 가져오기
//        now = new Point(mouseLocation.x-7,mouseLocation.y-30);
////        nowX =mouseLocation.x-7;
////        nowY = mouseLocation.y-30;
//        System.out.printf("Mouse Cursor Position: x=%d, y=%d%n", mouseLocation.x-7, mouseLocation.y-30);
//        //
//
//        if (Score.Checking(now,beat,noteOrRest));
//    }
}
