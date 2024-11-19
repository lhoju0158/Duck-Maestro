package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame {
    private String name;
    private String composer;

    private float measure;
    private int n;
    private int m;

    public Score(String name, String composer, int n, int m) {
        // Set basic variables
        this.name = name;
        this.composer = composer;
        this.n = n;
        this.m = m;
        this.measure = (float) n / (float) m;

        // Set frame
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1670, 900);
        setLayout(null);

        Container c = getContentPane();

        // Create Line background panel
        ScoreBackground scoreBackground = new ScoreBackground();
        scoreBackground.setBounds(0, 0, 1700, 900); // Line panel 크기 설정
        scoreBackground.setLayout(null); // Allow absolute positioning for child components
        scoreBackground.setOpaque(false);
        c.add(scoreBackground);

        // Add titleName JLabel on Line background
//        JLabel titleName = new JLabel(name);
//        titleName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
//        titleName.setHorizontalAlignment(SwingConstants.CENTER);
//        titleName.setBounds(10, 50, 300, 50); // 위치와 크기 설정
//        line.add(titleName);

        // Add draggable ImageIcon on Line background
        ImageIcon icon = new ImageIcon("./images/high (25).png"); // 여기에 이미지 경로 설정
        DraggableLabel draggableLabel = new DraggableLabel(icon);
        draggableLabel.setBounds(100, 150, icon.getIconWidth(), icon.getIconHeight()); // 이미지 크기로 설정
        c.add(draggableLabel);
        c.setComponentZOrder(scoreBackground, c.getComponentCount() - 1);
        c.setComponentZOrder(draggableLabel, 0); // draggableLabel을 가장 위에 설정

        // Make everything visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Score("Drama", "IU", 3, 4);
    }

    // Background Line panel class
    class ScoreBackground extends JPanel {
        private Point s1Start = new Point(94, 120);
        private Point s1End = new Point(810, 120);
        private Point s2Start = new Point(904, 120);
        private Point s2End = new Point(1620, 120);
        private int smallGap = 8;
        private int largeGap = 50;

        public ScoreBackground() {
            JLabel titleName = new JLabel(name);
            titleName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
            titleName.setHorizontalAlignment(SwingConstants.CENTER);
            titleName.setBounds(332,40, 240, 20);

            add(titleName);
            JLabel composerName = new JLabel(composer);
            composerName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
            composerName.setHorizontalAlignment(SwingConstants.CENTER);
            composerName.setBounds(670,75, 120, 15);
            add(composerName);


            setOpaque(true); // Prevent unnecessary background repainting
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLACK);
//            g.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
//            g.drawString(name,342,70);
            g.drawLine(332,70,572,70);

            g.drawLine(670,95,790,95);


            // draw measure
            // Font measureFont = new Font("Times New Roman", Font.BOLD, 24);
            g.setFont(new Font("Times New Roman", Font.BOLD, 24));
            g.drawString(String.valueOf(n),74,136);
            g.drawString(String.valueOf(m),74,154);



            Point tempS1Start = new Point(s1Start);
            Point tempS1End = new Point(s1End);
            Point tempS2Start = new Point(s2Start);
            Point tempS2End = new Point(s2End);
            ImageIcon High = new ImageIcon("./images/high.png");
            Image highImage = High.getImage(); // Convert ImageIcon to Image
            for (int i = 0; i < 9; i++) {
                // temp line for check => erase later
                g.drawLine(tempS1Start.x, tempS1Start.y, tempS1Start.x, tempS1Start.y+32);
                g.drawLine(tempS2Start.x, tempS2Start.y, tempS2Start.x, tempS2Start.y+32);
                //

                for (int j = 0; j < 5; j++) {
                    g.drawLine(tempS1Start.x, tempS1Start.y + j * smallGap, tempS1End.x, tempS1End.y + j * smallGap);
                    g.drawLine(tempS2Start.x, tempS2Start.y + j * smallGap, tempS2End.x, tempS2End.y + j * smallGap);



                    if (j != 4) {
                        int measureGap = (tempS1End.x - tempS1Start.x) / 4;
                        for (int s = 1; s < 4; s++) {
                            g.drawLine(
                                    tempS1Start.x + (measureGap * s),
                                    tempS1Start.y + j * smallGap,
                                    tempS1Start.x + (measureGap * s),
                                    tempS1Start.y + (j + 1) * smallGap
                            );
                            g.drawLine(
                                    tempS2Start.x + (measureGap * s),
                                    tempS2Start.y + j * smallGap,
                                    tempS2Start.x + (measureGap * s),
                                    tempS2Start.y + (j + 1) * smallGap
                            );
                        }
                    }
                }

                tempS1Start.y += (4 * smallGap + largeGap);
                tempS1End.y += (4 * smallGap + largeGap);
                tempS2Start.y += (4 * smallGap + largeGap);
                tempS2End.y += (4 * smallGap + largeGap);
            }
            // for first measure
            Point tempS1 = new Point(s1Start);
            Point tempS2 = new Point(s2Start);

            // draw first line
            for (int j = 0; j < 5; j++) {
                g.drawLine(tempS1.x - 56, tempS1.y + j * smallGap, tempS1.x, tempS1.y + j * smallGap);
                g.drawLine(tempS2.x - 44, tempS2.y + j * smallGap, tempS2.x, tempS2.y + j * smallGap);
            }
            g.drawImage(highImage,tempS1.x-52,tempS1.y-15,25,65,this);
            g.drawImage(highImage,tempS2.x-36,tempS2.y-15,25,65,this);
            tempS1.y += (4 * smallGap + largeGap);
            tempS2.y += (4 * smallGap + largeGap);
            // draw remain lines

            for(int i =0;i<8;i++) {
                for (int j = 0; j < 5; j++) {
                    g.drawLine(tempS1.x - 44, tempS1.y + j * smallGap, tempS1.x, tempS1.y + j * smallGap);
                    g.drawLine(tempS2.x - 44, tempS2.y + j * smallGap, tempS2.x, tempS2.y + j * smallGap);
                }

                    g.drawImage(highImage,tempS1.x-36,tempS1.y-15,25,65,this);
                    g.drawImage(highImage,tempS2.x-36,tempS2.y-15,25,65,this);

                tempS1.y += (4 * smallGap + largeGap);
                tempS2.y += (4 * smallGap + largeGap);
            }



        }
    }

    // Separate DraggableLabel class
    class DraggableLabel extends JLabel {
        private Point initialClick;

        public DraggableLabel(ImageIcon icon) {

            super(icon); // Set the image as an icon for the label
            setOpaque(false); // Make label background transparent to show the image only

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
    }}