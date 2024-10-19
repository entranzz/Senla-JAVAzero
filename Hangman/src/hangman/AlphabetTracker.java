package hangman;

import java.util.HashSet;
import java.util.Set;

public class AlphabetTracker {
    private Set<Character> correctLetters;
    private Set<Character> incorrectLetters;
    private final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";

    public AlphabetTracker() {
        correctLetters = new HashSet<>();
        incorrectLetters = new HashSet<>();
    }


    public void addCorrectLetter(char letter) {
        correctLetters.add(letter);
    }


    public void addIncorrectLetter(char letter) {
        incorrectLetters.add(letter);
    }


    public void displayAlphabet() {
        System.out.println("ALPHABET:");
        for (char letter : ALPHABET.toCharArray()) {
            if (correctLetters.contains(letter)) {
                System.out.print(letter + "(✔) "); // Отмечаем правильные буквы
            } else if (incorrectLetters.contains(letter)) {
                System.out.print(letter + "(✘) "); // Отмечаем неправильные буквы
            } else {
                System.out.print(letter + " "); // Неиспользованные буквы
            }
        }
        System.out.println();
    }
    public boolean isLetterUsed(char letter) {
        return correctLetters.contains(letter) || incorrectLetters.contains(letter);
    }

}
