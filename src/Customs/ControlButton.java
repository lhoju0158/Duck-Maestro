package Customs;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ControlButton extends JButton {
    public ControlButton(String text, Color color){
        super(text);
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setForeground(color);
        setBackground(Color.WHITE);
        setBorder(new LineBorder(color, 1));
    }
}
