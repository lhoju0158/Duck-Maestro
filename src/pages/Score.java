package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Score extends JFrame {
//    private String name;
//    private String composer;

    private float measure;
//    private int n;
//    private int m;

    public Score(String name, String composer, int n, int m) {
        // Set basic variables
//        this.name = name;
//        this.composer = composer;
        this.measure = (float) n / (float) m;

        // Set frame
        setTitle("Score");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1670, 900);
        setLayout(null);

        Container c = getContentPane();

        // Create Line background panel
        ScoreBackground scoreBackground = new ScoreBackground(name, composer, n, m);
        scoreBackground.setBounds(0, 0, 1700, 900); // Line panel 크기 설정
        scoreBackground.setLayout(null); // Allow absolute positioning for child components
        scoreBackground.setOpaque(false);
        c.add(scoreBackground);


        // Add draggable ImageIcon on Line background

        ComponentLabel.createSection(c);
//        ImageIcon icon = new ImageIcon("./images/high (25).png"); // 여기에 이미지 경로 설정
//        ComponentSection draggableLabel = new ComponentSection(icon);
//        draggableLabel.setBounds(100, 150, icon.getIconWidth(), icon.getIconHeight()); // 이미지 크기로 설정
//        c.add(draggableLabel);
//        ComponentSection draggableLabel2 = new ComponentSection(icon);
//        draggableLabel2.setBounds(800, 150, icon.getIconWidth(), icon.getIconHeight()); // 이미지 크기로 설정
//        c.add(draggableLabel2);


        c.setComponentZOrder(scoreBackground, c.getComponentCount() - 2);
        // 이후 음표, 쉼표들이 위에 있을 예정

        // c.setComponentZOrder(draggableLabel, 0); // draggableLabel을 가장 위에 설정

        // Make everything visible
        setVisible(true);
    }

    public static void main(String[] args) {
        new Score("Drama", "IU", 3, 4);
    }


}