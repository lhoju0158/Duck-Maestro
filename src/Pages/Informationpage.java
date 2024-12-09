package Pages;
import Customs.MyLabel;
import Customs.MyTextField;

import javax.swing.*;
import java.awt.*;

class Informationpage extends JFrame {
    private String title;
    private String composer;
    private int tempo;
    private JTextField titleText;
    private JTextField composerText;
    private JTextField tempoText;
    private Homepage homepage;


    public Informationpage(Homepage homepage) {
        // set container
        setTitle("Information");
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // center
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(4, 1, 10, 10));

        // center components - 1) p1
        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel titleLabel = new MyLabel("  Title:    ");
        titleText = new MyTextField(15);
        p1.add(titleLabel);
        p1.add(titleText);

        // center components - 2) p2
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel composerLabel = new MyLabel("  Composer:    ");
        composerText = new MyTextField(15);
        p2.add(composerLabel);
        p2.add(composerText);

        // center components - 3) p3
        JPanel p3 = new JPanel();
        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel tempoLabel = new MyLabel("  Tempo:    ");
        tempoText = new MyTextField(10);
        p3.add(tempoLabel);
        p3.add(tempoText);

        // center components - 4) buttonPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        JButton createButton = new JButton("Create!");
        createButton.setBackground(new Color(33, 150, 211));
        createButton.setPreferredSize(new Dimension(100, 30));
        createButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        createButton.setForeground(Color.WHITE);
        createButton.setBorder(null);
        buttonPanel.add(createButton);

        center.add(p1);
        center.add(p2);
        center.add(p3);
        center.add(buttonPanel);


        // set variables of Informationpage
        this.homepage = homepage;
        createButton.addActionListener(e -> saveData());

        // set rest components of container
        JPanel North = new JPanel();
        North.setPreferredSize(new Dimension(50, 70));
        JPanel South = new JPanel();
        South.setPreferredSize(new Dimension(50, 35));
        JPanel West = new JPanel();
        West.setPreferredSize(new Dimension(50, 40));
        JPanel East = new JPanel();
        East.setPreferredSize(new Dimension(50, 40));
        add(North, BorderLayout.NORTH);
        add(South, BorderLayout.SOUTH);
        add(West, BorderLayout.WEST);
        add(East, BorderLayout.EAST);
        add(center, BorderLayout.CENTER);

        p1.setOpaque(false);
        p2.setOpaque(false);
        p3.setOpaque(false);
        buttonPanel.setOpaque(false);
        North.setOpaque(false);
        South.setOpaque(false);
        West.setOpaque(false);
        East.setOpaque(false);
        center.setOpaque(false);

        c.setBackground(new Color(33, 150, 211));
        setSize(650, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void saveData() {
        this.title = (titleText.getText() == null || titleText.getText().trim().isEmpty()) ? "Untitled" : titleText.getText();
        this.composer = (composerText.getText() == null || composerText.getText().trim().isEmpty()) ? "Unknown" : composerText.getText();
        try {
            tempo = Integer.parseInt(tempoText.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter numeric values for Tempo.", "Tempo Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int result = JOptionPane.showConfirmDialog(
                this,
                "Title: " + title +
                        "\nComposer: " + composer +
                        "\nTempo: " + tempo,
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            homepage.dispose();
            this.dispose();
            new Scorepage(title, composer, tempo);
        }
    }
}