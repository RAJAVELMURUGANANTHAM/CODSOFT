package CodSoft;

import java.util.Scanner;
import java.util.Random;

public class Number_Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int minRange = 1;
        int maxRange = 100;
        int attemptsLimit = 7;
        int score = 0;
        int NumberofRound = 0;

        System.out.println("Welcome to Guess the Number game!");

        boolean playAgain = true;
        while (playAgain) {
            int NumberToGuess = random.nextInt(maxRange - minRange + 1) + minRange;
            int attempts = 0;
            boolean guessedCorrectly = false;

            System.out.println(
                    "\nI have generated a number between " + minRange + " and " + maxRange
                            + ". Try to guess the Number");

            while (!guessedCorrectly && attempts < attemptsLimit) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;

                if (userGuess == NumberToGuess) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score++;
                    guessedCorrectly = true;
                } else if (userGuess < NumberToGuess) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The number was: " + NumberToGuess);
            }

            boolean validInput = false;
            while (!validInput) {
                System.out.print("Would you like to play again? (yes/no): ");
                String playChoice = scanner.next().toLowerCase();
                if (playChoice.equals("yes")) {
                    validInput = true;
                } else if (playChoice.equals("no")) {
                    validInput = true;
                    playAgain = false;
                } else {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }
            }
            NumberofRound++;
        }

        System.out.println("Your final score: " + score + " Out of " + NumberofRound);
        if ((NumberofRound - score) > score) {
            System.out.println("Computer has won the game.");
        } else if ((NumberofRound - score) < score) {
            System.out.println("Congratulations! You have won the game.");
        } else {
            System.out.println("The game ends in a draw.");
        }
        System.out.println("Thank you for playing Guess the Number!");
        scanner.close();
    }
}
