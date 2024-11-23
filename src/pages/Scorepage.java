package pages;

import Grapics.ScoreBackground;
import Grapics.ScoreForeground;
import Section.CreateSection;

import javax.swing.*;
import java.awt.*;
import java.io.File;


public class Scorepage extends JFrame {
    public static float measure;
    public static final Point s1Start = new Point(94, 140);
    public static final Point s1End = new Point(810, 140);
    public static final Point s2Start = new Point(904, 140);
    public static final Point s2End = new Point(1620, 140);
    public static final int smallGap = 8;
    public static final int largeGap = 60;
    public static int nextSpotX = s1Start.x + 7;
    public static int nowMeasureStartX = s1Start.x;
    public static int remainMeasure;

    public static Point now;
    public static int noteOrRestOrRepeatmark = -1; // 0 = note, 1 = rest, 2 = repeatmark;

    public static int insertBeat;
    public static int insertMelody;

    public static final File notesDir = new File("./images/notes");
    public static final File restDir = new File("./images/rests");

    public static boolean Checking(double thisBeat, String noteOrRest) {
        // checking해야하는것
        // 1) nextSpotX에 오차 범위 내에 있나
        // 2) measure를 안넘나 => note인지 rest인지, beat인지 아닌지 확인하기
        return false;
    }
    public void createMelody() {
        // 멜로디 생성 메소드 구현
    }
    public static void call(double beat,String noteOrRest,int[] modifyValue){
        Point mouseRealLocation = MouseInfo.getPointerInfo().getLocation(); // 현재 마우스 커서 위치 가져오기
        Point mouseLocation = new Point(mouseRealLocation.x+modifyValue[0],mouseRealLocation.y+modifyValue[1]);
        now = new Point(mouseLocation.x-7,mouseLocation.y-30); // 차이 존재

        System.out.printf("it's center Position: x=%d, y=%d%n", mouseLocation.x-7, mouseLocation.y-30);

        if (Checking(beat,noteOrRest));
    }

    public Scorepage(String name, String composer, int n, int m) {
        // Frame 설정
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1690, 900);
        setLayout(null);

        Container c = getContentPane();

        // Line 배경 Panel 추가
        ScoreBackground scoreBackground = new ScoreBackground(name, composer, n, m);
        scoreBackground.setBounds(0, 0, 1690, 900);
        // scoreBackground.setLayout(null);

        c.add(scoreBackground);

        // Foreground 추가
        ScoreForeground scoreForeground = new ScoreForeground();
        scoreForeground.setBounds(0, 0, 1690, 900);
        // scoreForeground.setLayout(null);
        // scoreForeground.setOpaque(true);


//        // ComponentSection 추가
//        ComponentSection componentSection = new ComponentSection();
//        componentSection.setBounds(0, 0, 1670, 900);
//        c.add(componentSection);

        // NotesRestsRepeatMark

        // NotesRestsRepeatMark notesRestsRepeatMark = new NotesRestsRepeatMark();
        // notesRestsRepeatMark.setBounds(0, 0, 1670, 900);

        // notesRestsRepeatMark.setOpaque(true); // 투명도 제거
        // notesRestsRepeatMark.setBackground(new Color(255, 255, 255, 0)); // 투명한 배경을 원하면 투명 색상 지정

        // CreateSection
        CreateSection createSection = new CreateSection();
        createSection.setBounds(860, 20, 760, 110);
        c.add(scoreBackground);
        c.add(scoreForeground);
        c.add(createSection);

        c.setComponentZOrder(scoreBackground, c.getComponentCount() - 3);
        c.setComponentZOrder(scoreForeground, c.getComponentCount() - 2);
        c.setComponentZOrder(createSection, c.getComponentCount() - 1);

        setVisible(true);
    }



    public static void main(String[] args) {
        Scorepage s = new Scorepage("Drama", "IU", 3, 4);
    }
}

