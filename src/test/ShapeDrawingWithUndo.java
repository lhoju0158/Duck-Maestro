package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;

public class ShapeDrawingWithUndo extends JFrame {
    public ShapeDrawingWithUndo() {
        setTitle("타원과 가로선 생성 및 Undo");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        DrawingPanel panel = new DrawingPanel();
        add(panel, BorderLayout.CENTER);

        JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(e -> panel.undoLastShape());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(undoButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    class DrawingPanel extends JPanel {
        private final ArrayList<ShapeGroup> shapes = new ArrayList<>();

        public DrawingPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point clickPoint = e.getPoint();

                    // 타원 생성
                    Ellipse2D ellipse = new Ellipse2D.Double(clickPoint.x - 50, clickPoint.y - 30, 100, 60);
                    AffineTransform rotateTransform = AffineTransform.getRotateInstance(
                            Math.toRadians(15), clickPoint.x, clickPoint.y); // 타원 회전 (15도)
                    Shape rotatedEllipse = rotateTransform.createTransformedShape(ellipse);

                    // 가로선 생성 (타원의 중심에 위치)
                    Line2D horizontalLine = new Line2D.Double(clickPoint.x - 60, clickPoint.y, clickPoint.x + 60, clickPoint.y);

                    // ShapeGroup에 추가
                    shapes.add(new ShapeGroup(rotatedEllipse, horizontalLine));
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            for (ShapeGroup group : shapes) {
                // 타원 그리기
                g2.setColor(Color.BLUE);
                g2.draw(group.getRotatedEllipse());

                // 가로선 그리기
                g2.setColor(Color.RED);
                g2.draw(group.getHorizontalLine());
            }
        }

        public void undoLastShape() {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1); // 마지막 그룹 제거
                repaint();
            }
        }
    }

    class ShapeGroup {
        private final Shape rotatedEllipse;
        private final Shape horizontalLine;

        public ShapeGroup(Shape rotatedEllipse, Shape horizontalLine) {
            this.rotatedEllipse = rotatedEllipse;
            this.horizontalLine = horizontalLine;
        }

        public Shape getRotatedEllipse() {
            return rotatedEllipse;
        }
        public Shape getHorizontalLine() {
            return horizontalLine;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShapeDrawingWithUndo example = new ShapeDrawingWithUndo();
            example.setVisible(true);
        });
    }
}
