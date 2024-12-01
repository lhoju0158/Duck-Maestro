package Sections;

import Buttons.SelectButton;
import Buttons.ControlButton;
import Pages.Scorepage;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class CreateSection extends JPanel{
    private CreateSection1 createSection1 = new CreateSection1();
    protected static JLabel selectedImage;

    public CreateSection(){
        // set panel
        setLayout(null);

        // CreateSection components - 1) createSection 1
        createSection1.setBounds(920, 20, 720, 110);
        add(createSection1);

        // CreateSection components - 2) selectedImage
        ImageIcon imageIcon = new ImageIcon("");
        selectedImage = new JLabel(imageIcon);
        selectedImage.setBounds(860, 20, 50, 90);
        selectedImage.setPreferredSize(new Dimension(40,50));
        selectedImage.setBorder(new LineBorder(Color.black, 2));
        selectedImage.setHorizontalAlignment(SwingConstants.CENTER);
        selectedImage.setVerticalAlignment(SwingConstants.CENTER);
        add(selectedImage);

        setOpaque(true);
        setVisible(true);
    }
    public static void whichSelected(String noteOrRests){
        selectedImage.setIcon(new ImageIcon("./images/"+noteOrRests+String.valueOf(CreateSection1.insertBeat)+".png"));
        selectedImage.repaint();
    }
}


class CreateSection1 extends JPanel {
    protected static double insertBeat = -1;
    protected static double insertMelody = 18.0;
    protected static double modifyValue = 0.0;
    protected static HashMap<String, Double> melodyHashmap = new HashMap<String, Double>() {{
        put("G3", 0.0);
        put("A3", 1.0);
        put("B3", 2.0);
        put("C4", 3.0);
        put("D4", 4.0);
        put("E4", 5.0);
        put("F4", 6.0);
        put("G4", 7.0);
        put("A4", 8.0);
        put("B4", 9.0);
        put("C5", 10.0);
        put("D5", 11.0);
        put("E5", 12.0);
        put("F5", 13.0);
        put("G5", 14.0);
        put("Rest", 16.0);
    }};

    private String[] beatTexts = {
            "4",
            "3",
            "2",
            "1.5",
            "1",
            "0.75",
            "0.5",
            "0.375",
            "0.25",
    };

    private String[] melodTexts = {
            "G3",
            "A3",
            "B3",
            "C4",
            "D4",
            "E4",
            "F4",
            "G4",
            "A4",
            "B4",
            "C5",
            "D5",
            "E5",
            "F5",
            "G5",
            "Rest"
    };
    private String[] accidentalTexts = {"Flat", "Sharp", "Natural"};
    private SelectButton[] Beatbuttons = new SelectButton[9];
    private SelectButton[] Melodybuttons = new SelectButton[16];
    protected static SelectButton[] Accidentalbuttons = new SelectButton[3];

