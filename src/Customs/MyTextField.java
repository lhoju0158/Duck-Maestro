package Customs;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class MyTextField extends JTextField {
    public MyTextField(int columns) {
        super(columns);
        setBorder(new LineBorder(Color.white, 1));
        setPreferredSize(new Dimension(10,30));

        setBackground(new Color(33, 150, 211));
        setForeground(Color.white);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setHorizontalAlignment(JTextField.CENTER);
    }
}