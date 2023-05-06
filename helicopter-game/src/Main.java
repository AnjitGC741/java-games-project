import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Main extends JFrame {
    private ImageIcon backgroundImage;
    //game width and height
    private int width = 800;
    private int height = 400;
    //    object starting position
    private int birdX = 100;
    private int birdY = 250;
    //    object size and speed
    private int birdSize = 50;
    //    object falling speed from top to bottom
    private int birdSpeed = 0;
    private int pipeX = width + 50;
    //    gap between the upPipe and the downPipe
    private int pipeGap = 200;
    private int pipeWidth = 100;
    private int pipe1Height = 230;
    private int pipe2Height = height - pipe1Height - pipeGap;
    public boolean isRunning = true;
    private Clip soundClip;
    private Image planeImage;
    private Image treeImage;
    private Image ufoImage;

    public Main() {
//        for background image
        try {
            BufferedImage originalImage = ImageIO.read(new File("background2.jpg"));
            Image scaledImage = originalImage.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel backgroundLabel = new JLabel(scaledIcon);
            add(backgroundLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        playSound(true);
        setTitle("Flappy Bird");
        setSize(width, height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        planeImage = new ImageIcon("helicopter.gif").getImage();
        treeImage = new ImageIcon("tree2.png").getImage();
        ufoImage = new ImageIcon("ufo.png").getImage();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    birdSpeed = -10;
                }
            }
        });
        Timer timer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
                repaint();
            }
        });
        timer.start();
    }
    //    for sound
    private void playSound(Boolean value) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("helicopter-sound-41975.wav").getAbsoluteFile());
            if (soundClip != null && soundClip.isRunning()) {
                soundClip.stop();
            }
            soundClip = AudioSystem.getClip();
            soundClip.open(audioInputStream);
            if (value) {
                soundClip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (Exception ex) {
            System.out.println("Error playing sound: " + ex.getMessage());
        }
    }
//    for checking and updating the game status
    private void update() {
        if (!isRunning) {
            return;
        }
//        make object fall when a space is not clicked
        birdY += birdSpeed;
        birdSpeed += 1;

//        to create new obstacle one the old obstacle is out of screen
        pipeX -= 5;
        if (pipeX + pipeWidth < 0) {
            pipeX = width;
            pipe1Height = (int) (Math.random() * (height - pipeGap - 100)) + 50;
            pipe2Height = height - pipe1Height - pipeGap;
        }
//        checks if the bird  collided with the pipe or not
        if (birdY < 0 || birdY + birdSize > height) {
            playSound(false);
            HelicopterSound.playCrashSound();
            isRunning = false;
        }
        if (birdX + birdSize > pipeX && birdX < pipeX + pipeWidth) {
            if (birdY < pipe1Height || birdY + birdSize > pipe1Height + pipeGap) {
                playSound(false);
                HelicopterSound.playCrashSound();
                isRunning = false;
            }
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.RED);
//        g.fillRect(pipeX, 0, pipeWidth, pipe1Height);
        g.drawImage(ufoImage,pipeX, 0, pipeWidth, pipe1Height,null);
        g.drawImage(treeImage,pipeX, height - pipe2Height, pipeWidth, pipe2Height,null);
        g.setColor(Color.YELLOW);
        g.drawImage(planeImage, birdX, birdY, birdSize, birdSize, null);
        if (!isRunning) {
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", width / 2 - 150, height / 2);
        }

    }
//    to restart a game
    public void restart() {
        birdX = 100;
        birdY = 250;
        birdSpeed = 0;
        pipeX = width + 50;
        pipe1Height = 200;
        pipe2Height = height - pipe1Height - pipeGap;
        isRunning = true;
    }

    public static void main(String[] args) {
        new Main();
    }
}
