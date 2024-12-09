package Grapics;

import Pages.Scorepage;

import javax.swing.*;
import java.awt.*;

public class ScoreBackground extends JPanel {
    private final Point s1Start = Scorepage.s1Start;
    private final Point s1End = Scorepage.s1End;
    private final Point s2Start = Scorepage.s2Start;
    private final Point s2End = Scorepage.s2End;
    private final int smallGap = Scorepage.smallGap;
    private final int largeGap = Scorepage.largeGap;

    public ScoreBackground(String name, String composer) {
        // set panel
        setLayout(null);

        // ScoreBackground components - 1) titleName
        JLabel titleName = new JLabel(name);
        titleName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
        titleName.setHorizontalAlignment(SwingConstants.CENTER);
        titleName.setBounds(332, 40, 240, 20);

        // ScoreBackground components - 2) composerName
        JLabel composerName = new JLabel(composer);
        composerName.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
        composerName.setHorizontalAlignment(SwingConstants.CENTER);
        composerName.setBounds(670, 75, 120, 15);

        // ScoreBackground components - 3) N and M
        JLabel N = new JLabel(String.valueOf(4));
        N.setFont(new Font("Times New Roman", Font.BOLD, 24));
        N.setBounds(85, 132, 50, 30);
        JLabel M = new JLabel(String.valueOf(4));
        M.setFont(new Font("Times New Roman", Font.BOLD, 24));
        M.setBounds(85, 149, 50, 30);

        add(titleName);
        add(composerName);
        add(N);
        add(M);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.drawLine(332, 70, 572, 70);
        g.drawLine(670, 95, 790, 95);

        g.setFont(new Font("Times New Roman", Font.BOLD, 24));

        Point tempS1Start = new Point(s1Start);
        Point tempS1End = new Point(s1End);
        Point tempS2Start = new Point(s2Start);
        Point tempS2End = new Point(s2End);
        ImageIcon High = new ImageIcon("./images/high.png");
        Image highImage = High.getImage();


        for (int i = 0; i < 8; i++) {

            for (int j = 0; j < 5; j++) {
                g.drawLine(tempS1Start.x, tempS1Start.y + j * smallGap, tempS1End.x, tempS1End.y + j * smallGap);
                g.drawLine(tempS2Start.x, tempS2Start.y + j * smallGap, tempS2End.x, tempS2End.y + j * smallGap);

                if (j != 4) {

                    int measureGap = (tempS1End.x - tempS1Start.x) / 4;
                    if(i==0){
                        for (int s = 1; s < 4; s++) {
                            g.drawLine(
                                    tempS1Start.x+10 + (measureGap * s),
                                    tempS1Start.y + j * smallGap,
                                    tempS1Start.x+10 + (measureGap * s),
                                    tempS1Start.y + (j + 1) * smallGap

                            );
                            g.drawLine(
                                    tempS2Start.x-2+ (measureGap * s),
                                    tempS2Start.y + j * smallGap,
                                    tempS2Start.x-2 + (measureGap * s),
                                    tempS2Start.y + (j + 1) * smallGap
                            );
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

                            g.drawLine(
                                    tempS2Start.x -2+ (measureGap * s),
                                    tempS2Start.y + j * smallGap,
                                    tempS2Start.x-2 + (measureGap * s),
                                    tempS2Start.y + (j + 1) * smallGap
                            );

                        }
                    }

                }
            }

            tempS1Start.y += (4 * smallGap + largeGap);
            tempS1End.y += (4 * smallGap + largeGap);
            tempS2Start.y += (4 * smallGap + largeGap);
            tempS2End.y += (4 * smallGap + largeGap);
        }
        Point tempS1 = new Point(s1Start);
        Point tempS2 = new Point(s2Start);

        for (int j = 0; j < 5; j++) {
            g.drawLine(tempS1.x - 49, tempS1.y + j * smallGap, tempS1.x, tempS1.y + j * smallGap);
            g.drawLine(tempS1.x +716, tempS1.y + j * smallGap, tempS1.x+721, tempS1.y + j * smallGap);
            g.drawLine(tempS2.x - 44, tempS2.y + j * smallGap, tempS2.x, tempS2.y + j * smallGap);
        }
        g.drawImage(highImage, tempS1.x - 42, tempS1.y - 18, 25, 65, this);
        g.drawImage(highImage, tempS2.x - 36, tempS2.y - 18, 25, 65, this);
        tempS1.y += (4 * smallGap + largeGap);
        tempS2.y += (4 * smallGap + largeGap);

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
    }
}