import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int continueChoice;

        do {
            System.out.println("===== Currency Converter =====");
            System.out.println("1. USD to PKR");
            System.out.println("2. PKR to USD");
            System.out.println("3. USD to EUR");
            System.out.println("4. EUR to USD");
            System.out.println("5. USD to INR");
            System.out.println("6. INR to USD");
           
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();

            if (choice >= 1 && choice <= 6) {

                if (choice == 1) {
                    System.out.print("Enter amount in USD: ");
                    double USD = input.nextDouble();
                    double PKR = USD * 280.0;
                    System.out.printf("%.2f USD = %.2f PKR\n", USD, PKR);
                }

                else if (choice == 2) {
                    System.out.print("Enter amount in PKR: ");
                    double PKR = input.nextDouble();
                    double USD = PKR / 280.0;
                    System.out.printf("%.2f PKR = %.2f USD\n", PKR, USD);
                }

                else if (choice == 3) {
                    System.out.print("Enter amount in USD: ");
                    double USD = input.nextDouble();
                    double EUR = USD * 0.90;
                    System.out.printf("%.2f USD = %.2f EUR\n", USD, EUR);
                }

                else if (choice == 4) {
                    System.out.print("Enter amount in EUR: ");
                    double EUR = input.nextDouble();
                    double USD = EUR / 0.90;
                    System.out.printf("%.2f EUR = %.2f USD\n", EUR, USD);
                }

                else if (choice == 5) {
                    System.out.print("Enter amount in USD: ");
                    double USD = input.nextDouble();
                    double INR = USD * 92;
                    System.out.printf("%.2f USD = %.2f INR\n", USD, INR);
                }

                else if (choice == 6) {
                    System.out.print("Enter amount in INR: ");
                    double INR = input.nextDouble();
                    double USD = INR / 92;
                    System.out.printf("%.2f INR = %.2f USD\n", INR, USD);
                }

            } else {
                System.out.println("Invalid choice!");
            }

            System.out.print("\nDo you want to continue again? Yes (1) / No (0): ");
            continueChoice = input.nextInt();

        } while (continueChoice == 1);

        input.close();
        System.out.println("Thank you for using the Currency Converter!");
    }
}
