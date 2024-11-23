package Section;

import Buttons.SelectButton;
import Buttons.ControlButton;
import pages.Scorepage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;

public class CreateSection extends JPanel{
    private CreateSection1 createSection1 = new CreateSection1();
    protected static JLabel selectedImage;

    public CreateSection(){
        setLayout(null);
        // setBounds(0,0,1690,900);
        createSection1.setBounds(920, 20, 720, 110);
        add(createSection1);

        ImageIcon imageIcon = new ImageIcon("");

        selectedImage = new JLabel(imageIcon);
        selectedImage.setBounds(860, 20, 50, 90);
        selectedImage.setPreferredSize(new Dimension(40,50));
        selectedImage.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        selectedImage.setHorizontalAlignment(SwingConstants.CENTER);
        selectedImage.setVerticalAlignment(SwingConstants.CENTER);
        add(selectedImage);

        setVisible(true);
    }
    public static void whichSelected(String noteOrRests){
        selectedImage.setIcon(new ImageIcon("./images/"+noteOrRests+String.valueOf(CreateSection1.insertBeat)+".png"));
        selectedImage.repaint();
    }
}


class CreateSection1 extends JPanel {
    // private CardLayout cardLayout; // CardLayout 객체
    // private JPanel contentPanel; // 패널 전환을 위한 컨테이너
    // private NotesRestsRepeatMarkSection notesRestsRepeatMarkSection; // 버튼 관리 패널
    // private NoteSection noteSection; // Note 패널

    protected static double insertBeat = -1;
    protected static double insertMelody = 18.0;
    protected static double modifyValue = 0.0;
    // protected static String insertMelody = "";
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
            "4",                // 온음표
            "3",          // 점2분음표
            "2",                 // 2분음표
            "1.5",       // 점4분음표
            "1",              // 4분음표
            "0.75",        // 점8분음표
            "0.5",               // 8분음표
            "0.375",     // 점16분음표
            "0.25",            // 16분음표
    };

    private String[] melodTexts = {
            "G3", // 솔
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
            "G5", // 솔
            "Rest"
    };
    private String[] accidentalTexts = {"Flat", "Sharp", "Natural"};
    private SelectButton[] Beatbuttons = new SelectButton[9];
    private SelectButton[] Melodybuttons = new SelectButton[16];
    protected static SelectButton[] Accidentalbuttons = new SelectButton[3];
    // protected static String imagePath = "";
    // protected static JLabel imageLabel;

    public CreateSection1() {
        setLayout(new BorderLayout(20,0));
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(3,1,3,0)); // 사용자 지정 레이아웃 사용
        JLabel beat = new JLabel("Beat:");
        JLabel melody = new JLabel("Melody:");
        JLabel accidental = new JLabel("Accidentals:");
        Font labelFont = new Font("Roboto", Font.BOLD, 13);
        beat.setFont(labelFont); // 폰트 설정
        melody.setFont(labelFont); // 폰트 설정
        accidental.setFont(labelFont); // 폰트 설정

        JPanel beats = new JPanel();

        beats.add(beat);
        beats.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup beatComponents = new ButtonGroup();

        for (int i = 0; i < Beatbuttons.length; i++) {
            Beatbuttons[i] = new SelectButton(beatTexts[i]);
            beatComponents.add(Beatbuttons[i]);
            beats.add(Beatbuttons[i]);
            Beatbuttons[i].addItemListener(new BeatListener());
        }

