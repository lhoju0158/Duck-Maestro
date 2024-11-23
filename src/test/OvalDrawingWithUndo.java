package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class OvalDrawingWithUndo extends JFrame {
    public OvalDrawingWithUndo() {
        setTitle("타원 및 선 그리기 및 Undo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        OvalPanel panel = new OvalPanel();
        add(panel, BorderLayout.CENTER);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> panel.undoLastShape());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(undoButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    // 타원 및 선 그리기용 JPanel 클래스
    class OvalPanel extends JPanel {
        private final ArrayList<ShapeGroup> shapes = new ArrayList<>(); // 타원과 선을 묶어 저장
        private Point startPoint = null; // 타원 시작점
        private Point currentPoint = null; // 현재 마우스 위치

        public OvalPanel() {
            // 마우스 이벤트 추가
            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    startPoint = e.getPoint(); // 시작점 저장
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    Point endPoint = e.getPoint(); // 끝점 저장

                    // 타원의 위치와 크기 계산
                    int x = Math.min(startPoint.x, endPoint.x);
                    int y = Math.min(startPoint.y, endPoint.y);
                    int width = Math.abs(startPoint.x - endPoint.x);
                    int height = Math.abs(startPoint.y - endPoint.y);

                    // 타원과 선을 그룹으로 추가
                    shapes.add(new ShapeGroup(
                            new Rectangle(x, y, width, height),
                            new Line2D.Double(startPoint, endPoint)
                    ));

                    // 초기화 및 화면 갱신
                    startPoint = null;
                    currentPoint = null;
                    repaint();
                }
            });

            // 마우스 이동 이벤트 추가
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    currentPoint = e.getPoint(); // 현재 마우스 위치 갱신
                    repaint();
                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    currentPoint = e.getPoint(); // 드래그 중 위치 갱신
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;

            // 저장된 모든 타원과 선을 그림
            g2.setColor(Color.BLUE);
            for (ShapeGroup group : shapes) {
                Rectangle oval = group.getOval();
                Line2D line = group.getLine();

                // 타원 그리기
                g2.drawOval(oval.x, oval.y, oval.width, oval.height);

                // 선 그리기
                g2.draw(line);
            }

            // 현재 마우스 커서 위치에 선 그리기 (드래그 중일 때)
            if (startPoint != null && currentPoint != null) {
                g2.setColor(Color.RED);
                g2.drawLine(startPoint.x, startPoint.y, currentPoint.x, currentPoint.y);
            }
        }

        // Undo 기능
        public void undoLastShape() {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1); // 마지막 그룹(타원과 선) 제거
                repaint(); // 화면 갱신
            }
        }
    }

    // 타원과 선을 묶는 클래스
    class ShapeGroup {
        private final Rectangle oval;
        private final Line2D line;

        public ShapeGroup(Rectangle oval, Line2D line) {
            this.oval = oval;
            this.line = line;
        }

        public Rectangle getOval() {
            return oval;
        }

        public Line2D getLine() {
            return line;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OvalDrawingWithUndo example = new OvalDrawingWithUndo();
            example.setVisible(true);
        });
    }
}
