import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class WordScramble {

    static final String SCORE_FILE = "highscore.txt";

    public static void start(Scanner input) {

        while (true) {
            System.out.println("\n=====================================");
            System.out.println("         WORD SCRAMBLE HUB");
            System.out.println("=====================================");
            System.out.println("1. Play Game");
            System.out.println("2. Check Rules");
            System.out.println("3. View High Score");
            System.out.println("4. Back to GameArena");
            System.out.print("Choose an option: ");

            String menuChoice = input.nextLine().trim();

            if (menuChoice.equals("1")) {
                runGameLoop(input); 
            } 
            else if (menuChoice.equals("2")) {
                printRules();
            } 
            else if (menuChoice.equals("3")) {
                showHighScore();
            } 
            else if (menuChoice.equals("4") || menuChoice.equalsIgnoreCase("exit")) {
                System.out.println("Returning to GameArena...");
                return; 
            } 
            else {
                System.out.println("Invalid choice. Try again!");
            }
        }
    }

    private static void printRules() {
        System.out.println("\n=========== RULES ===========");
        System.out.println("1. Total Game Time = 60 Seconds");
        System.out.println("2. Each Word Time = 10 Seconds");
        System.out.println("3. Correct Answer = +1 Point");
        System.out.println("4. Unlimited Tries Within Time");
        System.out.println("5. Type 'pass' to skip a word");
        System.out.println("=============================");

    }

    private static void runGameLoop(Scanner input) {
        Random rand = new Random();

        System.out.println("\n--- Categories ---");
        System.out.println("1. Animals");
        System.out.println("2. Countries");
        System.out.println("3. Sports");
        System.out.print("Pick one category: ");

        String catChoice = input.nextLine();
        String fileName = "";

        if (catChoice.equals("1") || catChoice.equalsIgnoreCase("Animals")) {
            fileName = "animals.txt";
        } else if (catChoice.equals("2") || catChoice.equalsIgnoreCase("Countries")) {
            fileName = "countries.txt";
        } else if (catChoice.equals("3") || catChoice.equalsIgnoreCase("Sports")) {
            fileName = "sports.txt";
        } else {
            System.out.println("Invalid category!");
            return;
        }

        System.out.println("\n--- Select Difficulty ---");
        System.out.println("1. Easy");
        System.out.println("2. Normal");
        System.out.println("3. Difficult");
        System.out.print("Select : ");

        String diffChoice = input.nextLine();

        if (!(diffChoice.equals("1")
                || diffChoice.equals("2")
                || diffChoice.equals("3")
                || diffChoice.equalsIgnoreCase("Easy")
                || diffChoice.equalsIgnoreCase("Normal")
                || diffChoice.equalsIgnoreCase("Difficult"))) {

            System.out.println("Invalid difficulty!");
            return;
        }

        String[] words = new String[100];
        int wordCount = 0;

        try {
            File file = new File(fileName);
            Scanner read = new Scanner(file);
            int lineNum = 0;

            while (read.hasNextLine() && wordCount < words.length) {
                String word = read.nextLine().trim();

                if (!word.isEmpty()) {
                    lineNum++;

                    if ((diffChoice.equals("1") || diffChoice.equalsIgnoreCase("Easy")) && lineNum <= 30) {
                        words[wordCount++] = word;
                    }
                    else if ((diffChoice.equals("2") || diffChoice.equalsIgnoreCase("Normal")) && lineNum > 30 && lineNum <= 60) {
                        words[wordCount++] = word;
                    }
                    else if ((diffChoice.equals("3") || diffChoice.equalsIgnoreCase("Difficult")) && lineNum > 60) {
                        words[wordCount++] = word;
                    }
                }
            }
            read.close();

            if (wordCount == 0) {
                System.out.println("No words found!");
                return;
            }

            System.out.print("\nEnter Your Name: ");
            String playerName = input.nextLine();

            int score = 0;
            long gameEndTime = System.currentTimeMillis() + 60000;

            System.out.println("\n===== 60 SECOND SPEED RUN START =====");

            boolean gameover = false;
            while (System.currentTimeMillis() < gameEndTime && !gameover) {
                String targetWord = words[rand.nextInt(wordCount)];
                String scrambled = scrambleWord(targetWord, rand);
                long wordEndTime = System.currentTimeMillis() + 10000;

                while (true) {
                    long remainingGameTime = (gameEndTime - System.currentTimeMillis()) / 1000;
                    long remainingWordTime = (wordEndTime - System.currentTimeMillis()) / 1000;

                    if (remainingGameTime <= 0) {
                        System.out.println("\nOverall game time finished!");
                        gameover = true;
                        break;
                    }

                    if (remainingWordTime <= 0) {
                        System.out.println("Too slow! Word was: " + targetWord);
                        break;
                    }

                    System.out.println("\nGame Time Left: " + remainingGameTime + " sec");
                    System.out.println("Word Time Left: " + remainingWordTime + " sec");
                    System.out.print("Guess (" + scrambled + ") or pass : ");

                    String guess = input.nextLine();

                    if (guess.equalsIgnoreCase("pass")) {
                        System.out.println("Skipped! The word was: " + targetWord);
                        break;
                    }

                    if (guess.equalsIgnoreCase(targetWord)) {
                        System.out.println("Correct! +1 Point");
                        score++;
                        break;
                    } else {
                        System.out.println("Wrong! Try Again.");
                    }
                }
            }

            System.out.println("\n=================================");
            System.out.println("           GAME OVER");
            System.out.println("=================================");
            System.out.println("Player Name : " + playerName);
            System.out.println("Final Score : " + score);

            saveHighScore(playerName, score);

        } catch (FileNotFoundException e) {
            System.out.println("Missing File: " + fileName);
        }
    }

    public static String scrambleWord(String word, Random rand) {
        if (word.length() <= 1) {
            return word;
        }

        char[] letters = word.toCharArray();
        String scrambled;

        do {
            for (int i = 0; i < letters.length; i++) {
                int swapIndex = rand.nextInt(letters.length);
                char temp = letters[i];
                letters[i] = letters[swapIndex];
                letters[swapIndex] = temp;
            }
            scrambled = new String(letters);
        } while (scrambled.equalsIgnoreCase(word));

        return scrambled;
    }

    public static void saveHighScore(String playerName, int score) {
        String bestPlayer = "";
        int bestScore = 0;

        try {
            File file = new File(SCORE_FILE);

            if (file.exists()) {
                Scanner read = new Scanner(file);
                if (read.hasNextLine()) {
                    bestPlayer = read.nextLine();
                }
                if (read.hasNextLine()) {
                    bestScore = Integer.parseInt(read.nextLine());
                }
                read.close();
            }

            if (score > bestScore) {
                PrintWriter write = new PrintWriter(file);
                write.println(playerName);
                write.println(score);
                write.close();
                System.out.println("\nNEW HIGH SCORE!");
            } else {
                System.out.println("\nCurrent High Score: " + bestScore + " by " + bestPlayer);
            }
        } catch (Exception e) {
            System.out.println("Error Saving High Score.");
        }
    }

    public static void showHighScore() {
        try {
            File file = new File(SCORE_FILE);

            if (!file.exists()) {
                System.out.println("\nNo High Score Yet!");
                return;
            }

            Scanner read = new Scanner(file);
            String player = read.nextLine();
            int score = Integer.parseInt(read.nextLine());
            read.close();

            System.out.println("\n===== HIGH SCORE =====");
            System.out.println("Player : " + player);
            System.out.println("Score  : " + score);
        } catch (Exception e) {
            System.out.println("Error Reading High Score.");
        }
    }
}
