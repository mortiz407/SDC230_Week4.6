/*Name: Manuel Ortiz
 *Date: 05/03/2026
 *Assignment: SDC230 Performance Assessment - Account Balance Calculations
 *Description: This program simulates a simple bank account system.
 *It allows the user to enter a starting balance and then apply credits
 *or debits. A user-defined exception prevents the balance from going negative.
 *It also handles invalid (non-numeric) input.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

// Custom message for when balance would go negative
class NegativeBalanceException extends Exception {
    public NegativeBalanceException(String message) {
        super(message);
    }
}

public class App {

    public static void main(String[] args) {

        // Reads user input
        Scanner scanner = new Scanner(System.in);

        System.out.println("Manuel Ortiz - Week 4 PA Account Balance Calculations.");
        System.out.println();

        // Balabce starts at zero
        double balance = 0;

        // Asking for starting balance and keeps asking until when true
        while (true) {
            try {
                System.out.print("Enter starting balance: ");
                balance = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Exception: " + e.getClass().getSimpleName());
                System.out.println("Error: Please enter a valid number.");

                // Clear invalid input
                scanner.next();
                System.out.println();
            }
        }

        while (true) {
            try {
                System.out.print("Enter credit (+) or debit (-), or 0 to quit: ");
                double amount = scanner.nextDouble();

                if (amount == 0) {
                    break;
                }

                // Check for negative balance
                if (balance + amount < 0) {
                    throw new NegativeBalanceException(
                            "Amount entered will cause account to be negative");
                }

                // Update balance
                balance += amount;
                System.out.printf("Updated balance: %.2f%n", balance);
                System.out.println();

            } catch (InputMismatchException e) {
                // Runs if user enters a letter like "four"
                System.out.println("Exception: " + e.getClass().getSimpleName());
                System.out.println("Please enter a numeric value.");

                // clear invalid input
                scanner.next();
                System.out.println();

            } catch (NegativeBalanceException e) {
                // Runs if balance is below zero
                System.out.println("Exception: " + e.getClass().getSimpleName() + ": " + e.getMessage());
                System.out.println();
            }
        }

        System.out.printf("Final balance: %.2f%n", balance);
        scanner.close();
    }
}