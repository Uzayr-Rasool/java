import java.util.Scanner;

public class HangmanGame {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);

      String ToGuess = "";
      String NewGuess = "";
      String guessedLetters = "";  
      int guessesLeft = 6;

      System.out.print("Enter the word: ");
      String word = input.next().toLowerCase();


  
      for (int i = 0; i < word.length(); i++) {
         ToGuess = ToGuess + "_";
      }

      System.out.println("Word: " + ToGuess);
      System.out.println("Now guess the word letter by letter");


      while (!ToGuess.equals(word) && guessesLeft > 0) {

         System.out.println("Guessed letters: " + guessedLetters);
         System.out.print("Enter a letter: ");
         String guessed = input.next().toLowerCase();


         if (guessed.length() != 1 || !Character.isLetter(guessed.charAt(0))) {
            System.out.println(" Enter only ONE letter!");
            continue;
         }

         char letter = guessed.charAt(0);

         if (guessedLetters.indexOf(letter) != -1) {
            System.out.println(" You already guessed this letter!");
            continue;
         }

         guessedLetters = guessedLetters + letter + " ";

         boolean found = false;
         NewGuess = "";

         for (int i = 0; i < word.length(); i++) {

            if (letter == word.charAt(i)) {
               NewGuess = NewGuess + word.charAt(i);
               found = true;
            } 
            else if (ToGuess.charAt(i) != '_') {
               NewGuess = NewGuess + ToGuess.charAt(i);
            } 
            else {
               NewGuess = NewGuess + "_";
            }
         }


         if (!found) {
            guessesLeft--;
            System.out.println(" Wrong guess!");
         } else {
            System.out.println(" Good guess!");
         }

         ToGuess = NewGuess;

         System.out.println("Word: " + ToGuess);
         System.out.println("Guesses left: " + guessesLeft);
         System.out.println();
      }

   
      if (ToGuess.equals(word)) {
         System.out.println(" You guessed the word!");
      } else {
         System.out.println(" You lost! The word was: " + word);
      }

      input.close();
   }
}
