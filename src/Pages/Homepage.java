package Pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Homepage extends JFrame {
    public Homepage() {
        // set container
        setTitle("Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container c = getContentPane();
        c.setLayout(new BorderLayout());

        // center section for center
        JPanel center = new JPanel();
        center.setLayout(new BorderLayout());

        // center components - 1) profile
        ImageIcon defaultProfile = new ImageIcon("./images/defaultProfile.jpg");
        ImageIcon gifProfile = new ImageIcon("./images/gifProfile.gif");
        JLabel profile = new JLabel(defaultProfile);
        profile.setPreferredSize(new Dimension(300, 300));

        profile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                profile.setIcon(gifProfile);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                profile.setIcon(defaultProfile);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                new Informationpage(Homepage.this);
            }
        });

        // center components - 2) title
        JLabel title = new JLabel("Duck-Maestro");
        title.setFont(new Font("Century Gothic", Font.PLAIN, 30));
        title.setForeground(Color.white);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        center.add(profile, BorderLayout.CENTER);
        center.add(title, BorderLayout.SOUTH);
        c.add(center, BorderLayout.CENTER);

        // set rest components of container
        JPanel North = new JPanel();
        North.setPreferredSize(new Dimension(50, 70));
        JPanel South = new JPanel();
        South.setPreferredSize(new Dimension(50, 100));
        JPanel West = new JPanel();
        West.setPreferredSize(new Dimension(50, 100));
        JPanel East = new JPanel();
        East.setPreferredSize(new Dimension(50, 100));
        c.add(North, BorderLayout.NORTH);
        c.add(South, BorderLayout.SOUTH);
        c.add(West, BorderLayout.WEST);
        c.add(East, BorderLayout.EAST);

        center.setOpaque(false);
        North.setOpaque(false);
        South.setOpaque(false);
        West.setOpaque(false);
        East.setOpaque(false);

        c.setBackground(new Color(33, 150, 211));
        setSize(800, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}