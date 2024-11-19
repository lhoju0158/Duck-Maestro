package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame {
    private String name;
    private String composer;
    private float measure;

    public Score(String name, String composer, int n, int m) {
        // Set basic variables
        this.name = name;
        this.composer = composer;
        this.measure = (float) n / (float) m;

        // Set frame
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1700, 900);
        setLayout(null);

        // Create Line background panel
        Line line = new Line();
        line.setBounds(0, 0, 1700, 900); // Line panel 크기 설정
        line.setLayout(null); // Allow absolute positioning for child components
        add(line);

        // Add titleName JLabel on Line background
        JLabel titleName = new JLabel(name);
        titleName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
        titleName.setHorizontalAlignment(SwingConstants.CENTER);
        titleName.setBounds(10, 50, 300, 50); // 위치와 크기 설정
        line.add(titleName);

        // Add draggable JLabel on Line background
        DraggableLabel draggableLabel = new DraggableLabel("Drag Me");
        draggableLabel.setBounds(100, 150, 100, 50); // 위치와 크기 설정
        line.add(draggableLabel);

        // Make everything visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Score("Drama", "IU", 4, 4);
    }

    // Background Line panel class
    class Line extends JPanel {
        private Point s1Start = new Point(350, 100);
        private Point s1End = new Point(950, 100);
        private Point s2Start = new Point(1050, 100);
        private Point s2End = new Point(1650, 100);
        private int smallGap = 15;
        private int largeGap = 30;

        public Line() {
            setOpaque(true); // Prevent unnecessary background repainting
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);

            Point tempS1Start = new Point(s1Start);
            Point tempS1End = new Point(s1End);
            Point tempS2Start = new Point(s2Start);
            Point tempS2End = new Point(s2End);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 5; j++) {
                    g.drawLine(tempS1Start.x, tempS1Start.y + j * smallGap, tempS1End.x, tempS1End.y + j * smallGap);
                    g.drawLine(tempS2Start.x, tempS2Start.y + j * smallGap, tempS2End.x, tempS2End.y + j * smallGap);
                }
                tempS1Start.y += (4 * smallGap + largeGap);
                tempS1End.y += (4 * smallGap + largeGap);
                tempS2Start.y += (4 * smallGap + largeGap);
                tempS2End.y += (4 * smallGap + largeGap);
            }
        }
    }
}

// Separate DraggableLabel class
class DraggableLabel extends JLabel {
    private Point initialClick;

    public DraggableLabel(String text) {
        super(text);
        setOpaque(true);
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setHorizontalAlignment(SwingConstants.CENTER);

        // Add drag functionality to the label
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint(); // 마우스 클릭 위치 저장
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JComponent source = (JComponent) e.getSource();
                Point currentLocation = source.getLocation();

                // 마우스 이동 거리 계산
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int newX = currentLocation.x + xMoved;
                int newY = currentLocation.y + yMoved;

                source.setLocation(newX, newY); // 새로운 위치 설정
                source.getParent().repaint(); // Parent를 리페인트하여 배경 유지
            }
        });
    }
}
