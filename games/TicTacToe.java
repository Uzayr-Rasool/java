import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        String board[] = {"1","2","3","4","5","6","7","8","9"};
        String p1Name, p2Name;
        String symbol1 = "X", symbol2 = "O";
        String tossChoice, tossResult;

        System.out.println("\n\t\tWelcome to TicTac Game\t\n");

        System.out.print("\nEnter player 1 name : ");
        p1Name = input.next();

        System.out.print("Enter player 2 name : ");
        p2Name = input.next();

        System.out.println("\n" + p1Name + " will flip the coin");

        do {
            System.out.print(p2Name + " choose (heads/tails): ");
            tossChoice = input.next().toLowerCase();

            if (!(tossChoice.equals("heads") || tossChoice.equals("tails"))) {
                System.out.println("Invalid choice! Try again.");
            }

        } while (!(tossChoice.equals("heads") || tossChoice.equals("tails")));

        int randToss = (int)(Math.random() * 2);
        tossResult = (randToss == 0) ? "heads" : "tails";

        System.out.println("\nCoin landed on: " + tossResult);

        String currentPlayer, currentSymbol;

        if (tossChoice.equals(tossResult)) {
            System.out.println(p2Name + " won the toss!");
            currentPlayer = p2Name;
            currentSymbol = symbol2;
        } else {
            System.out.println(p1Name + " won the toss!");
            currentPlayer = p1Name;
            currentSymbol = symbol1;
        }

        System.out.println(currentPlayer + " will start first with symbol " + currentSymbol);

        int count = 0;
        boolean win = false;
        int move, index;

        while (count < 9 && !win) {

            System.out.println("\n__ " + board[0] + " __ | __ " + board[1] + " __ | __ " + board[2] + " __");
            System.out.println("__ " + board[3] + " __ | __ " + board[4] + " __ | __ " + board[5] + " __");
            System.out.println("__ " + board[6] + " __ | __ " + board[7] + " __ | __ " + board[8] + " __");

            while (true) {
                System.out.print(currentPlayer + " , enter your move : ");
                move = input.nextInt();

                if (move >= 1 && move <= 9) {
                    index = move - 1;

                    if (board[index].equals("X") || board[index].equals("O")) {
                        System.out.println("This spot is already taken! Try again.");
                    } else {
                        break; 
                    }
                } else {
                    System.out.println("Invalid move! Enter 1-9.");
                }
            }

            board[index] = currentSymbol;
            count++;

            if (
                (board[0].equals(board[1]) && board[1].equals(board[2])) ||
                (board[3].equals(board[4]) && board[4].equals(board[5])) ||
                (board[6].equals(board[7]) && board[7].equals(board[8])) ||
                (board[0].equals(board[3]) && board[3].equals(board[6])) ||
                (board[1].equals(board[4]) && board[4].equals(board[7])) ||
                (board[2].equals(board[5]) && board[5].equals(board[8])) ||
                (board[0].equals(board[4]) && board[4].equals(board[8])) ||
                (board[2].equals(board[4]) && board[4].equals(board[6]))
            ) {
                win = true;

                System.out.println("\n__ " + board[0] + " __ | __ " + board[1] + " __ | __ " + board[2] + " __");
                System.out.println("__ " + board[3] + " __ | __ " + board[4] + " __ | __ " + board[5] + " __");
                System.out.println("__ " + board[6] + " __ | __ " + board[7] + " __ | __ " + board[8] + " __");

                System.out.println("\n " + currentPlayer + " wins the game!");
                break;
            }


            if (currentPlayer.equals(p1Name)) {
                currentPlayer = p2Name;
                currentSymbol = symbol2;
            } else {
                currentPlayer = p1Name;
                currentSymbol = symbol1;
            }
        }

        if (!win) {
            System.out.println("\nIt's a draw!");
        }

        input.close();
    }
}
