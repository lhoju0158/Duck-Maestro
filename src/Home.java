import javax.swing.*;
import java.awt.*;
import java.io.InputStream;
public class Home extends JFrame{

    public Home(){
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(null);
        // ImageIcon
        ImageIcon profileNormal = new ImageIcon("./images/defaultProfile.jpg");
        ImageIcon profileRollover = new ImageIcon("./images/rolloverProfile.gif");
        JButton profile = new JButton(profileNormal);
        profile.setRolloverIcon(profileRollover);
        profile.setLocation(425,325);
        profile.setSize(150,150);
        c.add(profile);

        // Title
        JLabel title = new JLabel("Induck transforms into a composer!");
        title.setLocation(425,20);
        title.setSize(400, 30);
        // https://kwonnam.pe.kr/wiki/java/swing/font
        // Unhandled exceptions: java.awt.FontFormatException, java.io.IOException
        try {
            InputStream is = Home.class.getResourceAsStream("./font/KyoboHandwriting2023wsa.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(24f);
            title.setFont(customFont);
        } catch (Exception e) {
            title.setFont(new Font("Arial", Font.PLAIN, 24));
        }
        c.add(title);
        setSize(1000,800);
        setVisible(true);
    }
    public static void main(String[] args) {
        new Home();
    }
}