package Section;

import Buttons.SelectButton;
import Buttons.ControlButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CreateSection extends JPanel {
    // private CardLayout cardLayout; // CardLayout 객체
    // private JPanel contentPanel; // 패널 전환을 위한 컨테이너
    // private NotesRestsRepeatMarkSection notesRestsRepeatMarkSection; // 버튼 관리 패널
    // private NoteSection noteSection; // Note 패널

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
    private SelectButton[] Accidentalbuttons = new SelectButton[3];

    public CreateSection() {
        setLayout(new BorderLayout(30,0));
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
        // beats.setBounds(0,0,1690,900);

        beats.add(beat);
        beats.setLayout(new FlowLayout(FlowLayout.LEFT, 7, 0));
        ButtonGroup beatComponents = new ButtonGroup();

        for (int i = 0; i < Beatbuttons.length; i++) {
            Beatbuttons[i] = new SelectButton(beatTexts[i]);
            beatComponents.add(Beatbuttons[i]);
            beats.add(Beatbuttons[i]);
            Beatbuttons[i].addItemListener(new BeatListener());
        }

        JPanel melodies = new JPanel();
        // melodies.setBounds(0,300,1690,900);

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
        // SelectButton createRemark = new SelectButton("Create Remark");
        // accidentalsAndRemarks.add(createRemark);


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

//        // NotesRestsRepeatMarkSection 생성 및 설정
//        notesRestsRepeatMarkSection = new NotesRestsRepeatMarkSection();
//        notesRestsRepeatMarkSection.setBounds(850, 20, 1690, 40);
//        add(notesRestsRepeatMarkSection);
//
//        // 리스너 등록
//        notesRestsRepeatMarkSection.setButtonSelectionListener(buttonText -> {
//            if (buttonText != null) {
//                // 패널 전환 전 상태 초기화
//                resetAllPanels();
//                cardLayout.show(contentPanel, buttonText);
//            } else {
//                resetAllPanels(); // 버튼 선택 해제 시 모든 패널 초기화
//                cardLayout.show(contentPanel, "Empty");
//            }
//        });
//
//        // CardLayout과 각 패널 설정
//        cardLayout = new CardLayout();
//        contentPanel = new JPanel(cardLayout);
//        contentPanel.setBounds(850, 70, 500, 50);
//
//        // 개별 패널 생성 및 추가
//        noteSection = new NoteSection();
//        JPanel restsPanel = new JPanel();
//        restsPanel.setBackground(Color.PINK);
//        restsPanel.add(new JLabel("Rests Panel"));
//
//        JPanel repeatPanel = new JPanel();
//        repeatPanel.setBackground(Color.LIGHT_GRAY);
//        repeatPanel.add(new JLabel("Repeat Mark Panel"));
//
//        JPanel emptyPanel = new JPanel(); // 아무것도 없는 기본 빈 패널
//
//        // CardLayout에 패널 추가
//        contentPanel.add(emptyPanel, "Empty"); // 빈 패널 추가
//        contentPanel.add(noteSection, "Notes");
//        contentPanel.add(restsPanel, "Rests");
//        contentPanel.add(repeatPanel, "Repeat Mark");
//
//        cardLayout.show(contentPanel, "Empty"); // 초기 상태로 빈 패널 표시
//        add(contentPanel);
    }

//    private void resetAllPanels() {
//        notesRestsRepeatMarkSection.resetSelection(); // NotesRestsRepeatMarkSection 초기화
//        noteSection.resetSelection(); // NoteSection 초기화
//        // 다른 패널 초기화도 필요하면 추가
//    }
}

class BeatListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(button.getText() + " is SELECTED.");
        }
    }
}
class MelodyListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(button.getText() + " is SELECTED.");
        }
    }
}
class AccidentalListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        JToggleButton button = (JToggleButton) e.getSource();
        if (e.getStateChange() == ItemEvent.SELECTED) {
            System.out.println(button.getText() + " is SELECTED.");
        }
    }
}