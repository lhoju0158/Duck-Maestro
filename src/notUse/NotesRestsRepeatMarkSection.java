//package pages;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ItemEvent;
//
//
//public class NotesRestsRepeatMarkSection extends JPanel {
//    private String[] texts = {"Notes", "Rests", "Repeat Mark"};
//    private MyButton[] buttons = new MyButton[3];
//    private ButtonGroup buttonGroup = new ButtonGroup(); // 버튼 그룹
//    private ButtonSelectionListener listener;
//
//    public interface ButtonSelectionListener {
//        void onButtonSelected(String buttonText);
//    }
//
//    public NotesRestsRepeatMarkSection() {
//        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 10));
//
//        for (int i = 0; i < 3; i++) {
//            MyButton button = new MyButton(texts[i], new Dimension(60, 30)); // 로컬 변수로 복사
//            buttons[i] = button; // 배열에 추가
//            buttonGroup.add(button);
//            add(button);
//
//            button.addItemListener(e -> {
//                if (listener != null) {
//                    if (e.getStateChange() == ItemEvent.SELECTED) {
//                        listener.onButtonSelected(button.getText());
//                    }
//                }
//            });
//        }
//
//    }
//
//    public void setButtonSelectionListener(ButtonSelectionListener listener) {
//        this.listener = listener;
//    }
//
//    /**
//     * 선택된 버튼 상태를 초기화하는 메서드
//     */
//    public void resetSelection() {
//        buttonGroup.clearSelection(); // 버튼 그룹 선택 해제
//    }
//}
