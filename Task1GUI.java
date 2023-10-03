import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Task1GUI {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel guessLabel;
    private JTextField guessTextField;
    private JButton guessButton;
    private JPanel resultPanel;
    private JTextArea resultTextArea;
    private JButton restartButton;

    private int num;
    private int lives;

    public Task1GUI() {
        frame = new JFrame("Guess the Amount Game");
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        titleLabel = new JLabel("I have taken out some money from your wallet.");
        guessLabel = new JLabel("Guess the amount:");
        guessTextField = new JTextField(10);
        guessButton = new JButton("Guess");

        resultPanel = new JPanel();
        resultTextArea = new JTextArea(5, 30);
        resultTextArea.setEditable(false);
        resultTextArea.setLineWrap(true);
        resultTextArea.setWrapStyleWord(true);
        resultTextArea.setBorder(new EmptyBorder(10, 10, 10, 10));

        restartButton = new JButton("Restart");
        restartButton.setEnabled(false);

        resultPanel.setLayout(new BorderLayout());
        resultPanel.add(new JScrollPane(resultTextArea), BorderLayout.CENTER);
        resultPanel.add(restartButton, BorderLayout.SOUTH);

        frame.add(titleLabel);
        frame.add(guessLabel);
        frame.add(guessTextField);
        frame.add(guessButton);
        frame.add(resultPanel);

        Random rand = new Random();
        num = rand.nextInt(100);
        lives = 5;

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess;
                try {
                    guess = Integer.parseInt(guessTextField.getText());
                } catch (NumberFormatException ex) {
                    resultTextArea.setText("Please enter a valid number.");
                    return;
                }

                setDefaultFont();   // Set default font
                if (guess == num) {
                    resultTextArea.setForeground(Color.BLUE);
                    resultTextArea.setFont(new Font("Arial", Font.BOLD, 15));
                    resultTextArea.setText("Congratulations! You correctly guessed the amount.");
                    guessTextField.setEnabled(false);
                    guessButton.setEnabled(false);
                    restartButton.setEnabled(true);
                } else {
                    if (guess > num) {
                        resultTextArea.setForeground(Color.RED);
                        resultTextArea.setText("Your guessed amount is larger. You have " + (--lives) + " more chances.");
                    } else if (lives > 0) {
                        resultTextArea.setForeground(Color.RED);
                        resultTextArea.setText("Your guessed amount is smaller. You have " + (--lives) + " more chances.");
                    }

                    if (lives == 0) {
                        resultTextArea.setForeground(Color.RED);
                        resultTextArea.setFont(new Font("Arial", Font.BOLD, 15));
                        resultTextArea.setText("You failed to guess the number.");
                        resultTextArea.append("\nThe amount was " + num + " rupees.");
                        guessTextField.setEnabled(false);
                        guessButton.setEnabled(false);
                        restartButton.setEnabled(true); // Enable restart button
                        restartButton.setEnabled(true);
                    }
                }
                guessTextField.setText("");
            }
        });

        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the game
                Random rand = new Random();
                num = rand.nextInt(100);
                lives = 5;
                guessTextField.setEnabled(true);
                guessButton.setEnabled(true);
                restartButton.setEnabled(false);
                resultTextArea.setText("");
                setDefaultFont(); // Set default font
            }
        });

        frame.setVisible(true);
    }

    private void setDefaultFont() {
        resultTextArea.setForeground(Color.BLACK); // Set text color to black
        resultTextArea.setFont(UIManager.getFont("Label.font")); // Use the default font
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Task1GUI();
            }
        });
    }
}
