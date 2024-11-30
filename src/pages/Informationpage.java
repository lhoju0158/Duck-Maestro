package pages;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.border.Border;

class Informationpage extends JFrame {
    private String title;
    private String composer;
//    private int n;
//    private int m;
    private int tempo;

    // 텍스트 필드를 클래스 필드로 선언
    private JTextField titleText;
    private JTextField composerText;
    private JTextField nText;
    private JTextField mText;
    private JTextField tempoText;
    private Homepage homepage;
//    private MyComboBoxMelody melody1;
//    private MyComboBoxMelody melody2;
//    private MyComboBoxMelody melody3;
//
//    private MyComboBoxAccidental accidental1;
//    private MyComboBoxAccidental accidental2;
//    private MyComboBoxAccidental accidental3;

//    protected static HashMap<String, Double> melodyHashmap = new HashMap<String, Double>() {{
//        put("", -1.0);
//        put("G3", 0.0);
//        put("A3", 1.0);
//        put("B3", 2.0);
//        put("C4", 3.0);
//        put("D4", 4.0);
//        put("E4", 5.0);
//        put("F4", 6.0);
//        put("G4", 7.0);
//        put("A4", 8.0);
//        put("B4", 9.0);
//        put("C5", 10.0);
//        put("D5", 11.0);
//        put("E5", 12.0);
//        put("F5", 13.0);
//        put("G5", 14.0);
//        // put("Rest", 16.0);
//    }};
//    protected static String[] keySignature = new String[]{"","C","D","E","F","G","A","B"};
//    protected static HashMap<String, Double> accidentalHashmap = new HashMap<String, Double>() {{
//        put("", 0.0);
//        put("Flat", -0.5);
//        put("Sharp", +0.5);
//    }};
    // ArrayList<String> accidentalsForPrint = new ArrayList<>();
    // ArrayList<double[]> accidentals = new ArrayList<>();


