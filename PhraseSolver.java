/*
 * Activity 2.2.2
 *
 *  The PhraseSolver class the PhraseSolverGame
 */
import java.util.Scanner;
  
public class PhraseSolver
{
  /* your code here - attributes */
  private Player player1;
  private Player player2;
  private Board board;
  private boolean solved;

  /* your code here - constructor(s) */ 
  public PhraseSolver(){
    player1 = new Player();
    player2 = new Player();
    board = new Board();
    solved = false;
  }

  public void play() {
    Scanner input = new Scanner(System.in);

    System.out.println("\nLetter Guessing Game Started!");

    Player currentPlayer = player1;

    while (!solved) {
      // Set a new value for the current letter at the start of each turn
      board.setLetterValue();
      System.out.println("\nPhrase: " + board.getSolvedPhrase());
      System.out.println("Current Letter Value: " + board.getCurrentLetterValue());
      System.out.println("Player 1 (" + player1.getName() + "): " + player1.getPoints() + " points");
      System.out.println("Player 2 (" + player2.getName() + "): " + player2.getPoints() + " points");
      System.out.println(currentPlayer.getName() + ", it is your turn.");


      String guess = "";
      boolean validInput = false;
      while (!validInput) {
        System.out.print("Guess a letter (a-z or A-Z): ");
        String ActualInput = input.nextLine();
        if (ActualInput.length() == 1 && Character.isLetter(ActualInput.charAt(0))) {
          guess = ActualInput.toLowerCase();
          validInput = true;
        } else {
          System.out.println("Invalid input. Please enter exactly one letter.");
        }
      }

      if (board.guessLetter(guess)) {
        System.out.println("Yes! The letter '" + guess + "' is in the phrase.");
        int currentPoints = currentPlayer.getPoints();
        currentPlayer.setPoints(currentPoints + board.getCurrentLetterValue());
        if (!board.getSolvedPhrase().contains("_")) {
          solved = true;
        }
      } else {
        System.out.println("Sorry, the letter '" + guess + "' is not in the phrase.");
      }


      if (currentPlayer == player1) {
        currentPlayer = player2;
      } else {
        currentPlayer = player1;
      }
    }


    System.out.println("\nThe phrase is solved!");
    System.out.println("The final phrase is: " + board.getSolvedPhrase());
    System.out.println(player1.getName() + ": " + player1.getPoints() + " points");
    System.out.println(player2.getName() + ": " + player2.getPoints() + " points");

    if (player1.getPoints() > player2.getPoints()) {
      System.out.println("The winner is: " + player1.getName() + "!");
    } else if (player2.getPoints() > player1.getPoints()) {
      System.out.println("The winner is: " + player2.getName() + "!");
    } else {
    System.out.println("Both players got the same amount of points. It's a tie!");
    }
  }
}
