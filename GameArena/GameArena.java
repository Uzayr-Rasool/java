import java.util.Scanner;

public class GameArena {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n\n=====Welcome to GameArena=====");
        
        while (true) {
            System.out.println("\n======MENU======");
            System.out.println("1. Word Scramble");
            System.out.println("2. Connect Four");
            System.out.println("3. Exit");
            System.out.print("Pick an option: ");
            
            String choice = input.nextLine();

            if (choice.equals("1") || choice.equalsIgnoreCase("Word Scramble")) {
                WordScramble.start(input); 
            } 
            else if (choice.equals("2") || choice.equalsIgnoreCase("Connect Four")) {
                ConnectFour.start(input);
            } 
            else if (choice.equals("3") || choice.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting Arena. Goodbye!");
                return;
            } 
            else {
                System.out.println("Invalid Choice");
            }
        }
    }
}