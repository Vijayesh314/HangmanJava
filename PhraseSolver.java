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



  public void play()
  {
    boolean solved = false;
    int currentPlayer = 1;

    Scanner input = new Scanner(System.in);
    
    boolean correct = true;
    while (!solved) 
    {
      
      /* your code here - game logic */
      // show turn and board
      Player active = (currentPlayer % 2 == 1) ? player1 : player2;
      System.out.println("\n" + active.getPlayer() + "'s turn.");
      System.out.println("Phrase: " + board.getSolvedPhrase());

      // set value for this letter
      board.setLetterValue();
      System.out.println("Current letter value: " + board.getCurrentLetterValue());

      // show scores
      System.out.println(player1.getPlayer() + ": " + player1.getPoints() + "    " + player2.getPlayer() + ": " + player2.getPoints());

      System.out.println("Type '1' to guess a letter, or '2' to try to solve the phrase:");
      String choice = input.nextLine().trim();

      if (choice.equals("1"))
      {
        System.out.println("Enter a single letter:");
        String guess = input.nextLine().trim().toLowerCase();
        if (guess.length() != 1 || !guess.matches("[a-z]"))
        {
          System.out.println("Invalid input. Please enter a single letter.");
        }
        else
        {
          int count = board.guessLetter(guess);
          if (count > 0)
          {
            int award = count * board.getCurrentLetterValue();
            active.setPoints(active.getPoints() + award);
            System.out.println("Good guess! '" + guess + "' appears " + count + " time(s). " + active.getPlayer() + " earns " + award + " points.");
          }
          else
          {
            System.out.println("Sorry, '" + guess + "' is not in the phrase.");
          }
        }
      }
      else if (choice.equals("2"))
      {
        System.out.println("Enter your solution attempt:");
        String attempt = input.nextLine().trim().toLowerCase();
        if (board.isSolved(attempt))
        {
          System.out.println("Correct! The phrase was: " + board.getPhrase());
          solved = true;
        }
        else
        {
          System.out.println("Incorrect solution attempt.");
        }
      }
      else
      {
        System.out.println("Invalid choice. Turn skipped.");
      }

      // check if all letters have been revealed
      if (board.isFullySolved())
      {
        System.out.println("All letters revealed! The phrase is: " + board.getPhrase());
        solved = true;
      }

      // alternate player for next turn
      if (!solved) currentPlayer++;
    } 
    
    // game finished - determine winner
    System.out.println("\nFinal scores: " + player1.getPlayer() + ": " + player1.getPoints() + "    " + player2.getPlayer() + ": " + player2.getPoints());
    if (player1.getPoints() > player2.getPoints())
    {
      System.out.println(player1.getPlayer() + " wins!");
    }
    else if (player2.getPoints() > player1.getPoints())
    {
      System.out.println(player2.getPlayer() + " wins!");
    }
    else
    {
      System.out.println("It's a tie!");
    }

  }
  
}