    public Informationpage(Homepage homepage) {
        this.homepage = homepage;
        setTitle("Information");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Container c = getContentPane();

        c.setLayout(new BorderLayout());
        JPanel Informations = new JPanel();
        Informations.setLayout(new GridLayout(4, 1, 10, 10));

        JPanel p1 = new JPanel();
        p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel titleLabel = new MyLabel("  Title: ");
        titleText = new MyTextField(10); // 필드 변수로 초기화
        p1.add(titleLabel);
        p1.add(titleText);

        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel composerLabel = new MyLabel("  Composer: ");
        composerText = new MyTextField(10); // 필드 변수로 초기화
        p2.add(composerLabel);
        p2.add(composerText);
//
//        JPanel p3 = new JPanel();
//        p3.setLayout(new FlowLayout(FlowLayout.LEFT));
//        MyLabel timesLabel = new MyLabel("  Times: ");
//        nText = new MyTextField(5); // 필드 변수로 초기화
//        MyLabel slashLabel = new MyLabel("  /  ");
//        mText = new MyTextField(5); // 필드 변수로 초기화
//        p3.add(timesLabel);
//        p3.add(nText);
//        p3.add(slashLabel);
//        p3.add(mText);

        JPanel p4 = new JPanel();
        p4.setLayout(new FlowLayout(FlowLayout.LEFT));
        MyLabel tempoLabel = new MyLabel("  Tempo: ");
        tempoText = new MyTextField(10); // 필드 변수로 초기화
        p4.add(tempoLabel);
        p4.add(tempoText);

//        JPanel p5 = new JPanel();
//        p5.setLayout(new FlowLayout(FlowLayout.LEFT));
//        MyLabel accidentalLabel = new MyLabel("  Key Signatures: ");
////
//        melody1 = new MyComboBoxMelody();
//        accidental1 = new MyComboBoxAccidental();
//        melody2 = new MyComboBoxMelody();
//        accidental2 = new MyComboBoxAccidental();
//        melody3 = new MyComboBoxMelody();
//        accidental3 = new MyComboBoxAccidental();
//
//        p5.add(accidentalLabel);
//        p5.add(melody1);
//        p5.add(accidental1);
//        p5.add(new MyLabel(" / "));
//        p5.add(melody2);
//        p5.add(accidental2);
//        p5.add(new MyLabel(" / "));
//        p5.add(melody3);
//        p5.add(accidental3);

        JPanel p6 = new JPanel();
        p6.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton createButton = new JButton("Create!");
        createButton.setBackground(new Color(33, 150, 211));
        createButton.setPreferredSize(new Dimension(100, 30));
        createButton.setFont(new Font("Century Gothic", Font.BOLD, 20));
        createButton.setForeground(Color.WHITE);
        createButton.setBorder(null);

        // 버튼 클릭 시 saveData() 호출
        createButton.addActionListener(e -> saveData());

        p6.add(createButton);

        Informations.add(p1);
        Informations.add(p2);
        // Informations.add(p3);
        Informations.add(p4);
        // Informations.add(p5);
        Informations.add(p6);
        add(Informations, BorderLayout.CENTER);

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

        p1.setOpaque(false);
        p2.setOpaque(false);
        // p3.setOpaque(false);
        p4.setOpaque(false);
        // p5.setOpaque(false);
        p6.setOpaque(false);
        North.setOpaque(false);
        South.setOpaque(false);
        West.setOpaque(false);
        East.setOpaque(false);
        Informations.setOpaque(false);

        c.setBackground(new Color(33, 150, 211));

        setSize(650, 400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
//    private String printAccidentals(){
//        String result="";
//        for(int i=0;i<accidentals.size();i++){
//            result+=(accidentalsForPrint.get(i)+" ");
//        }
//        return result;
//    }

    private void saveData() {
        title = (titleText.getText() == null || titleText.getText().trim().isEmpty()) ? "Untitled" : titleText.getText();
        composer = (composerText.getText() == null || composerText.getText().trim().isEmpty()) ? "Unknown" : composerText.getText();
        try {
//            n = Integer.parseInt(nText.getText());
//            m = Integer.parseInt(mText.getText());
            tempo = Integer.parseInt(tempoText.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter numeric values for Times and Tempo.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
//
//        if (n <= 1) {
//            JOptionPane.showMessageDialog(this, "Numerator must be a positive integer greater than 1.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//
//        if ((m > 0) && (m & (m - 1)) != 0) {
//            JOptionPane.showMessageDialog(this, "Denominator must be a power of 2.", "Input Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        // out of melody range
//        if(!melody1.getSelectedItem().equals("")&&!accidental1.getSelectedItem().equals("")){
//            accidentalsForPrint.add((String)(melody1.getSelectedItem())+"-"+(String)(accidental1.getSelectedItem()));
//            String melodyValue = (String) melody1.getSelectedItem();
//            double accidentalValue = accidentalHashmap.get((String) accidental1.getSelectedItem());
//            accidentals.add(new double[]{melodyValue, accidentalValue});
//        }
//        if(!melody2.getSelectedItem().equals("")&&!accidental2.getSelectedItem().equals("")){
//            accidentalsForPrint.add((String)(melody2.getSelectedItem())+"-"+(String)(accidental2.getSelectedItem()));
//            double melodyValue = melodyHashmap.get((String) melody2.getSelectedItem());
//            double accidentalValue = accidentalHashmap.get((String) accidental2.getSelectedItem());
//            accidentals.add(new double[]{melodyValue, accidentalValue});
//        }
//        if(!melody3.getSelectedItem().equals("")&&!accidental3.getSelectedItem().equals("")){
//            accidentalsForPrint.add((String)(melody3.getSelectedItem())+"-"+(String)(accidental3.getSelectedItem()));
//            double melodyValue = melodyHashmap.get((String) melody3.getSelectedItem());
//            double accidentalValue = accidentalHashmap.get((String) accidental3.getSelectedItem());
//            accidentals.add(new double[]{melodyValue, accidentalValue});
//        }



        //https://velog.io/@san/2%EC%B0%A8%EC%9B%90-%EB%B0%B0%EC%97%B4%EB%A6%AC%EC%8A%A4%ED%8A%B8-%EC%A2%85%EB%A5%98


        int choice = JOptionPane.showConfirmDialog(
                this,
                "Title: " + title +
                        "\nComposer: " + composer +
//                        "\nTimes: " + n + " / " + m +
                        "\nTempo: " + tempo,
                        // "\nKey Signatures: "
                        // printAccidentals(),
                //여기//,
                "Confirmation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (choice == JOptionPane.YES_OPTION) {
            homepage.dispose();
            this.dispose();
            new Scorepage(title, composer,tempo); // 새 페이지 열기 (NextPage 클래스 필요)
            // System.out.println(accidentals);
        }

    }


//    public static void main(String[] args) {
//        new Informationpage();
//
}

class MyLabel extends JLabel {
    MyLabel(String text) {
        super(text);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setForeground(Color.white);
    }
}

class MyTextField extends JTextField {
    MyTextField(int columns) {
        super(columns);
        Border outerBorder = BorderFactory.createLineBorder(Color.WHITE, 1); // 외부 테두리
        Border innerPadding = BorderFactory.createEmptyBorder(2, 0, 2, 0); // 내부 여백: 위, 왼쪽, 아래, 오른쪽
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerPadding)); // 테두리와 여백 결합

        setBackground(new Color(33, 150, 211));
        setForeground(Color.white);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setHorizontalAlignment(JTextField.CENTER);
    }
}

//class MyComboBoxMelody extends JComboBox<String> {
//    MyComboBoxMelody() {
//        super(Informationpage.keySignature);
//        setFont(new Font("Century Gothic", Font.PLAIN, 14));
//        setBackground(new Color(33, 150, 211));
//        setForeground(Color.white);
//    }
//}
//
//class MyComboBoxAccidental extends JComboBox<String> {
//
//    MyComboBoxAccidental() {
//        super(Informationpage.accidentalHashmap.keySet().toArray(new String[0]));
//        setFont(new Font("Century Gothic", Font.PLAIN, 14));
//        setBackground(new Color(33, 150, 211));
//        setForeground(Color.white);
//    }
//}
//
