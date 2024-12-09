package Customs;

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
        setPreferredSize(size);
    }


    private Dimension calculateButtonSize(String text) {
        Font font = new Font("Roboto", Font.PLAIN, 12);
        FontMetrics fontMetrics = getFontMetrics(font);
        int textWidth = fontMetrics.stringWidth(text);
        int textHeight = fontMetrics.getHeight();

        int buttonWidth = textWidth + (6 * 2);
        int buttonHeight = textHeight + 4;

        return new Dimension(buttonWidth, buttonHeight);
    }

}