//        ImageIcon selectedImage = new ImageIcon("");
//        imageLabel = new JLabel(selectedImage);
//        imageLabel.setPreferredSize(new Dimension(40, 18));
//
//        beats.add(imageLabel);




        JPanel melodies = new JPanel();

        melodies.add(melody);
        melodies.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup melodyComponent = new ButtonGroup();

        for (int i = 0; i < Melodybuttons.length; i++) {
            Melodybuttons[i] = new SelectButton(melodTexts[i]);
            melodyComponent.add(Melodybuttons[i]);
            melodies.add(Melodybuttons[i]);
            Melodybuttons[i].addItemListener(new MelodyListener());
        }


        JPanel accidentalsAndRemarks = new JPanel();
        // accidentals.setBounds(0,600,1690,900);

        accidentalsAndRemarks.add(accidental);
        accidentalsAndRemarks.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup accidentalComponent = new ButtonGroup();

        for (int i = 0; i < Accidentalbuttons.length; i++) {
            Accidentalbuttons[i] = new SelectButton(accidentalTexts[i]);
            accidentalComponent.add(Accidentalbuttons[i]);
            accidentalsAndRemarks.add(Accidentalbuttons[i]);
            Accidentalbuttons[i].addItemListener(new AccidentalListener());
        }

        p1.add(beats);
        p1.add(melodies);
        p1.add(accidentalsAndRemarks);
        add(p1,BorderLayout.WEST);

        JPanel p2 = new JPanel();
        p2.setLayout(new GridLayout(3,1,0,10));
        ControlButton Create = new ControlButton("Insert",Color.GREEN);


        p2.add(Create);
        ControlButton Insert = new ControlButton("Undo",Color.BLUE);
        p2.add(Insert);
        JLabel result = new JLabel("Result");
        result.setFont(new Font("Roboto", Font.PLAIN, 12));
        result.setHorizontalAlignment(SwingConstants.CENTER);
        result.setVerticalAlignment(SwingConstants.NORTH);
        p2.add(result);

        add(p2,BorderLayout.CENTER);
        setOpaque(true);
        setVisible(true);


        Create.addActionListener(e -> {
            Scorepage.insertBeat = insertBeat;
            Scorepage.insertMelody = insertMelody + modifyValue;
            System.out.println("Scorepage.insertBeat = "+Scorepage.insertBeat+", Scorepage.insertMelody = "+Scorepage.insertMelody);
            // create 이후에 Score에서 판단하기
            if(Scorepage.Checking()){
                result.setText("OK!");
                result.setForeground(Color.green);
            }
            else{
                result.setText("Do Again!");
                result.setForeground(Color.RED);
            }
        });

    }


}


class BeatListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            CreateSection1.insertBeat = Double.parseDouble(button.getText()) / 4;
            if(CreateSection1.insertMelody==15.5||CreateSection1.insertMelody==16.0||CreateSection1.insertMelody==16.5){
                CreateSection.whichSelected("rests/rest");
                CreateSection1.Accidentalbuttons[2].setSelected(true);
                CreateSection1.Accidentalbuttons[2].repaint();

            }
            else{
                CreateSection.whichSelected("notes/note");
            }
            // System.out.println("Scorepage.insertBeat = "+Scorepage.insertBeat);
            // System.out.println("CreateSection.insertBeat = "+CreateSection.insertBeat);
            // String stringinsertBeat
            // CreateSection.imagePath = "./images/notes/note"+String.valueOf(CreateSection.insertBeat)+".png";
            // CreateSection.imageLabel.setIcon(new ImageIcon("./images/notes/note"+String.valueOf(CreateSection.insertBeat)+".png"));
            // CreateSection.imageLabel.repaint();
            // .out.println(CreateSection.imagePath);
        }
    }
}
class MelodyListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            CreateSection1.insertMelody = CreateSection1.melodyHashmap.get(button.getText());
            if(CreateSection1.insertMelody==15.5||CreateSection1.insertMelody==16.0||CreateSection1.insertMelody==16.5){
                CreateSection.whichSelected("rests/rest");
                CreateSection1.Accidentalbuttons[2].setSelected(true);

                CreateSection1.Accidentalbuttons[2].repaint();

            }
            else{
                CreateSection.whichSelected("notes/note");
            }
            // System.out.println("CreateSection.insertMelody = "+CreateSection.insertMelody);
        }
    }
}
class AccidentalListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if(button.getText().equals("Flat")){
                CreateSection1.modifyValue = -0.5;
            }
            else if(button.getText().equals("Sharp")){
                CreateSection1.modifyValue = +0.5;

            }
            else if(button.getText().equals("Natural")){
                CreateSection1.modifyValue = 0.0;
            }
        }
    }
}