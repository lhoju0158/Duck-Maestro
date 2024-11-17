package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Home extends JFrame{
    //  Container c;
    // void setPage(boolean visible){
    //     c.setVisible(visible);
    // }

    public Home(){
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        c.setBackground(new Color(33,150,211));

        // ImageIcon
        ImageIcon defaultProfile = new ImageIcon("./images/defaultProfile.jpg");
        ImageIcon gifProfile = new ImageIcon("./images/gifProfile.gif");
        JLabel profile = new JLabel(defaultProfile);
        profile.setSize(300,300);
        profile.setAlignmentX(Component.CENTER_ALIGNMENT);
        profile.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // https://202psj.tistory.com/289
        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e){
                profile.setIcon(gifProfile);
            }
            @Override
            public void mouseExited(MouseEvent e) {
                // GPT
                profile.setIcon(defaultProfile);
                // Cursor.HAND_CURSOR;
            }
            @Override
            public void mouseClicked(MouseEvent e) {
                setVisible(false);
                new Score();
            }

        });

        // Title
        JLabel title = new JLabel("Duck-Maestro");
        // title.setFont(new Font("Lucida Handwriting", Font.PLAIN, 24));
        title.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        title.setSize(400, 30);

        c.add(Box.createVerticalStrut(200));
        c.add(profile);
        c.add(Box.createVerticalStrut(20));
        c.add(title);
        c.add(Box.createVerticalStrut(200));
        setSize(1000,800);
        setVisible(true);
    }
    // public static void main(String[] args) {
    //     new Home();
    // }
}