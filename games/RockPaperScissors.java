import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int computerChoice = (int)(Math.random() * 3);

        System.out.println("Rock Paper Scissors");
        System.out.print("Enter 0 for Scissor, 1 for Rock and 2 for Paper: ");
        int userChoice = input.nextInt();

        if (userChoice < 0 || userChoice > 2) {
            System.out.println("Invalid input! Please enter 0, 1, or 2.");
            input.close();
            return;
        }

        String userChoiceText;
        if (userChoice == 0) {
            userChoiceText = "Scissor";
        } else if (userChoice == 1) {
            userChoiceText = "Rock";
        } else {
            userChoiceText = "Paper";
        }

        String computerChoiceText;
        if (computerChoice == 0) {
            computerChoiceText = "Scissor";
        } else if (computerChoice == 1) {
            computerChoiceText = "Rock";
        } else {
            computerChoiceText = "Paper";
        }

        System.out.println("You chose: " + userChoiceText);
        System.out.println("Computer chose: " + computerChoiceText);

        if (userChoice == computerChoice) {
            System.out.println("Result: It's a draw!");
        } 
        else if ((userChoice == 0 && computerChoice == 2) ||
                 (userChoice == 1 && computerChoice == 0) ||
                 (userChoice == 2 && computerChoice == 1)) {
            System.out.println("Result: You win!");
        } 
        else {
            System.out.println("Result: You lose!");
        }

        input.close();
        System.out.println("\nThank you for playing!");
    }
}
