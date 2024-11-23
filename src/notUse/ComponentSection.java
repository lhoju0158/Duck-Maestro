//package notUse;
//
//import notUse.ComponentLabel;
//import pages.Score;
//
//import javax.swing.*;
//import java.awt.*;
//import java.io.File;
//import java.util.ArrayList;
//
//public class ComponentSection extends JPanel {
//    public ComponentSection() {
//        setLayout(null); // 절대 좌표 사용
//        setOpaque(false); // 배경 투명
//
//        File[] noteImages = Score.notesDir.listFiles();
//        File[] restImages = Score.restDir.listFiles();
//        Point noteStartPosition = new Point(880, 25);
//        Point restStartPosition = new Point(1170, 25);
//        ArrayList<String> etcImages = new ArrayList<>();
//
//        // Note 이미지 추가
//        if (noteImages != null) {
//            for (int i = 0; i < noteImages.length; i++) {
//                Point tempPosition = noteStartPosition;
//                ImageIcon temIcon = new ImageIcon(noteImages[i].getPath());
//                ComponentLabel noteLabel = new ComponentLabel(temIcon, noteImages[i].getPath());
//                noteLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//
//                if (temIcon.getIconHeight() < 20) {
//                    etcImages.add(noteImages[i].getPath());
//                } else {
//                    noteLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);
//                }
//                add(noteLabel);
//                tempPosition.x += 30;
//            }
//        }
//
//        // Rest 이미지 추가
//        if (restImages != null) {
//            for (int i = 0; i < restImages.length; i++) {
//                Point tempPosition = restStartPosition;
//                ImageIcon temIcon = new ImageIcon(restImages[i].getPath());
//                ComponentLabel restLabel = new ComponentLabel(temIcon, restImages[i].getPath());
//                restLabel.setVerticalAlignment(SwingConstants.BOTTOM);
//
//                if (temIcon.getIconHeight() < 20) {
//                    etcImages.add(restImages[i].getPath());
//                } else {
//                    restLabel.setBounds(tempPosition.x, tempPosition.y, temIcon.getIconWidth(), 40);
//                }
//                add(restLabel);
//                tempPosition.x += 35;
//            }
//        }
//
//        // 기타 이미지 추가
//        for (int i = 0; i < etcImages.size(); i++) {
//            ImageIcon temIcon = new ImageIcon(etcImages.get(i));
//            ComponentLabel temLabel = new ComponentLabel(temIcon, etcImages.get(i));
//            temLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            temLabel.setBounds(1120, 10 + i * 15, 40, 25);
//            add(temLabel);
//        }
//
//        // 더블클릭 이벤트 추가
////        addMouseListener(new MouseAdapter() {
////            @Override
////            public void mouseClicked(MouseEvent e) {
////                if (e.getClickCount() == 2) { // 더블클릭 확인
////                    Point clickPoint = e.getPoint();
////                    System.out.printf("Mouse Double-Clicked at Component: x=%d, y=%d%n", clickPoint.x, clickPoint.y);
////                }
////            }
////        });
//    }
//
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g);
//
//        // 테스트용 코드 (현재 주석 처리)
//        g.setColor(Color.RED);
//        // g.drawLine(94, 140, 94, 140 + 32);
//        // g.drawLine(904, 140, 904, 140 + 32);
//        // g.drawLine(810, 140, 810, 140 + 32);
//        // g.drawLine(1620, 140, 1620, 140 + 32);
//    }
//}
