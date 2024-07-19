import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class NumberGuessingGame extends JFrame {

    private JTextField guessField;
    private JLabel feedbackLabel;
    private int numberToGuess;
    private int numberOfTries;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel promptLabel = new JLabel("Guess the number (1-100):");
        promptLabel.setBounds(20, 20, 200, 25);
        add(promptLabel);

        guessField = new JTextField();
        guessField.setBounds(20, 50, 100, 25);
        add(guessField);

        JButton guessButton = new JButton("Guess");
        guessButton.setBounds(140, 50, 80, 25);
        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkGuess();
            }
        });
        add(guessButton);

        feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(20, 90, 250, 25);
        add(feedbackLabel);

        resetGame();
    }

    private void resetGame() {
        Random random = new Random();
        numberToGuess = random.nextInt(100) + 1;
        numberOfTries = 0;
        feedbackLabel.setText("");
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            numberOfTries++;

            if (guess < numberToGuess) {
                feedbackLabel.setText("Too low. Try again.");
            } else if (guess > numberToGuess) {
                feedbackLabel.setText("Too high. Try again.");
            } else {
                feedbackLabel.setText("Correct! You guessed it in " + numberOfTries + " tries.");
                int response = JOptionPane.showConfirmDialog(this, "Do you want to play again?", "Play Again", JOptionPane.YES_NO_OPTION);
                if (response == JOptionPane.YES_OPTION) {
                    resetGame();
                } else {
                    System.exit(0);
                }
            }
        } catch (NumberFormatException e) {
            feedbackLabel.setText("Invalid input. Please enter a number.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NumberGuessingGame().setVisible(true);
            }
        });
    }
}
