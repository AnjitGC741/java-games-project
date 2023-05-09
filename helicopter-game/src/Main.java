import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


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
    private  Image crashed;
    private int score = 0;
    private JButton restartButton;
    private  JLabel scoreValue;
    private int highScore = 0;

    public Main() {
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        //        for background image
        try {
            BufferedImage originalImage = ImageIO.read(new File("background2.jpg"));
            Image scaledImage = originalImage.getScaledInstance(800, 400, Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            JLabel backgroundLabel = new JLabel(scaledIcon);
            add(backgroundLabel);
            scoreValue = new JLabel("0");
            scoreValue.setBounds(370, 50, 100, 60);
            scoreValue.setFont(new Font("Arial", Font.BOLD, 70));
            scoreValue.setForeground(Color.WHITE);
            restartButton = new JButton("Restart");
            restartButton.setBounds(350, 260, 80, 30);
            restartButton.setBackground(Color.RED);
            restartButton.setForeground(Color.WHITE);
            restartButton.setFont(new Font("Arial", Font.BOLD, 12));
            restartButton.setVisible(false); //hide the button initially
            restartButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    restart();
                }
            });
            backgroundLabel.add(scoreValue);
            backgroundLabel.add(restartButton);
            backgroundLabel.setLayout(null);
            //add(restartButton);
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
        crashed = new ImageIcon("crashed.gif").getImage();
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    birdSpeed = -10;
                }
            }
        });
        JLabel backgroundLabel = new JLabel();
        // Read the high score from a file (if it exists)
        try {
            File file = new File("highscore.txt");
            Scanner scanner = new Scanner(file);
            highScore = scanner.nextInt();
            scanner.close();
        } catch (FileNotFoundException e) {
            // If the file doesn't exist, create a new one
            try {
                File file = new File("highscore.txt");
                FileWriter writer = new FileWriter(file);
                writer.write("0");
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        JLabel highScoreLabel = new JLabel("High Score: " + highScore);
        highScoreLabel.setBounds(100, 100, 200, 60);
        highScoreLabel.setFont(new Font("Arial", Font.BOLD, 30));
        highScoreLabel.setForeground(Color.WHITE);
        backgroundLabel.add(highScoreLabel);



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
            restartButton.setVisible(true); // show the button when the game is over
            // Update the high score
            if (score > highScore) {
                highScore = score;
                try {
                    File file = new File("highscore.txt");
                    FileWriter writer = new FileWriter(file);
                    writer.write(Integer.toString(highScore));
                    writer.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
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
        //increment the score every time the object passes through a pipe
        if (pipeX + pipeWidth == birdX) {
            score++;
            scoreValue.setText(String.valueOf(score));
        }

        if (score > highScore) {
            highScore = score;
            // Write the new high score to the file
            try {
                File file = new File("highscore.txt");
                FileWriter writer = new FileWriter(file);
                writer.write(Integer.toString(highScore));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        scoreValue.setText(String.valueOf(score));
        scoreValue.repaint();
        JLabel backgroundLabel = new JLabel();
// Update the high score label
        JLabel highScoreValue = new JLabel(String.valueOf(highScore));
        highScoreValue.setBounds(700, 100, 100, 60);
        highScoreValue.setFont(new Font("Arial", Font.BOLD, 70));
        highScoreValue.setForeground(Color.WHITE);
        backgroundLabel.add(highScoreValue);
        if (score > highScore) {
            highScore = score;
            try {
                File file = new File("highscore.txt");
                FileWriter writer = new FileWriter(file);
                writer.write(Integer.toString(highScore));
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void updateHighScore(int score) {
        if (score > highScore) {
            highScore = score;
        }
        try {
            FileWriter writer = new FileWriter("highscore.txt");
            writer.write(Integer.toString(highScore));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(planeImage, birdX, birdY, 60, 50, null);
        g.drawImage(ufoImage,pipeX, 0, pipeWidth, pipe1Height,null);
        g.drawImage(treeImage,pipeX, height - pipe2Height, pipeWidth, pipe2Height,null);
//        g.setColor(Color.YELLOW);
//        g.fillRect(birdX, birdY, birdSize, birdSize);
        if (!isRunning) {
            g.drawImage(crashed, birdX, birdY, 45, 35, null);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", width / 2 - 150, height / 2);
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 48));
            g.drawString("GAME OVER", width / 2 - 150, height / 2);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Your score: " + score, width / 2 - 80, height / 2 + 50);
            g.drawString("High score: " + highScore, width / 2 - 80, height / 2 + 80);
        }
    }
//    to restart a game
    private void restart() {
        birdX = 100;
        birdY = 250;
        birdSpeed = 0;
        pipeX = width + 50;
        pipe1Height = 200;
        pipe2Height = height - pipe1Height - pipeGap;
        isRunning = true;
        score = 0;
        restartButton.setVisible(false);
        playSound(true);
        scoreValue.setText(String.valueOf(score) );
    }
    public static void main(String[] args) {
        new Main();
    }
}
