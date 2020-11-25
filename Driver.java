import java.util.*;

/**
 * Driver class provides a command line interface (CLI) to Othello game.
 * 
 * @author Nandigam
 *
 */
public class Driver {

	private static Scanner sc = new Scanner(System.in);
	
	/**
	 * Method that implements the user interface to the game.
	 * 
	 * @param args command line arguments (not used)
	 */
	public static void main(String[] args) {
		System.out.println("!!!!Welcome to OTHELLO game!!!!");
		
		// get board size
		int size = getBoardSize();
		
		// get start player
		int startPlayer = getStartPlayer();
		
		// get start player's disc
		Disc startPlayerDisc = getStartPlayerDisc();
		
		// instantiate Othello object
		Othello game = new Othello(size, startPlayer, startPlayerDisc);
		
		// let's play the game
		while (!game.isGameOver()) {
			// print the game board
			System.out.println(game);
			
			if (!game.isValidMoveAvailableForDisc(game.getCurrentPlayerDisc())) {
				System.out.println("No valid moves avaialable for player " + game.getTurn() + ". You lose your turn.");
				game.setNextTurn();
			} else {
				int row, col;
				while (true) {
					System.out.print("Player " + game.getTurn() +"'s Turn (" + game.getCurrentPlayerDisc().name()
								+ ")" + " - where do you like to place your disc (row col)? ");
					row = sc.nextInt();
					col = Integer.parseInt(sc.nextLine().trim());
					if (row < 1 || row > size || col < 1 || col > size) {
						System.out.println("Sorry, wrong input(s). Try again.");
						continue;
					}
					row--;	// before use, adjust it for zero-indexed board
					col--;	// before use, adjust it for zero-indexed board
					if (!game.isValidMoveForDisc(row, col, game.getCurrentPlayerDisc())) {
						System.out.println("Sorry, that is not a valid move. Try agian.");
						continue;
					}
					break;
				}
				game.placeDiscAt(row, col);
			}
		}
		
		// print the final board and announce winner/tie information
		Winner winner = game.whoWon();
		if (winner == Winner.PLAYER1) {
			System.out.println("Game over. The winner for this round is Player 1.");
		} else if (winner == Winner.PLAYER2) {
			System.out.println("Game over. The winner for this round is Player 2.");
		} else if (winner == Winner.TIE) {
			System.out.println("No winner - game resulted in a tie.");
		}
		
		sc.close();
	}
	
	/**
	 * Prompts, reads, and returns the game board size.
	 * 
	 * @return game board size
	 */
	private static int getBoardSize() {
		int size = 0;
		boolean repeat = true;
		
		do {
			try {
			System.out.print("Enter board size (4 or 6 or 8): ");
			size = Integer.parseInt(sc.nextLine().trim());
			if (size == 4 || size == 6 || size == 8) {
				repeat = false;
			} else {
				System.out.println("Invalid board size. Try again...");
			
			}
			}catch (NumberFormatException e) {
				System.out.println("Please enter an integer value");
			}
		} while (repeat);
		
		return size;
	}

	/**
	 * Prompts, reads, and returns the number of the player to start the game.
	 * 
	 * @return number of the player to start the game
	 */
	private static int getStartPlayer() {
		int startPlayer;
		boolean repeat = true;
		
		do {
			System.out.print("Enter start player (1 or 2): ");
			startPlayer = Integer.parseInt(sc.nextLine().trim());
			if (startPlayer == 1 || startPlayer == 2) {
				repeat = false;
			} else {
				System.out.println("Invalid player number. Try again...");
			}
		} while (repeat);
		
		return startPlayer;
	}
	
	/**
	 * Prompts, reads, and returns the disc color chosen by the start player.
	 * 
	 * @return disc color chosen by the start player
	 */
	private static Disc getStartPlayerDisc() {
		String disc;
		boolean repeat = true;
		
		do {
			System.out.print("Enter disc color of start player (w/W or b/B): ");
			disc = sc.nextLine().trim().toUpperCase();
			if (disc.equals("W") || disc.equals("B")) {
				repeat = false;
			} else {
				System.out.println("Invalid input. Try again...");
			}
		} while (repeat);
		
		return disc.equals("W") ? Disc.WHITE : Disc.BLACK;
	}

}
