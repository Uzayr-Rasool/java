import java.util.Scanner;

public class TemperatureConverter {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int choice, continueChoice;

        do {
            System.out.println("Temperature Converter");
            System.out.println("1. Celsius to Fahrenheit");
            System.out.println("2. Fahrenheit to Celsius");
            System.out.println("3. Celsius to Kelvin");
            System.out.println("4. Kelvin to Celsius");

            System.out.print("Enter your choice: ");
            choice = input.nextInt();

            if (choice >= 1 && choice <= 4) {

                if (choice == 1) {
                    System.out.print("Enter temperature in Celsius: ");
                    double celsius = input.nextDouble();
                    double fahrenheit = (celsius * 9.0 / 5) + 32;
                    System.out.printf("%.2f°C = %.2f°F\n", celsius, fahrenheit);
                }

                else if (choice == 2) {
                    System.out.print("Enter temperature in Fahrenheit: ");
                    double fahrenheit = input.nextDouble();
                    double celsius = (fahrenheit - 32) * 5.0 / 9;
                    System.out.printf("%.2f°F = %.2f°C\n", fahrenheit, celsius);
                }

                else if (choice == 3) {
                    System.out.print("Enter temperature in Celsius: ");
                    double celsius = input.nextDouble();
                    double kelvin = celsius + 273.15;
                    System.out.printf("%.2f°C = %.2f K\n", celsius, kelvin);
                }

                else if (choice == 4) {
                    System.out.print("Enter temperature in Kelvin: ");
                    double kelvin = input.nextDouble();
                    double celsius = kelvin - 273.15;
                    System.out.printf("%.2f K = %.2f°C\n", kelvin, celsius);
                }

            } else {
                System.out.println("Invalid choice! Please select between 1 and 4.");
            }

            System.out.print("\nDo you want to continue? Yes (1) / No (0): ");
            continueChoice = input.nextInt();

        } while (continueChoice == 1);

        input.close();
    }
}
