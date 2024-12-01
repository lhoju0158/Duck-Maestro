package Labels;

import javax.swing.*;
import java.awt.*;

public class MyLabel extends JLabel {
    public MyLabel(String text) {
        super(text);
        setFont(new Font("Century Gothic", Font.PLAIN, 15));
        setForeground(Color.white);
    }
}