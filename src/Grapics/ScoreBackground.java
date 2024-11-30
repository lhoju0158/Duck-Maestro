package Grapics;

import pages.Scorepage;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ScoreBackground extends JPanel {
    private final Point s1Start = Scorepage.s1Start;
    private final Point s1End = Scorepage.s1End;
    private final Point s2Start = Scorepage.s2Start;
    private final Point s2End = Scorepage.s2End;
    private final int smallGap = Scorepage.smallGap;
    private final int largeGap = Scorepage.largeGap;

    // private final static HashMap<Double, String> nowMelodyHashma = Scorepage.nowMelodyHashmap;
//    private int n;
//    private int m;

    public ScoreBackground(String name, String composer) {
        setLayout(null);
//        this.n = n;
//        this.m = m;

        JLabel titleName = new JLabel(name);
        titleName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
        titleName.setHorizontalAlignment(SwingConstants.CENTER);
        titleName.setBounds(332, 40, 240, 20);
        add(titleName);

        JLabel composerName = new JLabel(composer);
        composerName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        composerName.setHorizontalAlignment(SwingConstants.CENTER);
        composerName.setBounds(670, 75, 120, 15);
        add(composerName);

        // Label이 픽셀이 안깨짐

        JLabel N = new JLabel(String.valueOf(4));
        N.setFont(new Font("Times New Roman", Font.BOLD, 24));
        N.setBounds(85, 132, 50, 30);

        add(N);
        JLabel M = new JLabel(String.valueOf(4));
        M.setFont(new Font("Times New Roman", Font.BOLD, 24));
        M.setBounds(85, 149, 50, 30);

        add(M);
        setOpaque(false);

        // setOpaque(true); // Prevent unnecessary background repainting
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
//            g.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
//            g.drawString(name,342,70);
        g.drawLine(332, 70, 572, 70);
        g.drawLine(670, 95, 790, 95);


        // draw measure
        // Font measureFont = new Font("Times New Roman", Font.BOLD, 24);
        g.setFont(new Font("Times New Roman", Font.BOLD, 24));
        // g.drawString(String.valueOf(n), 74, 136);
        //g.drawString(String.valueOf(m), 74, 154);


        Point tempS1Start = new Point(s1Start);
        Point tempS1End = new Point(s1End);
        Point tempS2Start = new Point(s2Start);
        Point tempS2End = new Point(s2End);
        ImageIcon High = new ImageIcon("./images/high.png");
        Image highImage = High.getImage(); // Convert ImageIcon to Image
        JLabel highLabel = new JLabel(High); // 이거 나중에 JLabel로 고치지 => Image랑


        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 5; j++) {
                g.drawLine(tempS1Start.x, tempS1Start.y + j * smallGap, tempS1End.x, tempS1End.y + j * smallGap);
                g.drawLine(tempS2Start.x, tempS2Start.y + j * smallGap, tempS2End.x, tempS2End.y + j * smallGap);

                if (j != 4) {

                    int measureGap = (tempS1End.x - tempS1Start.x) / 4;
                    // System.out.println("measureGap = "+measureGap);
                    if(i==0){
                        for (int s = 1; s < 4; s++) {
                            g.drawLine(
                                    tempS1Start.x+10 + (measureGap * s),
                                    tempS1Start.y + j * smallGap,
                                    tempS1Start.x+10 + (measureGap * s),
                                    tempS1Start.y + (j + 1) * smallGap

                            );
                            // System.out.println("measureGap = "+measureGap);

                            g.drawLine(
                                    tempS2Start.x-2+ (measureGap * s),
                                    tempS2Start.y + j * smallGap,
                                    tempS2Start.x-2 + (measureGap * s),
                                    tempS2Start.y + (j + 1) * smallGap
                            );
                            // System.out.println("measureGap = "+measureGap);

                        }
                    }
                    else{
                        for (int s = 1; s < 4; s++) {
                            g.drawLine(
                                    tempS1Start.x -2+ (measureGap * s),
                                    tempS1Start.y + j * smallGap,
                                    tempS1Start.x-2 + (measureGap * s),
                                    tempS1Start.y + (j + 1) * smallGap

                            );
                            // System.out.println("measureGap = "+measureGap);

                            g.drawLine(
                                    tempS2Start.x -2+ (measureGap * s),
                                    tempS2Start.y + j * smallGap,
                                    tempS2Start.x-2 + (measureGap * s),
                                    tempS2Start.y + (j + 1) * smallGap
                            );
                            // System.out.println("measureGap = "+measureGap);

                        }
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
            g.drawLine(tempS1.x - 49, tempS1.y + j * smallGap, tempS1.x, tempS1.y + j * smallGap);
            g.drawLine(tempS1.x +716, tempS1.y + j * smallGap, tempS1.x+721, tempS1.y + j * smallGap);
            g.drawLine(tempS2.x - 44, tempS2.y + j * smallGap, tempS2.x, tempS2.y + j * smallGap);
        }
        g.drawImage(highImage, tempS1.x - 42, tempS1.y - 18, 25, 65, this);
        g.drawImage(highImage, tempS2.x - 36, tempS2.y - 18, 25, 65, this);
        tempS1.y += (4 * smallGap + largeGap);
        tempS2.y += (4 * smallGap + largeGap);
        // draw remain lines

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                g.drawLine(tempS1.x - 44, tempS1.y + j * smallGap, tempS1.x, tempS1.y + j * smallGap);
                g.drawLine(tempS2.x - 44, tempS2.y + j * smallGap, tempS2.x, tempS2.y + j * smallGap);
            }

            g.drawImage(highImage, tempS1.x - 36, tempS1.y - 18, 25, 65, this);
            g.drawImage(highImage, tempS2.x - 36, tempS2.y - 18, 25, 65, this);

            tempS1.y += (4 * smallGap + largeGap);
            tempS2.y += (4 * smallGap + largeGap);
        }
        // test code, erase later
//        g.drawLine(94, s1Start.y, s1Start.x, s1Start.y + 32);
//        g.drawLine(904, s2Start.y, s2Start.x, s2Start.y + 32);
//        g.drawLine(810, s1End.y, s1End.x, s1End.y + 32);
//        g.drawLine(1620, s2End.y, s2End.x, s2End.y + 32);
        // System.out.println(s2End); //1620
        // System.out.println(s1Start);
    }
}