package pages;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.util.ArrayList;


public class ComponentLabel extends JLabel {
    private Point initialClick;


    public ComponentLabel(ImageIcon icon) {
        super(icon); // Set the image as an icon for the label
        setOpaque(false); // Make label background transparent to show the image only

        // Add drag functionality to the label
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                initialClick = e.getPoint(); // 마우스 클릭 위치 저장
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JComponent source = (JComponent) e.getSource();
                Point currentLocation = source.getLocation();

                // 마우스 이동 거리 계산
                int xMoved = e.getX() - initialClick.x;
                int yMoved = e.getY() - initialClick.y;
                int newX = currentLocation.x + xMoved;
                int newY = currentLocation.y + yMoved;

                source.setLocation(newX, newY); // 새로운 위치 설정
                source.getParent().repaint(); // Parent를 리페인트하여 배경 유지
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDropped(MouseEvent e) {
//
//            }
        });
    }

    public static void createSection(Container container) {
        File notesDir = new File("./images/notes");
        File[] noteImages = notesDir.listFiles();
        File restDir = new File("./images/rests");
        File[] restImages = restDir.listFiles();
        Point noteStartPosition = new Point(880,25);
        Point restStartPosition = new Point(1170,25);
        // ImageIcon[] componentImagePath = {new ImageIcon("./images/notes/note0.5.png"), new ImageIcon("./images/high (25).png")};
        ArrayList<ImageIcon> etcImages = new ArrayList<>();
        for (int i = 0; i < noteImages.length; i++) {
            Point tempPosition = noteStartPosition;
            ImageIcon temIcon = new ImageIcon(noteImages[i].getPath());
            ComponentLabel noteLabel = new ComponentLabel(temIcon);
            noteLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            if(temIcon.getIconHeight()<20){
                etcImages.add(temIcon);
            }
            else{
                noteLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);

            }
            container.add(noteLabel);
            tempPosition.x+=30;
        }
        for (int i = 0; i < restImages.length; i++) {

            Point tempPosition = restStartPosition;
            ImageIcon temIcon = new ImageIcon(restImages[i].getPath());
            ComponentLabel restLabel = new ComponentLabel(temIcon);
            restLabel.setVerticalAlignment(SwingConstants.BOTTOM);
            if(temIcon.getIconHeight()<20){
                etcImages.add(temIcon);
            }
            else{
                restLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);
            }
            container.add(restLabel);
            tempPosition.x+=35;
        }
        for(int i=0;i<etcImages.size();i++){
            ImageIcon temIcon = etcImages.get(i);
            ComponentLabel temLabel = new ComponentLabel(temIcon);
            temLabel.setHorizontalAlignment(SwingConstants.CENTER);
            temLabel.setBounds(1120, 10+i*15, 40, 25);
            container.add(temLabel);
        }

    }
}
