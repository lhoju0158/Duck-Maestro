package Sections;

import Elements.Element;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import static Pages.Scorepage.*;


public class PlaySection extends JPanel {
    private JButton[] Playbuttons = new JButton[3];
    public static Timer timer;
    public static JLabel rotatingLP;
    public static double angle = 0;
    public static ImageIcon originalLP;

    public PlaySection(){
        // set panel
        setLayout(new BorderLayout(0,8));

        // panel components - 1) rotatingLP
        originalLP = new ImageIcon("./images/lp.png");
        rotatingLP = new JLabel(originalLP);
        timer = new Timer(100, new ActionListener() {
            // https://top2blue.tistory.com/69
            @Override
            public void actionPerformed(ActionEvent e) {
                angle += 5;
                rotatingLP.setIcon(getRotatedImage(originalLP, angle));
            }
        });

        // panel components - 2) p1
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(1,3,5,5));
        Playbuttons[0] = new JButton("▶");
        Playbuttons[1] = new JButton("■");
        Playbuttons[2] = new JButton("SAVE");

        for (int i = 0; i < Playbuttons.length; i++) {
            p1.add(Playbuttons[i]);
            Playbuttons[i].addItemListener(new BeatListener());
            Playbuttons[i].setFont(new Font("Roboto", Font.BOLD, 10));
            Playbuttons[i].setBackground(Color.WHITE);
            Playbuttons[i].setBorder(new LineBorder(Color.BLACK, 1));
        }
        // playButton onClickHandler
        Playbuttons[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mergeSoundsForPlay("./sounds/resultSong.wav")) {
                    playSound("./sounds/resultSong.wav");
                    timer.start();
                }
            }
        });

        // stopButton onClickHandler
        Playbuttons[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentClip != null && currentClip.isRunning()) {
                    currentClip.stop();
                    currentClip.close();
                }
                timer.stop();
                angle = 0;
                rotatingLP.setIcon(originalLP);
            }
        });

        // saveButton onClickHandler
        Playbuttons[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mergeSoundsForPlay("./sounds/resultSong.wav")) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setDialogTitle("Save your song");
                    fileChooser.setSelectedFile(new File(songTitle+".wav"));

                    int userSelection = fileChooser.showSaveDialog(null);
                    if (userSelection == JFileChooser.APPROVE_OPTION) {
                        File dst = fileChooser.getSelectedFile();
                        File src = new File("./sounds/resultSong.wav");
                        try {
                            FileInputStream in = new FileInputStream(src);
                            FileOutputStream out = new FileOutputStream(dst);
                            byte[] buffer = new byte[1024];
                            int bytesRead;
                            while ((bytesRead = in.read(buffer)) != -1) {
                                out.write(buffer, 0, bytesRead);
                            }
                            JOptionPane.showMessageDialog(null, "Enjoy your melody!", "Save!", JOptionPane.INFORMATION_MESSAGE);
                            in.close();
                            out.close();

                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to save file.", "Save Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // set panel
        add(p1,BorderLayout.CENTER);
        add(rotatingLP,BorderLayout.NORTH);
        setOpaque(false);
        setVisible(true);
    }

    public ImageIcon getRotatedImage(ImageIcon icon, double angle) {
        int w = icon.getIconWidth();
        int h = icon.getIconHeight();
        BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotatedImage.createGraphics();

        g2d.translate(w / 2.0, h / 2.0); // GPT
        g2d.rotate(Math.toRadians(angle));
        g2d.translate(-w / 2.0, -h / 2.0);

        g2d.drawImage(icon.getImage(), 0, 0, null);

        return new ImageIcon(rotatedImage);
    }

    public static boolean mergeSoundsForPlay(String outputFilePath) {
        try {
            AudioInputStream margeStream = null;

            for (List<Element> elementList : elements) {
                for (Element element : elementList) {
                    String soundPath = element.getSoundPath();
                    File soundFile = new File(soundPath);

                    AudioInputStream nowStream = AudioSystem.getAudioInputStream(soundFile);
                    AudioFormat format = nowStream.getFormat();
                    long desiredFrameLength = (long) (format.getFrameRate() * element.getSoundLength());

                    AudioInputStream adjustedStream = adjustStreamLength(nowStream, format, desiredFrameLength);

                    if (margeStream == null) {
                        margeStream = adjustedStream;
                    } else {
                        margeStream = new AudioInputStream(
                                new SequenceInputStream(margeStream, adjustedStream),
                                margeStream.getFormat(),
                                margeStream.getFrameLength() + adjustedStream.getFrameLength()
                        );
                    }
                }
            }

            if (margeStream != null) {
                AudioSystem.write(margeStream, AudioFileFormat.Type.WAVE, new File(outputFilePath));
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
    }

    private static AudioInputStream adjustStreamLength(AudioInputStream inputStream, AudioFormat format, long desiredFrameLength) throws IOException {
        byte[] audioData = inputStream.readAllBytes();
        long originalFrameLength = inputStream.getFrameLength();
        int frameSize = format.getFrameSize();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        long framesWritten = 0;

        while (framesWritten < desiredFrameLength) {
            if (framesWritten + originalFrameLength <= desiredFrameLength) {
                outputStream.write(audioData);
                framesWritten += originalFrameLength;
            } else {
                long remainingFrames = desiredFrameLength - framesWritten;
                outputStream.write(audioData, 0, (int) (remainingFrames * frameSize));
                framesWritten = desiredFrameLength;
            }
        }

        byte[] adjustedData = outputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(adjustedData);
        return new AudioInputStream(byteArrayInputStream, format, desiredFrameLength);
    }


    public static void playSound(String mergedFilePath) {
        try {
            currentClip = AudioSystem.getClip();
            File audioFile = new File(mergedFilePath);

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip.open(audioStream);
            currentClip.start();
            currentClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    timer.stop();
                    angle = 0;
                    rotatingLP.setIcon(originalLP);
                    currentClip.close();
                }

            });

        } catch (Exception e) {
            return;
        }
    }
}
