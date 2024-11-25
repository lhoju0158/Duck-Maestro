package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
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

    class OvalPanel extends JPanel {
        private final ArrayList<ShapeGroup> shapes = new ArrayList<>();

        public OvalPanel() {
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    Point clickPoint = e.getPoint();

                    // 1. 타원 (10도 회전)
                    Ellipse2D ellipse = new Ellipse2D.Double(clickPoint.x - 50, clickPoint.y - 40, 100, 80);
                    AffineTransform rotateTransform = AffineTransform.getRotateInstance(
                            Math.toRadians(340), clickPoint.x, clickPoint.y);
                    Shape rotatedEllipse = rotateTransform.createTransformedShape(ellipse);

                    // 2. 수평선 (왼쪽 -60, 오른쪽 60)
                    Line2D horizontalLine = new Line2D.Double(clickPoint.x - 60, clickPoint.y, clickPoint.x + 60, clickPoint.y);

                    // 3. 세로선 (두께 있음)
                    int verticalTopY = clickPoint.y - 100;
                    Shape thickVerticalLine = new Rectangle2D.Double(clickPoint.x - 2, verticalTopY, 4, 100);

                    // 4. 깃발 (세로선 위)
                    Polygon flag = new Polygon();
                    flag.addPoint(clickPoint.x, verticalTopY); // 꼭짓점 (세로선 위)
                    flag.addPoint(clickPoint.x + 20, verticalTopY + 10);
                    flag.addPoint(clickPoint.x + 20, verticalTopY - 10);
                    flag.addPoint(clickPoint.x, verticalTopY);

                    shapes.add(new ShapeGroup(rotatedEllipse, horizontalLine, thickVerticalLine, flag));
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
                // 1. 타원
                g2.setColor(Color.BLUE);
                g2.draw(group.getRotatedEllipse());

                // 2. 수평선
                g2.setColor(Color.RED);
                g2.draw(group.getHorizontalLine());

                // 3. 세로선 (두께 있음)
                g2.setColor(Color.GREEN);
                g2.fill(group.getThickVerticalLine());

                // 4. 깃발
                g2.setColor(Color.MAGENTA);
                g2.fill(group.getFlag());
            }
        }

        public void undoLastShape() {
            if (!shapes.isEmpty()) {
                shapes.remove(shapes.size() - 1);
                repaint();
            }
        }
    }

    class ShapeGroup {
        private final Shape rotatedEllipse;
        private final Shape horizontalLine;
        private final Shape thickVerticalLine;
        private final Shape flag;

        public ShapeGroup(Shape rotatedEllipse, Shape horizontalLine, Shape thickVerticalLine, Shape flag) {
            this.rotatedEllipse = rotatedEllipse;
            this.horizontalLine = horizontalLine;
            this.thickVerticalLine = thickVerticalLine;
            this.flag = flag;
        }

        public Shape getRotatedEllipse() {
            return rotatedEllipse;
        }

        public Shape getHorizontalLine() {
            return horizontalLine;
        }

        public Shape getThickVerticalLine() {
            return thickVerticalLine;
        }

        public Shape getFlag() {
            return flag;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OvalDrawingWithUndo example = new OvalDrawingWithUndo();
            example.setVisible(true);
        });
    }
}
