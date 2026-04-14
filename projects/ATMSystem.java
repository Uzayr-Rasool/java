import java.util.*;

public class ATMSystem {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int balance = 10000;
        int PIN;
        int correctPIN = 1122;
        int attempts = 3;

        while (true) {

            if (attempts <= 0) {
                System.out.println("Card Blocked! Too many incorrect attempts.");
                break;
            }

            System.out.print("\nEnter your PIN: ");

            if (input.hasNextInt()) {
                PIN = input.nextInt();

                if (PIN == correctPIN) {

                    attempts = 3;
                    System.out.println("\nAccess Granted!");

                    int choice;

                    
                    do {

                        System.out.println("\n--- ATM Menu ---");
                        System.out.println("1. Deposit Balance");
                        System.out.println("2. Withdraw Balance");
                        System.out.println("3. Check Balance");
                        System.out.println("4. Exit");
                        System.out.print("Choose an option: ");

                        if (input.hasNextInt()) {
                            choice = input.nextInt();

                            System.out.println();
                            
                            switch (choice) {
                                case 1:
                                    balance = depositBalance(balance);
                                    break;
                                case 2:
                                    balance = withdrawBalance(balance);
                                    break;
                                case 3:
                                    checkBalance(balance);
                                    break;
                                case 4:
                                    System.out.println("Exiting... Thank you!");
                                    break;
                                default:
                                    System.out.println("Invalid Choice!");
                            }

                        } else {
                            System.out.println("Invalid input type!");
                            input.next();
                            choice = 0;
                        }

                    } while (choice != 4); 

                    break; 

                } else {
                    attempts--;
                    System.out.println("Incorrect PIN! " + attempts + " attempts left.");
                }

            } else {
                System.out.println("Invalid PIN format! Please enter numbers only.");
                input.next();
                attempts--;
                System.out.println(attempts + " attempts left.");
            }
        }
    }

    public static int depositBalance(int balance) {

        System.out.print("Enter amount to deposit: ");

        if (input.hasNextInt()) {

            int amount = input.nextInt();

            if (amount > 0) {
                balance += amount;
                System.out.println("Successfully deposited: $" + amount);
            } else {
                System.out.println("Enter a valid amount!");
            }

        } else {
            System.out.println("Invalid amount!");
            input.next();
        }

        return balance;
    }

    public static int withdrawBalance(int balance) {

        System.out.print("Enter amount to withdraw: ");

        if (input.hasNextInt()) {

            int amount = input.nextInt();

            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Successfully withdrawn: $" + amount);
            } else {
                System.out.println("Invalid or insufficient balance!");
            }

        } else {
            System.out.println("Invalid amount!");
            input.next();
        }

        return balance;
    }

    public static void checkBalance(int balance) {

        System.out.println("Current balance: $" + balance);
    }
}
