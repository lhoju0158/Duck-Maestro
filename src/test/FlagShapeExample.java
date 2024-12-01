package test;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.GeneralPath;

public class FlagShapeExample extends JFrame {
    public FlagShapeExample() {
        setTitle("깃발 모양 그리기");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        FlagPanel panel = new FlagPanel();
        add(panel, BorderLayout.CENTER);
    }

    class FlagPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 안티앨리어싱 설정
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // 깃발 모양 그리기
            g2d.setColor(Color.BLUE);
            GeneralPath flag = new GeneralPath();

            // 시작점 설정
            flag.moveTo(100, 300); // 깃대 상단
            flag.lineTo(100, 100); // 깃대 하단

            // 깃발 곡선 생성
            flag.curveTo(200, 100, 250, 200, 300, 100); // 첫 번째 곡선
            flag.curveTo(350, 0, 400, 200, 450, 100);   // 두 번째 곡선
            flag.lineTo(100, 100);                      // 깃대로 닫기

            // 모양 채우기
            g2d.fill(flag);

            // 깃대 그리기
            g2d.setColor(Color.DARK_GRAY);
            g2d.setStroke(new BasicStroke(10));
            g2d.drawLine(100, 100, 100, 400);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlagShapeExample example = new FlagShapeExample();
            example.setVisible(true);
        });
    }
}
