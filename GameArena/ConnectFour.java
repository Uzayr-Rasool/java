import java.util.Random;
import java.util.Scanner;

public class ConnectFour {

    public static void start(Scanner input) {

        Random rand = new Random();

        System.out.println("\n=====================================");
        System.out.println("         CONNECT FOUR GAME");
        System.out.println("=====================================");

        while (true) {

            System.out.println("\n1. With Friend");
            System.out.println("2. With Computer");
            System.out.println("3. Return to Game Arena menu");
            System.out.print("Choose option: ");

            String choice = input.nextLine();

            if (choice.equals("1") || choice.equalsIgnoreCase("Friend")) {
                withFriend(input, rand);
            }

            else if (choice.equals("2") || choice.equalsIgnoreCase("Computer")) {
                withComputer(input, rand);
            }

            else if (choice.equals("3") || choice.equalsIgnoreCase("Return")) {
                System.out.println("Returning to Game Arena menu...");
                return;
            }

            else {
                System.out.println("Invalid Choice!");
            }
        }
    }

    public static void resetGrid(String[][] grid) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                grid[i][j] = ".";
            }
        }
    }

    public static void printBoard(String[][] grid) {

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                System.out.print("| " + grid[i][j] + " ");
            }
            System.out.println("|");
        }

        System.out.println("-------------------------");
        System.out.println("  1   2   3   4   5   6");
    }

    public static boolean dropPiece(String[][] grid, int col, String symbol) {

        for (int row = 5; row >= 0; row--) {

            if (grid[row][col].equals(".")) {
                grid[row][col] = symbol;
                return true;
            }
        }

        return false;
    }

    public static boolean isBoardFull(String[][] grid) {

        for (int c = 0; c < 6; c++) {

            if (grid[0][c].equals(".")) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkWin(String[][] grid, String symbol) {

        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c].equals(symbol)
                        && grid[r][c + 1].equals(symbol)
                        && grid[r][c + 2].equals(symbol)
                        && grid[r][c + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        for (int c = 0; c < 6; c++) {
            for (int r = 0; r < 3; r++) {
                if (grid[r][c].equals(symbol)
                        && grid[r + 1][c].equals(symbol)
                        && grid[r + 2][c].equals(symbol)
                        && grid[r + 3][c].equals(symbol)) {
                    return true;
                }
            }
        }

        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c].equals(symbol)
                        && grid[r + 1][c + 1].equals(symbol)
                        && grid[r + 2][c + 2].equals(symbol)
                        && grid[r + 3][c + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        for (int r = 3; r < 6; r++) {
            for (int c = 0; c < 3; c++) {
                if (grid[r][c].equals(symbol)
                        && grid[r - 1][c + 1].equals(symbol)
                        && grid[r - 2][c + 2].equals(symbol)
                        && grid[r - 3][c + 3].equals(symbol)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static void withComputer(Scanner input, Random rand) {

        String[][] grid = new String[6][6];

        System.out.print("\nEnter Player Name: ");
        String player1 = input.nextLine();

        String player2 = "Computer";

        String symbol1 = "R";
        String symbol2 = "G";

        while (true) {

            resetGrid(grid);

            String currentPlayer;
            String currentSymbol;

            System.out.print(player1 + " choose Head/Tail: ");

            while (true) {

                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("Head") || choice.equalsIgnoreCase("Tail")) {

                    String coin = (rand.nextInt(2) == 0) ? "Head" : "Tail";

                    System.out.println("Coin Result: " + coin);

                    if (choice.equalsIgnoreCase(coin)) {
                        currentPlayer = player1;
                        currentSymbol = symbol1;
                    } else {
                        currentPlayer = player2;
                        currentSymbol = symbol2;
                    }

                    break;
                } else {
                    System.out.print("Invalid! Choose Head/Tail: ");
                }
            }

            boolean gameOver = false;

            while (!gameOver) {

                printBoard(grid);

                System.out.print("\n" + currentPlayer + " (" + currentSymbol + ") choose column (1-6): ");

                int col;

                if (currentPlayer.equals(player2)) {

                    col = -1;

                    for (int c = 0; c < 6; c++) {

                        if (!grid[0][c].equals("."))
                            continue;

                        for (int r = 5; r >= 0; r--) {

                            if (grid[r][c].equals(".")) {

                                grid[r][c] = symbol2;

                                if (checkWin(grid, symbol2)) {
                                    grid[r][c] = ".";
                                    col = c;
                                }

                                grid[r][c] = ".";
                                break;
                            }
                        }

                        if (col != -1)
                            break;
                    }

                    if (col == -1) {

                        for (int c = 0; c < 6; c++) {

                            if (!grid[0][c].equals("."))
                                continue;

                            for (int r = 5; r >= 0; r--) {

                                if (grid[r][c].equals(".")) {

                                    grid[r][c] = symbol1;

                                    if (checkWin(grid, symbol1)) {
                                        grid[r][c] = ".";
                                        col = c;
                                    }

                                    grid[r][c] = ".";
                                    break;
                                }
                            }

                            if (col != -1)
                                break;
                        }
                    }

                    if (col == -1) {
                        while (true) {

                            int temp = rand.nextInt(6);

                            if (grid[0][temp].equals(".")) {
                                col = temp;
                                break;
                            }
                        }
                    }

                    dropPiece(grid, col, currentSymbol);

                    System.out.println("\nComputer chose column: " + (col + 1));
                }

                else {

                    while (true) {

                        if (input.hasNextInt()) {

                            col = input.nextInt();
                            input.nextLine();

                            if (col >= 1 && col <= 6) {

                                col--;

                                if (!dropPiece(grid, col, currentSymbol)) {
                                    System.out.print("Column full! Try again: ");
                                    continue;
                                }

                                break;
                            }

                            else {
                                System.out.print("Enter 1-6: ");
                            }
                        }

                        else {
                            System.out.print("Invalid input: ");
                            input.nextLine();
                        }
                    }
                }

                if (checkWin(grid, currentSymbol)) {

                    printBoard(grid);

                    System.out.println("\n" + currentPlayer + " WON THE GAME!");

                    gameOver = true;
                }

                if (!gameOver && isBoardFull(grid)) {

                    printBoard(grid);

                    System.out.println("\nMATCH DRAW!");

                    gameOver = true;
                }

                if (!gameOver) {

                    if (currentPlayer.equals(player1)) {
                        currentPlayer = player2;
                        currentSymbol = symbol2;
                    }

                    else {
                        currentPlayer = player1;
                        currentSymbol = symbol1;
                    }
                }
            }

            System.out.print("\nPlay again with same players? (yes/no): ");
            String again = input.nextLine();

            if (!again.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }

    public static void withFriend(Scanner input, Random rand) {

        String[][] grid = new String[6][6];

        System.out.print("\nEnter Player 1 Name: ");
        String player1 = input.nextLine();

        System.out.print("Enter Player 2 Name: ");
        String player2 = input.nextLine();

        String symbol1 = "R";
        String symbol2 = "G";

        while (true) {

            resetGrid(grid);

            String currentPlayer;
            String currentSymbol;

            System.out.println("\n" + player1 + " will flip the coin.");
            System.out.print(player2 + " choose Head/Tail: ");

            while (true) {

                String choice = input.nextLine();

                if (choice.equalsIgnoreCase("Head") || choice.equalsIgnoreCase("Tail")) {

                    String coin = (rand.nextInt(2) == 0) ? "Head" : "Tail";

                    System.out.println("Coin Result: " + coin);

                    if (choice.equalsIgnoreCase(coin)) {
                        currentPlayer = player2;
                        currentSymbol = symbol2;
                    }

                    else {
                        currentPlayer = player1;
                        currentSymbol = symbol1;
                    }

                    break;
                }

                else {
                    System.out.print("Invalid! Choose Head/Tail: ");
                }
            }

            boolean gameOver = false;

            while (!gameOver) {

                printBoard(grid);

                System.out.print("\n" + currentPlayer + " (" + currentSymbol + ") choose column (1-6): ");

                int col;

                while (true) {

                    if (input.hasNextInt()) {

                        col = input.nextInt();
                        input.nextLine();

                        if (col >= 1 && col <= 6) {

                            col--;

                            if (!dropPiece(grid, col, currentSymbol)) {
                                System.out.print("Column full! Try again: ");
                                continue;
                            }

                            break;
                        }

                        else {
                            System.out.print("Enter 1-6: ");
                        }
                    }

                    else {
                        System.out.print("Invalid input: ");
                        input.nextLine();
                    }
                }

                if (checkWin(grid, currentSymbol)) {

                    printBoard(grid);

                    System.out.println("\n" + currentPlayer + " WON THE GAME!");

                    gameOver = true;
                }

                if (!gameOver && isBoardFull(grid)) {

                    printBoard(grid);

                    System.out.println("\nMATCH DRAW!");

                    gameOver = true;
                }

                if (!gameOver) {

                    if (currentPlayer.equals(player1)) {
                        currentPlayer = player2;
                        currentSymbol = symbol2;
                    }

                    else {
                        currentPlayer = player1;
                        currentSymbol = symbol1;
                    }
                }
            }

            System.out.print("\nPlay again with same players? (yes/no): ");
            String again = input.nextLine();

            if (!again.equalsIgnoreCase("yes")) {
                break;
            }
        }
    }
}
