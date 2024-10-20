package Main;

import java.security.SecureRandom;
import java.util.Scanner;

public class Main {

   
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_+=<>?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SecureRandom random = new SecureRandom();

        System.out.println("Welcome to the Password Generator!");


        int passwordLength = getValidPasswordLength(scanner);


        String password = generatePassword(passwordLength, random);
        System.out.println("Generated Password: " + password);

        scanner.close();
    }


    private static int getValidPasswordLength(Scanner scanner) {
        int length;
        while (true) {
            System.out.println("Enter the desired password length (8-12): ");
            try {
                length = Integer.parseInt(scanner.nextLine());
                if (length >= 8 && length <= 12) {
                    break;
                } else {
                    System.out.println("Please enter a length between 8 and 12.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 8 and 12.");
            }
        }
        return length;
    }


    private static String generatePassword(int length, SecureRandom random) {
        String allCharacters = UPPERCASE + LOWERCASE + DIGITS + SPECIAL_CHARACTERS;
        StringBuilder password = new StringBuilder();


        password.append(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        password.append(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL_CHARACTERS.charAt(random.nextInt(SPECIAL_CHARACTERS.length())));


        for (int i = 4; i < length; i++) {
            password.append(allCharacters.charAt(random.nextInt(allCharacters.length())));
        }


        return shuffleString(password.toString(), random);
    }


    private static String shuffleString(String input, SecureRandom random) {
        char[] characters = input.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = random.nextInt(characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
}
