package test;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ColorChangingCircles extends JPanel {
    private final ArrayList<Color> circleColors;
    private final int circleCount = 5; // 원의 개수
    private int currentIndex = 0;

    public ColorChangingCircles() {
        circleColors = new ArrayList<>();
        for (int i = 0; i < circleCount; i++) {
            circleColors.add(Color.BLUE); // 초기 색상은 파란색
        }

        // 1초 간격으로 색상 변경
        Timer timer = new Timer(1000, e -> {
            if (currentIndex < circleCount) {
                circleColors.set(currentIndex, Color.RED); // 현재 원의 색을 빨간색으로 변경
                currentIndex++;
                repaint();
            }
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int diameter = 50; // 원의 크기
        int gap = 20;      // 원 사이의 간격

        for (int i = 0; i < circleCount; i++) {
            g2.setColor(circleColors.get(i));
            g2.fillOval(50 + i * (diameter + gap), 100, diameter, diameter);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Color Changing Circles");
        ColorChangingCircles panel = new ColorChangingCircles();
        frame.add(panel);
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
