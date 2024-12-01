package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RotatingImage extends JFrame {
    private Timer rotationTimer;
    private double angle = 0; // 회전 각도
    private boolean isRunning = false;

    public RotatingImage() {
        setTitle("이미지 회전 예제");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 이미지가 회전하는 패널
        RotatingPanel rotatingPanel = new RotatingPanel();
        add(rotatingPanel, BorderLayout.CENTER);

        // 버튼 패널
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");

        // Start 버튼 이벤트
        startButton.addActionListener(e -> {
            if (!isRunning) {
                isRunning = true;
                rotationTimer.start();
            }
        });

        // Stop 버튼 이벤트
        stopButton.addActionListener(e -> {
            if (isRunning) {
                isRunning = false;
                rotationTimer.stop();
            }
        });

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // 타이머 설정
        rotationTimer = new Timer(100, e -> {
            angle += 1; // 1도씩 증가
            if (angle >= 360) {
                angle -= 360; // 360도를 넘지 않도록 제한
            }
            rotatingPanel.setAngle(angle); // 회전 각도 업데이트
        });
    }

    class RotatingPanel extends JPanel {
        private Image image;
        private double angle = 0;

        public RotatingPanel() {
            // 샘플 이미지 로드 (대체 이미지를 설정)
            image = Toolkit.getDefaultToolkit().getImage("./images/lp.png");
        }

        public void setAngle(double angle) {
            this.angle = angle;
            repaint();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;

            // 이미지 회전 및 렌더링
            if (image != null) {
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                int imgWidth = image.getWidth(this);
                int imgHeight = image.getHeight(this);

                AffineTransform transform = new AffineTransform();
                transform.translate(centerX - imgWidth / 2, centerY - imgHeight / 2);
                transform.rotate(Math.toRadians(angle), imgWidth / 2.0, imgHeight / 2.0);

                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2d.drawImage(image, transform, this);
            } else {
                g2d.drawString("이미지를 찾을 수 없습니다.", getWidth() / 2 - 50, getHeight() / 2);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RotatingImage frame = new RotatingImage();
            frame.setVisible(true);
        });
    }
}
