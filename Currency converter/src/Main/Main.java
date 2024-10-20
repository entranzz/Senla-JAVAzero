package Main;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueConversion = true;

        System.out.println("Welcome to the currency converter!");

        while (continueConversion) {

            Currency fromCurrency = getCurrencyChoice(scanner);
            if (fromCurrency == null) {
                System.out.println("Exiting the program. Goodbye!");
                break;
            }


            System.out.println("Enter the amount you want to convert:");
            double amount = getValidAmount(scanner);


            convertAndDisplay(amount, fromCurrency);
        }
        scanner.close();
    }


    public enum Currency {
        USD, EUR, GBP, JPY, BYN
    }


    private static final double[][] exchangeRates = {
            {1.0, 0.85, 0.75, 110.0, 2.6},  // USD to others
            {1.18, 1.0, 0.88, 129.41, 3.06},  // EUR to others
            {1.33, 1.14, 1.0, 146.9, 3.47},   // GBP to others
            {0.0091, 0.0077, 0.0068, 1.0, 0.0236}, // JPY to others
            {0.38, 0.33, 0.29, 42.37, 1.0}  // BYN to others
    };




    private static void convertAndDisplay(double amount, Currency fromCurrency) {
        int fromIndex = fromCurrency.ordinal();

        System.out.printf("Converting %,.2f %s:%n", amount, fromCurrency);

        for (Currency toCurrency : Currency.values()) {
            if (fromCurrency != toCurrency) {
                double convertedAmount = convertCurrency(amount, fromIndex, toCurrency.ordinal());
                printFormatted(amount, fromCurrency.toString(), convertedAmount, toCurrency.toString());
            }
        }
    }


    private static double convertCurrency(double amount, int fromIndex, int toIndex) {
        return amount * exchangeRates[fromIndex][toIndex];
    }


    private static Currency getCurrencyChoice(Scanner scanner) {
        while (true) {
            System.out.println("\nChoose a currency to convert from:");
            for (int i = 0; i < Currency.values().length; i++) {
                System.out.println((i + 1) + ". " + Currency.values()[i]);
            }
            System.out.println("0. Exit");

            int choice = getValidChoice(scanner);

            if (choice == 0) {
                return null;
            } else if (choice > 0 && choice <= Currency.values().length) {
                return Currency.values()[choice - 1];
            } else {
                System.out.println("Please choose a valid option.");
            }
        }
    }


    private static int getValidChoice(Scanner scanner) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 0 && choice <= 5) {
                    break;
                } else {
                    System.out.println("Please enter a valid option (0-5).");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 0 and 5.");
            }
        }
        return choice;
    }


    private static double getValidAmount(Scanner scanner) {
        double amount;
        while (true) {
            try {
                amount = Double.parseDouble(scanner.nextLine());
                if (amount < 0) {
                    System.out.println("Amount cannot be negative. Please enter a positive number.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
            }
        }
        return amount;
    }


    private static void printFormatted(double amount, String fromCurrency, double convertedAmount, String toCurrency) {
        System.out.printf("%,.2f %s = %,.2f %s%n", amount, fromCurrency, convertedAmount, toCurrency);
    }
}
