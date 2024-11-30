package Section;

import Buttons.SelectButton;
import components.Element;
import pages.Scorepage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static pages.Scorepage.*;


public class PlaySection extends JPanel {
    private JButton[] Playbuttons = new JButton[3];
    // ButtonGroup playComponents = new ButtonGroup();
    public static Timer rotationTimer; // 이미지를 회전시키는 타이머
    public static JLabel rotatingImageLabel; // 회전하는 이미지를 표시하는 JLabel
    public static double rotationAngle = 0; // 현재 회전 각도
    public final int rotationSpeed = 100; // 회전 속도 (밀리초 단위)
    public static ImageIcon originalImageIcon; // 원본 이미지

    public PlaySection(){
        setLayout(new BorderLayout(0,8));

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,3,5,5));


        Playbuttons[0] = new JButton("▶");
        Playbuttons[1] = new JButton("■");
        Playbuttons[2] = new JButton("Save");

        for (int i = 0; i < Playbuttons.length; i++) {
            p1.add(Playbuttons[i]);
            Playbuttons[i].addItemListener(new BeatListener());
            Playbuttons[i].setFont(new Font("Roboto", Font.BOLD, 10));
            Playbuttons[i].setBackground(Color.WHITE);
            Playbuttons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        }
        Playbuttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mergeSoundsForPlay(Scorepage.songTitle)) {
                    playElements(Scorepage.songTitle);
                    rotationTimer.start();
                }
            }
        });

        Playbuttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop(); // 재생 중단
                    currentClip.close(); // 리소스 해제
                }
                rotationTimer.stop(); // 회전 애니메이션 중지
                rotationAngle = 0; // 각도 초기화
                rotatingImageLabel.setIcon(originalImageIcon); // 원본 이미지 복원
            }
        });


        Playbuttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mergeSoundsForPlay(Scorepage.songTitle)) {
                    // 파일 저장 위치를 선택하는 다이얼로그 생성
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save Merged Sound File");
                    fileChooser.setSelectedFile(new File(songTitle)); // 기본 파일 이름 설정

                    int userSelection = fileChooser.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File fileToSave = fileChooser.getSelectedFile();

                        try {
                            // 원본 파일을 선택한 위치로 복사
                            File originalFile = new File(Scorepage.songTitle);
                            if (originalFile.exists()) {
                                try (InputStream in = new FileInputStream(originalFile);
                                     OutputStream out = new FileOutputStream(fileToSave)) {
                                    byte[] buffer = new byte[1024];
                                    int bytesRead;
                                    while ((bytesRead = in.read(buffer)) != -1) {
                                        out.write(buffer, 0, bytesRead);
                                    }
                                    JOptionPane.showMessageDialog(null, "File saved at: " + fileToSave.getAbsolutePath(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save file.", "Save Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        originalImageIcon = new ImageIcon("./images/iuImage.png");
        rotatingImageLabel = new JLabel(originalImageIcon);
        rotationTimer = new Timer(rotationSpeed, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rotationAngle += 5; // 각도 증가
                rotationAngle %= 360; // 360도 이상이면 초기화
                rotatingImageLabel.setIcon(getRotatedImage(originalImageIcon, rotationAngle));
            }
        });
        add(p1,BorderLayout.CENTER);
        add(rotatingImageLabel,BorderLayout.NORTH);

        setOpaque(false);
        setVisible(true);
    }

    public ImageIcon getRotatedImage(ImageIcon icon, double angle) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // 이미지 중심을 기준으로 회전
        g2d.translate(w / 2.0, h / 2.0);
        g2d.rotate(Math.toRadians(angle));
        g2d.translate(-w / 2.0, -h / 2.0);

        // 원본 이미지 그리기
        g2d.drawImage(icon.getImage(), 0, 0, null);
        g2d.dispose();

        return new ImageIcon(rotatedImage);
    }
    public static boolean mergeSoundsForPlay(String outputFilePath) {
        try {
            // 병합할 모든 AudioInputStream을 저장할 리스트
            AudioInputStream concatenatedStream = null;

            for (List<Element> elementList : elements) {
                for (Element element : elementList) {
                    String soundPath = element.getSoundPath();
                    if (soundPath == null || soundPath.isEmpty()) {
                        System.out.println("Skipping element with empty sound path.");
                        continue;
                    }

                    File soundFile = new File(soundPath);
                    if (!soundFile.exists()) {
                        System.out.println("Sound file not found: " + soundPath);
                        continue;
                    }

                    // 파일 스트림 읽기
                    AudioInputStream currentStream = AudioSystem.getAudioInputStream(soundFile);
                    AudioFormat format = currentStream.getFormat();
                    long desiredFrameLength = (long) (format.getFrameRate() * element.getSoundLength()); // 원하는 길이의 프레임 계산

                    // 원하는 길이로 조정된 스트림 생성
                    AudioInputStream adjustedStream = adjustStreamLength(currentStream, format, desiredFrameLength);

                    if (concatenatedStream == null) {
                        concatenatedStream = adjustedStream; // 첫 번째 스트림
                    } else {
                        concatenatedStream = new AudioInputStream(
                                new SequenceInputStream(concatenatedStream, adjustedStream),
                                concatenatedStream.getFormat(),
                                concatenatedStream.getFrameLength() + adjustedStream.getFrameLength()
                        );
                    }
                }
            }

            // 병합된 스트림을 새로운 파일로 저장
            if (concatenatedStream != null) {
                AudioSystem.write(concatenatedStream, AudioFileFormat.Type.WAVE, new File(outputFilePath));
                System.out.println("Merged file created at: " + outputFilePath);
                return true;
            } else {
                System.out.println("No valid sound files to merge.");
                return false;
            }

        } catch (Exception e) {
            System.err.println("Error merging sound files: " + e.getMessage());
            return false;
        }
    }

    private static AudioInputStream adjustStreamLength(AudioInputStream inputStream, AudioFormat format, long desiredFrameLength) throws IOException {
        byte[] audioData = inputStream.readAllBytes(); // 원본 데이터를 모두 읽기
        long originalFrameLength = inputStream.getFrameLength();
        int frameSize = format.getFrameSize();

        // 새 길이를 위한 데이터 배열 준비
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        long framesWritten = 0;

        while (framesWritten < desiredFrameLength) {
            if (framesWritten + originalFrameLength <= desiredFrameLength) {
                outputStream.write(audioData); // 전체 복제
                framesWritten += originalFrameLength;
            } else {
                // 필요한 만큼만 복제
                long remainingFrames = desiredFrameLength - framesWritten;
                outputStream.write(audioData, 0, (int) (remainingFrames * frameSize));
                framesWritten = desiredFrameLength;
            }
        }

        byte[] adjustedData = outputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjustedData);
        return new AudioInputStream(byteArrayInputStream, format, desiredFrameLength);
    }


    public static void playElements(String mergedFilePath) {
        try {
            File soundFile = new File(mergedFilePath);
            if (!soundFile.exists()) {
                System.out.println("Merged sound file not found: " + mergedFilePath);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            currentClip = AudioSystem.getClip(); // currentClip에 재생 클립 저장
            currentClip.open(audioStream);

            System.out.println("Playing merged sound...");
            currentClip.start();

            // 재생 완료까지 대기
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    rotationTimer.stop(); // 회전 애니메이션 중지
                    rotationAngle = 0; // 각도 초기화
                    rotatingImageLabel.setIcon(originalImageIcon); // 원본 이미지 복원

                    currentClip.close();
                    System.out.println("Playback finished.");

                }

            });

        } catch (Exception e) {
            System.err.println("Error playing merged sound file: " + e.getMessage());
        }
    }
}
