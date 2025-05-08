import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class RockPaperScissorsUI extends JFrame {
    // Create components for the UI
    private JLabel instructionLabel, resultLabel, scoreLabel;
    private JButton rockButton, paperButton, scissorsButton, resetButton;
    private int userScore = 0, computerScore = 0;

    public RockPaperScissorsUI() {
        // Set the frame properties
        setTitle("Rock, Paper, Scissors Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold UI components
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        // Instructions label
        instructionLabel = new JLabel("Choose Rock, Paper, or Scissors:");
        instructionLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(instructionLabel);

        // Buttons for rock, paper, and scissors
        rockButton = new JButton("Rock");
        paperButton = new JButton("Paper");
        scissorsButton = new JButton("Scissors");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(rockButton);
        buttonPanel.add(paperButton);
        buttonPanel.add(scissorsButton);
        panel.add(buttonPanel);

        // Result label
        resultLabel = new JLabel("Result: ", JLabel.CENTER);
        panel.add(resultLabel);

        // Score label
        scoreLabel = new JLabel("Score: You 0 - 0 Computer", JLabel.CENTER);
        panel.add(scoreLabel);

        // Reset button
        resetButton = new JButton("Reset Game");
        panel.add(resetButton);

        // Add panel to frame
        add(panel);

        // Add action listeners to buttons
        rockButton.addActionListener(new GameAction("rock"));
        paperButton.addActionListener(new GameAction("paper"));
        scissorsButton.addActionListener(new GameAction("scissors"));
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userScore = 0;
                computerScore = 0;
                scoreLabel.setText("Score: You 0 - 0 Computer");
                resultLabel.setText("Result: ");
            }
        });
    }

    // Inner class to handle game actions
    private class GameAction implements ActionListener {
        private String userChoice;

        public GameAction(String userChoice) {
            this.userChoice = userChoice;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] options = {"rock", "paper", "scissors"};
            Random random = new Random();
            String computerChoice = options[random.nextInt(3)];

            // Determine the result
            if (userChoice.equals(computerChoice)) {
                resultLabel.setText("Result: It's a tie! Computer chose " + computerChoice);
            } else if (userChoice.equals("rock") && computerChoice.equals("scissors")
                    || userChoice.equals("paper") && computerChoice.equals("rock")
                    || userChoice.equals("scissors") && computerChoice.equals("paper")) {
                userScore++;
                resultLabel.setText("Result: You win! Computer chose " + computerChoice);
            } else {
                computerScore++;
                resultLabel.setText("Result: Computer wins! Computer chose " + computerChoice);
            }

            // Update the score
            scoreLabel.setText("Score: You " + userScore + " - " + computerScore + " Computer");
        }
    }

    public static void main(String[] args) {
        // Run the UI
        SwingUtilities.invokeLater(() -> {
            RockPaperScissorsUI game = new RockPaperScissorsUI();
            game.setVisible(true);
        });
    }
}
