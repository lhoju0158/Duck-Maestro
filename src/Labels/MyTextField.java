package Labels;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(int columns) {
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