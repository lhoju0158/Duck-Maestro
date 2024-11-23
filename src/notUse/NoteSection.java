//package pages;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ItemEvent;
//import java.awt.event.ItemListener;
//
//public class NoteSection extends JPanel {
//    // 순서 보장하기 위해서 file.sub안함
//
////    private ImageIcon[] images
////            = {new ImageIcon("./images/notes/note1.png"),
////            new ImageIcon("./images/notes/note0.75.png"),
////            new ImageIcon("./images/notes/note0.5.png"),
////            new ImageIcon("./images/notes/note0.375.png"),
////            new ImageIcon("./images/notes/note0.25.png"),
////            new ImageIcon("./images/notes/note0.1875.png"),
////            new ImageIcon("./images/notes/note0.125.png"),
////            new ImageIcon("./images/notes/note0.09375.png"),
////            new ImageIcon("./images/notes/note0.0625.png")};
//
//
//    //    private double[] texts ={
////            1.0,0.75,0.5,0.375,0.25,0.1875,0.125,0.09375,0.0625
////    };
//    private NoteRestButton[] Beatbuttons = new NoteRestButton[9];
//    // private
//
//    public NoteSection() {
//        setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));
//        ButtonGroup beats = new ButtonGroup();
//        setOpaque(true);
//
//        for (int i = 0; i < 9; i++) {
//            Beatbuttons[i] = new NoteRestButton(texts[i]);
//            beats.add(Beatbuttons[i]);
//            add(Beatbuttons[i]);
//            Beatbuttons[i].addItemListener(new BeatListener());
//        }
//
//    }
//    public void resetSelection() {
//        for (int i = 0; i < 9; i++) {
//            Beatbuttons[i].setSelected(false);
//        }
//
//}}
//
//class BeatListener implements ItemListener {
//    @Override
//    public void itemStateChanged(ItemEvent e) {
//        JToggleButton button = (JToggleButton) e.getSource();
//        if (e.getStateChange() == ItemEvent.SELECTED) {
//            System.out.println(button.getText() + " is SELECTED.");
//        }
//    }
//}
