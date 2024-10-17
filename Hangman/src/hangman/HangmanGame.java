package hangman;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class HangmanGame {

    private static final String[] WORDS = {
            "programming", "java", "hangman", "encapsulation",
            "inheritance", "polymorphism", "abstraction", "interface",
            "constructor", "exception", "algorithm", "variable", "function",
            "compile", "debugging", "synchronization",
            "iterator", "collection", "stream", "lambda", "recursion",
            "stack", "queue", "graph",  "optimization",
            "package", "singleton", "prototype", "dependency", "reflection"
    };

    private static final int MAX_LIVES = 6;

    private String wordToGuess;
    private char[] guessedWord;
    private int livesLeft;
    private boolean isGameWon;

    private HangmanDrawer hangmanDrawer;
    private AlphabetTracker alphabetTracker;

    public HangmanGame() {
        this.wordToGuess = selectRandomWord();
        this.guessedWord = new char[wordToGuess.length()];
        Arrays.fill(guessedWord, '_');
        this.livesLeft = MAX_LIVES;
        this.isGameWon = false;
        this.hangmanDrawer = new HangmanDrawer();
        this.alphabetTracker = new AlphabetTracker();
    }

    private String selectRandomWord() {
        Random random = new Random();
        return WORDS[random.nextInt(WORDS.length)];
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        while (livesLeft > 0 && !isGameWon) {
            displayCurrentState();
            hangmanDrawer.drawHangman(livesLeft);
            alphabetTracker.displayAlphabet();

            char guessedLetter = getValidInput(scanner);

            if (!processGuess(guessedLetter)) {
                livesLeft--;
                alphabetTracker.addIncorrectLetter(guessedLetter);
                System.out.println("Incorrect! Lives remaining: " + livesLeft);
            } else {
                alphabetTracker.addCorrectLetter(guessedLetter);
            }

            if (checkWin()) {
                isGameWon = true;
                System.out.println("Congratulations! You guessed the word: " + wordToGuess);
                hangmanDrawer.drawVictory();
            } else if (livesLeft == 0) {
                System.out.println("Game over! The word was: " + wordToGuess);
                hangmanDrawer.drawHangman(livesLeft);
            }
        }
        scanner.close();
    }

    private char getValidInput(Scanner scanner) {
        String input;
        do {
            System.out.println("Enter one letter: ");
            input = scanner.nextLine().toLowerCase().trim();
            if (input.length() != 1 || !Character.isLetter(input.charAt(0))) {
                System.out.println("Invalid input! Enter one letter.");
            }
        } while (input.length() != 1 || !Character.isLetter(input.charAt(0)));
        return input.charAt(0);
    }

    private void displayCurrentState() {
        System.out.println("Current word: ");
        for (char c : guessedWord) {
            System.out.print(c + " ");
        }
        System.out.println();
    }

    private boolean processGuess(char letter) {
        boolean isCorrect = false;
        for (int i = 0; i < wordToGuess.length(); i++) {
            if (wordToGuess.charAt(i) == letter) {
                guessedWord[i] = letter;
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    private boolean checkWin() {
        for (char c : guessedWord) {
            if (c == '_') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        HangmanGame game = new HangmanGame();
        game.play();
    }
}
