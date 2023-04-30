import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class TictactoeGUI implements ActionListener  {
    public int flag = 0;
    JFrame frame = new JFrame("Tictactoe");
    JButton soundBtn = new JButton();
    JButton playAgain = new JButton();
    JLabel playerOneScoreText =  new JLabel("Player 1");
    JTextField playerOneScore = new JTextField("0");
    JLabel playerTwoScoreText =  new JLabel("Player 2");
    JTextField playerTwoScore = new JTextField("0");
    JPanel ticTacToeBoard = new JPanel();
    JButton index00 = new JButton();
    JButton index01 = new JButton();
    JButton index02 = new JButton();
    JButton index10 = new JButton();
    JButton index11 = new JButton();
    JButton index12 = new JButton();
    JButton index20 = new JButton();
    JButton index21 = new JButton();
    JButton index22 = new JButton();

    public TictactoeGUI() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
    }

    void renderGui()
    {
        //      for play Again button
        playAgain.setBounds(190,10,100,30);
        playAgain.setBorderPainted(false);
        playAgain.setText("Play Again");
        playAgain.setFocusable(false);
        playAgain.setOpaque(true);
        playAgain.setBackground(Color.decode("#29175B"));
        playAgain.setForeground(Color.white);
        playAgain.setFont(new Font("Arial", Font.BOLD, 12));
        playAgain.addActionListener(this);
        frame.add(playAgain);

//        for sound button
        soundBtn.setBounds(300,10,30,30);
        soundBtn.setBorderPainted(false);
        soundBtn.setText("S");
        soundBtn.setFocusable(false);
        soundBtn.setOpaque(true);
        soundBtn.setBackground(Color.decode("#29175B"));
        soundBtn.setForeground(Color.white);
        soundBtn.setFont(new Font("Arial", Font.BOLD, 30));
//        sound.addActionListener(this);
        frame.add(soundBtn);

//        for player one score Text
        playerOneScoreText.setBounds(70,70,100,30);
        playerOneScoreText.setForeground(Color.white);
        playerOneScoreText.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(playerOneScoreText);

//       for player one score
        playerOneScore.setBounds(75,100,50,50);
        playerOneScore.setEditable(false);
        playerOneScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerOneScore.setForeground(Color.white);
        Font fontOne = new Font("Arial", Font.PLAIN, 40);
        playerOneScore.setBackground(Color.decode("#29175B"));
        playerOneScore.setFont(fontOne);
        frame.add(playerOneScore);

//        for player two score Text
        playerTwoScoreText.setBounds(150,70,100,30);
        playerTwoScoreText.setForeground(Color.white);
        playerTwoScoreText.setFont(new Font("Arial", Font.BOLD, 15));
        frame.add(playerTwoScoreText);

//       for player two score
        playerTwoScore.setBounds(155,100,50,50);
        playerTwoScore.setEditable(false);
        playerTwoScore.setHorizontalAlignment(SwingConstants.CENTER);
        playerTwoScore.setForeground(Color.white);
        Font fontTwo = new Font("Arial", Font.PLAIN, 40);
        playerTwoScore.setBackground(Color.decode("#29175B"));
        playerTwoScore.setFont(fontTwo);
        frame.add(playerTwoScore);
//        for JPanel
        ticTacToeBoard.setBackground(Color.decode("#6649C4"));
        ticTacToeBoard.setLayout(null);
        ticTacToeBoard.setBounds(20,200,340,340);
        frame.add(ticTacToeBoard);
//       for index 00
        index00.setBounds(10,10,100,100);
        index00.setBackground(Color.decode("#29175B"));
        index00.setHorizontalAlignment(SwingConstants.CENTER);
        Font font00 = new Font("Arial", Font.PLAIN, 80);
        index00.setFont(font00);
        index00.addActionListener(this);
        ticTacToeBoard.add(index00);
//        for index 01
        index01.setBounds(120,10,100,100);
        index01.setBackground(Color.decode("#29175B"));
        index01.setHorizontalAlignment(SwingConstants.CENTER);
        index01.setFont(font00);
        index01.addActionListener(this);
        ticTacToeBoard.add(index01);
//             for index 02
        index02.setBounds(230,10,100,100);
        index02.setBackground(Color.decode("#29175B"));
        index02.setHorizontalAlignment(SwingConstants.CENTER);
        index02.setFont(font00);
        index02.addActionListener(this);
        ticTacToeBoard.add(index02);
//        for index 10
        index10.setBounds(10,120,100,100);
        index10.setBackground(Color.decode("#29175B"));
        index10.setHorizontalAlignment(SwingConstants.CENTER);
        index10.setFont(font00);
        index10.addActionListener(this);
        ticTacToeBoard.add(index10);
//        for index 11
        index11.setBounds(120,120,100,100);
        index11.setBackground(Color.decode("#29175B"));
        index11.setHorizontalAlignment(SwingConstants.CENTER);
        index11.setFont(font00);
        index11.addActionListener(this);
        ticTacToeBoard.add(index11);

//      for index 12
        index12.setBounds(230,120,100,100);
        index12.setBackground(Color.decode("#29175B"));
        index12.setHorizontalAlignment(SwingConstants.CENTER);
        index12.setFont(font00);
        index12.addActionListener(this);
        ticTacToeBoard.add(index12);
//        for index 20
        index20.setBounds(10,230,100,100);
        index20.setBackground(Color.decode("#29175B"));
        index20.setHorizontalAlignment(SwingConstants.CENTER);
        index20.setFont(font00);
        index20.addActionListener(this);
        ticTacToeBoard.add(index20);
//        for index 21
        //        for index 20
        index21.setBounds(120,230,100,100);
        index21.setBackground(Color.decode("#29175B"));
        index21.setHorizontalAlignment(SwingConstants.CENTER);
        index21.setFont(font00);
        index21.addActionListener(this);
        ticTacToeBoard.add(index21);
//        for index 22
        //        for index 20
        index22.setBounds(230,230,100,100);
        index22.setBackground(Color.decode("#29175B"));
        index22.setHorizontalAlignment(SwingConstants.CENTER);
        index22.setFont(font00);
        index22.addActionListener(this);
        ticTacToeBoard.add(index22);
    }
    void render()
    {
        renderGui();
        //        for frame
        frame.setSize(400,600);
        frame.getContentPane().setBackground(Color.decode("#412DA7"));
        frame.setLayout(null);
        frame.setTitle("Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() ==  index00)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index00.getText().equals("")) {
                if (flag % 2 == 0) {
                    index00.setForeground(Color.decode("#FFD032"));
                    index00.setText("X");
                } else {
                    index00.setForeground(Color.decode("#EB1751"));
                    index00.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index01)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index01.getText().equals("")) {
                if (flag % 2 == 0) {
                    index01.setForeground(Color.decode("#FFD032"));
                    index01.setText("X");
                } else {
                    index01.setForeground(Color.decode("#EB1751"));
                    index01.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index02)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index02.getText().equals("")) {
                if (flag % 2 == 0) {
                    index02.setForeground(Color.decode("#FFD032"));
                    index02.setText("X");
                } else {
                    index02.setForeground(Color.decode("#EB1751"));
                    index02.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index10)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index10.getText().equals("")) {
                if (flag % 2 == 0) {
                    index10.setForeground(Color.decode("#FFD032"));
                    index10.setText("X");
                } else {
                    index10.setForeground(Color.decode("#EB1751"));
                    index10.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index11)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index11.getText().equals("")) {
                if (flag % 2 == 0) {
                    index11.setForeground(Color.decode("#FFD032"));
                    index11.setText("X");
                } else {
                    index11.setForeground(Color.decode("#EB1751"));
                    index11.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index12)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index12.getText().equals("")) {
                if (flag % 2 == 0) {
                    index12.setForeground(Color.decode("#FFD032"));
                    index12.setText("X");
                } else {
                    index12.setForeground(Color.decode("#EB1751"));
                    index12.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index20)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index20.getText().equals("")) {
                if (flag % 2 == 0) {
                    index20.setForeground(Color.decode("#FFD032"));
                    index20.setText("X");
                } else {
                    index20.setForeground(Color.decode("#EB1751"));
                    index20.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index21)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index21.getText().equals("")) {
                if (flag % 2 == 0) {
                    index21.setForeground(Color.decode("#FFD032"));
                    index21.setText("X");
                } else {
                    index21.setForeground(Color.decode("#EB1751"));
                    index21.setText("O");
                }
            }
            flag++;
        }
        if(e.getSource() ==  index22)
        {
            SoundPlayer.playSound("buttonClicked.wav");
            if(index22.getText().equals("")) {
                if (flag % 2 == 0) {
                    index22.setForeground(Color.decode("#FFD032"));
                    index22.setText("X");
                } else {
                    index22.setForeground(Color.decode("#EB1751"));
                    index22.setText("O");
                }
            }
            flag++;
        }

    }
}
