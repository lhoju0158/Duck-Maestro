//package notUse;
//
//import pages.Score;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseMotionAdapter;
//import java.io.File;
//import java.util.ArrayList;
//
//
//
//public class ComponentLabel extends JLabel {
//    private Point initialClick;
//    private Point originalLocation;
//    private Point releasePosition;
//    private double beat;
//    private String noteOrRest;
//    private Point centerPosition;
//    private int[] modifyValue = new int[2];
//
//    public ComponentLabel(ImageIcon icon,String imagePath) {
//        super(icon); // Set the image as an icon for the label
//        setOpaque(false); // Make label background transparent to show the image only
//
//
//        // Split imagePath to extract noteOrRest, beat, and pointer
//        String[] strings = imagePath.split("\\(");
//
//        this.noteOrRest = strings[0].substring(strings[0].length() - 4); // note or rest
//        String beatString = strings[1].split("\\)")[0]; // First set of parentheses
//        this.beat = Double.parseDouble(beatString); // Convert to double
//
//        String pointerString = strings[2].split("\\)")[0]; // Second set of parentheses
//        String[] coordinates = pointerString.split(",");
//        int x = Integer.parseInt(coordinates[0].trim()); // Parse x-coordinate
//        int y = Integer.parseInt(coordinates[1].trim()); // Parse y-coordinate
//        this.centerPosition = new Point(x, y); // Create Point object
//        // System.out.println("Note/Rest: " + noteOrRest);
//        // System.out.println("Beat: " + beat);
//        System.out.println("Pointer: " + centerPosition);
//
//        addMouseListener(new DragMouseHandler());
//        addMouseMotionListener(new DragMouseMotionHandler());
//    }
//
////    private boolean matchLabelAndRealThing(){
////
////    }
//
//    // MouseListener 클래스
//    private class DragMouseHandler extends MouseAdapter {
//        @Override
//        public void mousePressed(MouseEvent e) {
//            initialClick = e.getPoint(); // 마우스 클릭 위치 저장
//            originalLocation = getLocation();
//            modifyValue[0] = centerPosition.x - initialClick.x;
//            modifyValue[1] = centerPosition.y - initialClick.y;
//            System.out.printf("Initial Click Position: %s%n", initialClick);
//            //System.out.printf("originalLocation Click Position: %s%n", originalLocation);
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
////            releasePosition = getLocation(); // 컴포넌트의 최종 위치 저장
////            System.out.printf("Initial Click Position: %s%n", initialClick);
////            System.out.printf("Release Position: %s%n", releasePosition);
////
////            if (Score.Checking(beat, noteOrRest)) {
////
////            }
//            Score.call(beat,noteOrRest,modifyValue);
//
//            setLocation(originalLocation); // 컴포넌트를 원래 위치로 복귀
//            getParent().repaint(); // Parent를 리페인트하여 화면을 업데이트
//
//        }
//
//    }
//    private class DragMouseMotionHandler extends MouseMotionAdapter {
//        @Override
//        public void mouseDragged(MouseEvent e) {
//            JComponent source = (JComponent) e.getSource();
//            Point currentLocation = source.getLocation();
//
//            // 마우스 이동 거리 계산
//            int xMoved = e.getX() - initialClick.x;
//            int yMoved = e.getY() - initialClick.y;
//            int newX = currentLocation.x + xMoved;
//            int newY = currentLocation.y + yMoved;
//
//            source.setLocation(newX, newY); // 새로운 위치 설정
//            source.getParent().repaint(); // Parent를 리페인트하여 배경 유지
//            releasePosition = source.getLocation();
//        }
//    }
//
//}
