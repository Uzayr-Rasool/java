import java.util.Scanner;

public class CoffeeMachine {

    static Scanner input = new Scanner(System.in);
    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static double money = 50.0;

    public static void buyCoffee() {
        int choice = 0;
        System.out.println("What do you want to buy? 1 - Espresso, 2 - Latte, 3 - Cappuccino:");

        if (input.hasNextInt()) {
            choice = input.nextInt();
            if (choice == 1) {
                if (water < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (beans < 16) {
                    System.out.println("Sorry, not enough beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    water -= 250;
                    beans -= 16;
                    cups -= 1;
                    money += 4.0;
                    System.out.println("Making your coffee... Enjoy!");
                }
            } else if (choice == 2) {
                if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 20) {
                    System.out.println("Sorry, not enough beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    cups -= 1;
                    money += 7.0;
                    System.out.println("Making your coffee... Enjoy!");
                }
            } else if (choice == 3) {
                if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 12) {
                    System.out.println("Sorry, not enough beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough cups!");
                } else {
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    cups -= 1;
                    money += 6.0;
                    System.out.println("Making your coffee... Enjoy!");
                }
            }else{
                System.out.println("Invalid Choice!");
            }
        } else {
            System.out.println("Wrong type! Please enter an integer value (1-3) ");
            input.next();
            choice = 0;
        }

    }

    public static void takeMoney() {
        System.out.println("You received $" + money);
        money = 0;
    }

    public static void printStatus() {
        System.out.println("--- CURRENT STATUS ---");
        System.out.println("Water: " + water + " ml");
        System.out.println("Milk: " + milk + " ml");
        System.out.println("Beans: " + beans + " g");
        System.out.println("Cups: " + cups);
        System.out.println("Money: $" + money);
    }

    public static void main(String[] args) {
        int choice = 0;

        do {
            System.out.println();
            System.out.println("=== COFFEE MACHINE MENU ===");
            System.out.println("1. Buy a coffee");
            System.out.println("2. Take the money");
            System.out.println("3. Check machine status");
            System.out.println("4. Turn off machine");
            System.out.println("===========================");

            System.out.print("What would you like to do? (1-4): ");
            if (input.hasNextInt()) {
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        buyCoffee();
                        break;
                    case 2:
                        takeMoney();
                        break;
                    case 3:
                        printStatus();
                        break;
                    case 4:
                        System.out.println("Turning off... Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            } else {
                System.out.println("Invalid type! Choice should be an integer value (1-4).");
                input.next();
                choice = 0;
            }
        } while (choice != 4);

    }
}