    public CreateSection1() {
        // set panel
        setLayout(new BorderLayout(20,0));

        // CreateSection1 components - 1) p1
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3,1,3,0));

        Font labelFont = new Font("Roboto", Font.BOLD, 13);
        // p1 components -1) beats
        JPanel beats = new JPanel();
        JLabel beat = new JLabel("Beat:");
        beat.setFont(labelFont);

        beats.add(beat);
        beats.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup beatComponents = new ButtonGroup();

        for (int i = 0; i < Beatbuttons.length; i++) {
            Beatbuttons[i] = new SelectButton(beatTexts[i]);
            beatComponents.add(Beatbuttons[i]);
            beats.add(Beatbuttons[i]);
            Beatbuttons[i].addItemListener(new BeatListener());
        }

        // p1 components - 2) melodies
        JPanel melodies = new JPanel();
        JLabel melody = new JLabel("Melody:");
        melody.setFont(labelFont);

        melodies.add(melody);
        melodies.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup melodyComponent = new ButtonGroup();

        for (int i = 0; i < Melodybuttons.length; i++) {
            Melodybuttons[i] = new SelectButton(melodTexts[i]);
            melodyComponent.add(Melodybuttons[i]);
            melodies.add(Melodybuttons[i]);
            Melodybuttons[i].addItemListener(new MelodyListener());
        }

        // p1 components - 3) accidentals
        JPanel accidentals = new JPanel();
        JLabel accidental = new JLabel("Accidentals:");
        accidental.setFont(labelFont);

        accidentals.add(accidental);
        accidentals.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup accidentalComponent = new ButtonGroup();

        for (int i = 0; i < Accidentalbuttons.length; i++) {
            Accidentalbuttons[i] = new SelectButton(accidentalTexts[i]);
            accidentalComponent.add(Accidentalbuttons[i]);
            accidentals.add(Accidentalbuttons[i]);
            Accidentalbuttons[i].addItemListener(new AccidentalListener());
        }

        // set p1
        p1.add(beats);
        p1.add(melodies);
        p1.add(accidentals);

        // CreateSection1 components - 2) p2
        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3,1,0,10));

        // p2 components - 1) Insert
        ControlButton Insert = new ControlButton("Insert",Color.GREEN);

        // p2 components - 2) Undo
        ControlButton Undo = new ControlButton("Undo",Color.BLUE);

        // p2 components - 3) result
        JLabel result = new JLabel("Result");
        result.setFont(new Font("Roboto", Font.PLAIN, 12));
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setVerticalAlignment(SwingConstants.NORTH);

        // set p2
        p2.add(Insert);
        p2.add(Undo);
        p2.add(result);

        // set penel
        add(p1,BorderLayout.WEST);
        add(p2,BorderLayout.CENTER);
        setOpaque(true);
        setVisible(true);


        // Insert onClickHandler
        Insert.addActionListener(e -> {
            Scorepage.insertMelodyForDraw = new double[]{insertMelody, modifyValue};
            Scorepage.insertBeat = insertBeat;
            Scorepage.insertMelodyForPlay = insertMelody + modifyValue;
            if (Scorepage.Checking()) {
                result.setText("OK!");
                result.setForeground(Color.GREEN);
            } else {
                result.setText("Do Again!");
                result.setForeground(Color.RED);
            }

        });

        // Undo onClickHandler
        Undo.addActionListener(e -> {
            if(Scorepage.undoLastElement()){
                result.setText("Undo!");
                result.setForeground(Color.BLUE);
            }
            else{
                result.setText("Nothing!");
                result.setForeground(Color.RED);
            }
        });
    }
}


class BeatListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource(); //https://blog.naver.com/kittenjun/10044445481
        if (e.getStateChange() == ItemEvent.SELECTED) {
            CreateSection1.insertBeat = Double.parseDouble(button.getText()) / 4;
            if(CreateSection1.insertMelody==16.0){
                CreateSection.whichSelected("rests/rest");
                CreateSection1.modifyValue = 0.0;
                CreateSection1.Accidentalbuttons[2].setSelected(true);
                CreateSection1.Accidentalbuttons[2].repaint();

            }
            else{
                CreateSection.whichSelected("notes/note");
            }
        }
    }
}
class MelodyListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            CreateSection1.insertMelody = CreateSection1.melodyHashmap.get(button.getText());
            if(CreateSection1.insertMelody==16.0){
                CreateSection.whichSelected("rests/rest");
                CreateSection1.modifyValue = 0.0;
                CreateSection1.Accidentalbuttons[2].setSelected(true);
                CreateSection1.Accidentalbuttons[2].repaint();
            }
            else{
                CreateSection.whichSelected("notes/note");
            }
        }
    }
}
class AccidentalListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if(button.getText().equals("Flat")){
                if(CreateSection1.insertMelody==16.0){
                    CreateSection1.modifyValue = 0.0;
                    CreateSection1.Accidentalbuttons[2].setSelected(true);
                    CreateSection1.Accidentalbuttons[2].repaint();
                }
                else{
                    CreateSection1.modifyValue = -0.5;
                }
            }
            else if(button.getText().equals("Sharp")){
                if(CreateSection1.insertMelody==16.0){
                    CreateSection1.modifyValue = 0.0;
                    CreateSection1.Accidentalbuttons[2].setSelected(true);
                    CreateSection1.Accidentalbuttons[2].repaint();
                }
                else{
                    CreateSection1.modifyValue = +0.5;
                }

            }
            else if(button.getText().equals("Natural")){
                CreateSection1.modifyValue = 0.0;
            }
        }
    }
}