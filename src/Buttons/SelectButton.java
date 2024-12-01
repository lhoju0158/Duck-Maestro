package Buttons;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SelectButton extends JToggleButton {
    public SelectButton(String text) {
        super(text);

        setFont(new Font("Roboto", Font.PLAIN, 12));
        setBackground(Color.WHITE);
        setBorder(new LineBorder(Color.BLACK, 1));

        Dimension size = calculateButtonSize(text);
        setPreferredSize(size);// https://m.blog.naver.com/fkdltmxlr3/221720415238
    }


    private Dimension calculateButtonSize(String text) {
        Font font = new Font("Roboto", Font.PLAIN, 12);
        FontMetrics metrics = getFontMetrics(font); //https://blog.naver.com/guzayoul/100037099995
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();

        int buttonWidth = textWidth + (6 * 2);
        int buttonHeight = textHeight + 4;

        return new Dimension(buttonWidth, buttonHeight);
    }

}
