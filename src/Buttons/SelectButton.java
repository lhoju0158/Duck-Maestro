package Buttons;

import javax.swing.*;
import java.awt.*;

public class SelectButton extends JToggleButton {
    public SelectButton(String text) {
        super(text);

        // 폰트 설정
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        // 텍스트 크기에 따라 버튼 크기 설정
        Dimension size = calculateButtonSize(text);
        setPreferredSize(size);
    }
    public SelectButton(String text, Color color){
        super(text);

        // 폰트 설정
        setFont(new Font("Roboto", Font.PLAIN, 12));
        setForeground(color);
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createLineBorder(color, 1));

        // 텍스트 크기에 따라 버튼 크기 설정
        Dimension size = calculateButtonSize(text);
        setPreferredSize(size);
    }

    private Dimension calculateButtonSize(String text) {
        // FontMetrics로 텍스트 크기 계산
        Font font = new Font("Roboto", Font.PLAIN, 12);
        FontMetrics metrics = getFontMetrics(font);
        int textWidth = metrics.stringWidth(text); // 텍스트의 너비
        int textHeight = metrics.getHeight(); // 텍스트의 높이

        // 텍스트 크기에 padding 추가
        int buttonWidth = textWidth + (6 * 2); // 양쪽 여백 포함
        int buttonHeight = textHeight + 4;    // 위아래 여백 포함

        return new Dimension(buttonWidth, buttonHeight);
    }
}