//class ComponentLabel extends JLabel {
//    private Point initialClick;
//    private Point originalLocation;
//    private Point releasePosition;
//    private double beat;
//    private String noteOrRest;
//
//    public ComponentLabel(ImageIcon icon, String imagePath) {
//        super(icon);
//        setOpaque(false);
//
//        String[] strings = imagePath.split("\\(");
//        this.noteOrRest = strings[0].substring(strings[0].length() - 4);
//        String beatString = strings[1].split("\\)")[0];
//        this.beat = Double.parseDouble(beatString);
//
//        addMouseListener(new DragMouseHandler());
//        addMouseMotionListener(new DragMouseMotionHandler());
//    }
//
//    private class DragMouseHandler extends MouseAdapter {
//        @Override
//        public void mousePressed(MouseEvent e) {
//            initialClick = e.getPoint(); // 마우스 클릭 위치 저장
//            originalLocation = getLocation();
//            System.out.printf("Initial Click Position: %s%n", initialClick);
//            System.out.printf("originalLocation Click Position: %s%n", originalLocation);
//        }
//
//        @Override
//        public void mouseReleased(MouseEvent e) {
//            releasePosition = getLocation(); // 컴포넌트의 최종 위치 저장
//           System.out.printf("Initial Click Position: %s%n", initialClick);
//            System.out.printf("Release Position: %s%n", releasePosition);            if (Score.Checking(beat, noteOrRest)) {
//                // Note를 적절한 위치에 놓았을 때의 로직 추가
//            }
//            setLocation(originalLocation);
//            getParent().repaint();
//        }
//    }
//
//    private class DragMouseMotionHandler extends MouseMotionAdapter {
//        @Override
//        public void mouseDragged(MouseEvent e) {
//            JComponent source = (JComponent) e.getSource();
//            Point currentLocation = source.getLocation();
//
//            int xMoved = e.getX() - initialClick.x;
//            int yMoved = e.getY() - initialClick.y;
//            int newX = currentLocation.x + xMoved;
//            int newY = currentLocation.y + yMoved;
//
//            source.setLocation(newX, newY);
//            source.getParent().repaint();
//        }
//    }
//}

//class ComponentSection extends JPanel {
//    public ComponentSection(Container container) {
//        File[] noteImages = Score.notesDir.listFiles();
//        File[] restImages = Score.restDir.listFiles();
//        Point noteStartPosition = new Point(880, 25);
//        Point restStartPosition = new Point(1170, 25);
//
//        ArrayList<String> etcImages = new ArrayList<>();
//        for (File noteImage : noteImages) {
//            Point tempPosition = noteStartPosition;
//            ImageIcon temIcon = new ImageIcon(noteImage.getPath());
//            ComponentLabel noteLabel = new ComponentLabel(temIcon, noteImage.getPath());
//            noteLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//
//            if (temIcon.getIconHeight() < 20) {
//                etcImages.add(noteImage.getPath());
//            } else {
//                noteLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);
//            }
//            container.add(noteLabel);
//            tempPosition.x += 30;
//        }
//
//        for (File restImage : restImages) {
//            Point tempPosition = restStartPosition;
//            ImageIcon temIcon = new ImageIcon(restImage.getPath());
//            ComponentLabel restLabel = new ComponentLabel(temIcon, restImage.getPath());
//            restLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//
//            if (temIcon.getIconHeight() < 20) {
//                etcImages.add(restImage.getPath());
//            } else {
//                restLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);
//            }
//            container.add(restLabel);
//            tempPosition.x += 35;
//        }
//
//        for (int i = 0; i < etcImages.size(); i++) {
//            ImageIcon temIcon = new ImageIcon(etcImages.get(i));
//            ComponentLabel temLabel = new ComponentLabel(temIcon, etcImages.get(i));
//            temLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            temLabel.setBounds(1120, 10 + i * 15, 40, 25);
//            container.add(temLabel);
//        }
//    }
