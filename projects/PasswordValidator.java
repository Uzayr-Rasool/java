import java.util.Random;
import java.util.Scanner;

public class PasswordValidator {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n--- PASSWORD SYSTEM ---");
            System.out.println("1. Generate Random Password");
            System.out.println("2. Set Your Own Password");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            if (input.hasNextInt()) {

                choice = input.nextInt();

                if (choice == 1) {

                    String password = generatePassword();
                    System.out.println("Generated Password: " + password);

                    System.out.print("Do you want to validate it? (y/n): ");
                    String option = input.next();

                    if (option.equalsIgnoreCase("y")) {

                        if (checkPassword(password)) {

                            System.out.println("Strong password!");

                        } else {

                            System.out.println("Weak password!");
                        }
                    }

                } else if (choice == 2) {

                    boolean result = false;

                    while (!result) {

                        System.out.print("Enter Password: ");
                        String password = input.next();

                        result = checkPassword(password);

                        if (result) {

                            System.out.println("Strong password! Successfully set.");
                        }
                    }

                } else if (choice == 3) {

                    System.out.println("Exiting... Thank you!");

                } else {

                    System.out.println("Invalid choice!");
                }

            } else {

                System.out.println("Invalid input type!");
                input.next();
                choice = 0;
            }

        } while (choice != 3);
    }

    public static boolean checkPassword(String password) {

        int digit = 0, uppercase = 0, lowercase = 0, special = 0;

        if (password.length() < 8) {

            System.out.println("Too short! Minimum 8 characters required.");
            return false;

        }

        for (int i = 0; i < password.length(); i++) {

            char ch = password.charAt(i);

            if (Character.isDigit(ch)) {

                digit++;

            } else if (Character.isUpperCase(ch)) {

                uppercase++;

            } else if (Character.isLowerCase(ch)) {

                lowercase++;

            } else {

                special++;

            }
        }

        if (digit < 1) {

            System.out.println("Must contain at least 1 digit!");
            return false;

        } else if (uppercase < 1) {

            System.out.println("Must contain at least 1 uppercase letter!");
            return false;

        } else if (lowercase < 1) {

            System.out.println("Must contain at least 1 lowercase letter!");
            return false;

        } else if (special < 1) {

            System.out.println("Must contain at least 1 special character!");
            return false;

        }

        return true;
    }

    public static String generatePassword() {

        String uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercase = "abcdefghijklmnopqrstuvwxyz";
        String special = "!@#$%^&*?:>.<,/|+_-";
        String digit = "0123456789";

        String allChars = uppercase + lowercase + special + digit;

        Random random = new Random();

        String password = "";

        password += uppercase.charAt(random.nextInt(uppercase.length()));
        password += lowercase.charAt(random.nextInt(lowercase.length()));
        password += digit.charAt(random.nextInt(digit.length()));
        password += special.charAt(random.nextInt(special.length()));

        for (int i = 1; i <= 4; i++) {
            password += allChars.charAt(random.nextInt(allChars.length()));
        }

        return password;
    }
}
