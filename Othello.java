/**
 * Othello class implements the logic of the game.
 * 
 * @author Nandigam & Carley Dziewicki
 *
 */
public class Othello {

	// game board as a two-dimensional array of discs
	private Disc[][] board;

	// who's turn is it next?
	private int turn;

	// player 1's disc color
	private Disc player1Disc;

	// player 2 disc color
	private Disc player2Disc;

	// current player's disc
	private Disc currentDisc;

	/**
	 * Constructs a newly initialized Othello object with the specified values.
	 * 
	 * @param boardSize   size of board
	 * @param startPlayer player to start the game
	 * @param disc        disc color of the start player
	 */
	public Othello(int boardSize, int startPlayer, Disc disc) {
		if (boardSize < 4 || boardSize > 8 || boardSize % 2 != 0) {
			throw new IllegalArgumentException("Board size is not valid.");
		}

		if (startPlayer < 1 || startPlayer > 2) {
			throw new IllegalArgumentException("Player number is not valid.");
		}

		turn = startPlayer;
		currentDisc = disc;
		if (startPlayer == 1) {
			player1Disc = disc;
			player2Disc = disc == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
		} else {
			player2Disc = disc;
			player1Disc = disc == Disc.WHITE ? Disc.BLACK : Disc.WHITE;
		}

		// instantiate and initialize the board to initial configuration
		setupBoard(boardSize);
	}

	/**
	 * Instantiates and initializes the game board to initial configuration.
	 * 
	 * @param size board size
	 */
	private void setupBoard(int size) {
		board = new Disc[size][size];

		int topMiddle = (size / 2) - 1;

		board[topMiddle][topMiddle] = Disc.BLACK;
		board[topMiddle + 1][topMiddle + 1] = Disc.BLACK;
		board[topMiddle + 1][topMiddle] = Disc.WHITE;
		board[topMiddle][topMiddle + 1] = Disc.WHITE;

		// System.out.println(toString());

	}

	/**
	 * Returns the size of the board.
	 * 
	 * @return board size
	 */
	public int getBoardSize() {
		return board.length;
	}

	/**
	 * Returns the number of current player.
	 * 
	 * @return number of current player
	 */
	public int getTurn() {
		return turn;
	}

	/**
	 * Returns the disc of player 1.
	 * 
	 * @return disc of player 1
	 */
	public Disc getPlayer1Disc() {
		return player1Disc;
	}

	/**
	 * Returns the disc of player 2.
	 * 
	 * @return disc of player 2
	 */
	public Disc getPlayer2Disc() {
		return player2Disc;
	}

	/**
	 * Return the disc of the current player.
	 * 
	 * @return disc of the current player
	 */
	public Disc getCurrentPlayerDisc() {
		return currentDisc;
	}

	/**
	 * Sets turn and currentDisc fields for next turn.
	 */
	public void setNextTurn() {

		if (getTurn() == 1) {
			currentDisc = getPlayer2Disc();
			turn = 2;
		} else {
			currentDisc = getPlayer1Disc();
			turn = 1;
		}
	}

	/**
	 * Returns true if placing the disc at specified row and column is valid,
	 * otherwise false.
	 * 
	 * @param row  row number
	 * @param col  column number
	 * @param disc disc to place at position row,col
	 * 
	 * @return true if placing a disc at specified position is valid, false
	 *         otherwise
	 */
	public boolean isValidMoveForDisc(int row, int col, Disc disc) {

		// Right
		if (col < getBoardSize()) {

			for (int i = col + 1; i < getBoardSize(); i++) {
				if ((i + 1) < getBoardSize()) {
					if (board[row][i] != disc && board[row][i] != null) {
						// check that the piece right next to me, is not the same color

						if (board[(row)][i + 1] == disc) {
							return true;
						}

						// can go in this direction (only need one to be valid)
					} else {
						break;
					}
				}
			}
		}
		
		// LEFT
		if (col > 1) {
			for (int i = col - 1; i >= 0; i--) {
				if ((i - 1) >= 0) {
					if (board[row][i] != disc && board[row][i] != null) {

						if (board[(row)][i - 1] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}
		
		// DOWN
		if (row < getBoardSize()) {

			for (int i = row + 1; i < getBoardSize(); i++) {
				if ((i + 1) < getBoardSize()) {
					if (board[i][col] != disc && board[i][col] != null) {

						if (board[(i + 1)][col] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}
		
		// UP
		if (row > 1) {
			for (int i = row - 1; i >= 0; i--) {
				if ((i - 1) >= 0) {
					if (board[i][col] != disc && board[i][col] != null) {
						
						if (board[(i - 1)][col] == disc) {
							return true;
						}

						// can go in this direction (only need one to be valid)
					} else {
						break;
					}
				}
			}
		}

		// DIAG DOWN/RIGHT
		if (row < getBoardSize() && col < getBoardSize()) {

			for (int i = row + 1, j = col + 1; i < getBoardSize() && j < getBoardSize(); i++, j++) {
				if ((i + 1) < getBoardSize() && (j + 1) < getBoardSize()) {
					if (board[i][j] != disc && board[i][j] != null) {

						if (board[(i + 1)][(j + 1)] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}

		// DIAG DOWN/LEFT
		if (row < getBoardSize() && col > 1) {
			for (int i = row + 1, j = col - 1; i < getBoardSize() && j >= 0; i++, j--) {
				if ((i + 1) < getBoardSize() && (j - 1) >= 0) {
					if (board[i][j] != disc && board[i][j] != null) {
						
						if (board[(i + 1)][j - 1] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}

		// DIAG UP/LEFT
		if (row > 1 && col > 1) {
			for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
				if ((i - 1) >= 0 && (j - 1) >= 0) {
					if (board[i][j] != disc && board[i][j] != null) {

						if (board[(i - 1)][j - 1] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}
		// DIAG UP/RIGHT
		if (row > 1 && col < getBoardSize()) {
			for (int i = row - 1, j = col + 1; i >= 0 && j < getBoardSize(); i--, j++) {
				if ((i - 1) >= 0 && (j + 1) < getBoardSize()) {
					if (board[i][j] != disc && board[i][j] != null) {

						if (board[(i - 1)][j + 1] == disc) {
							return true;
						}

					} else {
						break;
					}
				}
			}
		}
		return false;

	}

	/**
	 * Returns true if a valid move is available for the disc specified, false
	 * otherwise.
	 * 
	 * @param disc disc
	 * 
	 * @return true if a valid move is available for the disc specified, false
	 *         otherwise
	 */
	public boolean isValidMoveAvailableForDisc(Disc disc) {
		
		for (int i = 0; i < getBoardSize(); i++) {
			for (int j = 0; j < getBoardSize(); j++) {
				if (board[i][j] == null) {
					if (isValidMoveForDisc(i, j, disc)) {
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Places the disc of the current player at position row,col if a valid move is
	 * available and flips the opponent discs as needed.
	 * 
	 * @param row row to place the disc at
	 * @param col column to place the disc at
	 */
	public void placeDiscAt(int row, int col) {
		
		if (!isValidMoveForDisc(row, col, currentDisc)) {
			return;
		} else {

			board[row][col] = currentDisc;

			//First checks if flipping in this direction is valid
			boolean validMoveR = false;

			if (col < getBoardSize()) {

				for (int i = col + 1; i < getBoardSize(); i++) {
					if (board[row][i] != currentDisc && board[row][i] != null) {
						if ((i + 1) < getBoardSize()) {
							if (board[(row)][i + 1] == currentDisc) {
								validMoveR = true;
							}
						}
					} else {
						break;
					}
				}
			}

			//flips discs going right
			if (validMoveR) {
				for (int i = col + 1; i < getBoardSize(); i++) {
					if (board[row][i] != null && board[row][i] != currentDisc) {
						board[row][i] = currentDisc;
					} else {
						break;
					}
				}
			}

			// LEFT
			boolean validMoveL = false;

			if (col > 1) {
				for (int i = col - 1; i >= 0; i--) {
					if (board[row][i] != currentDisc && board[row][i] != null) {
						if ((i - 1) >= 0) {

							if (board[(row)][i - 1] == currentDisc) {
								validMoveL = true;

							}
						}
					} else {
						break;
					}
				}
			}

			if (validMoveL) {
				for (int i = col - 1; i >= 0; i--) {
					if (board[row][i] != null && board[row][i] != currentDisc) {
						board[row][i] = currentDisc;
					} else {
						break;
					}
				}
			}

			// DOWN
			boolean validMoveDown = false;

			if (row < getBoardSize()) {

				for (int i = row + 1; i < getBoardSize(); i++) {
					if (board[i][col] != currentDisc && board[i][col] != null) {
						if ((i + 1) < getBoardSize()) {
							if (board[(i + 1)][col] == currentDisc) {
								validMoveDown = true;
							}
						}
					} else {
						break;
					}
				}
			}

			if (validMoveDown) {
				for (int i = row + 1; i < getBoardSize(); i++) {
					if (board[i][col] != null && board[i][col] != currentDisc) {
						board[i][col] = currentDisc;
					} else {
						break;
					}
				}
			}

			// UP
			boolean validMoveUp = false;

			if (row > 1) {
				for (int i = row - 1; i >= 0; i--) {
					if (board[i][col] != currentDisc && board[i][col] != null) {

						if ((i - 1) >= 0) {
							if (board[(i - 1)][col] == currentDisc) {
								validMoveUp = true;
							}
						}

					} else {
						break;
					}
				}
			}

			if (validMoveUp) {
				for (int i = row - 1; i >= 0; i--) {
					if (board[i][col] != null && board[i][col] != currentDisc) {
						board[i][col] = currentDisc;
					} else {
						break;
					}
				}
			}

			// DIAG DOWN/RIGHT
			boolean validDownR = false;

			if (row < getBoardSize() && col < getBoardSize()) {

				for (int i = row + 1, j = col + 1; i < getBoardSize() && j < getBoardSize(); i++, j++) {
					if ((i + 1) < getBoardSize() && (j + 1) < getBoardSize()) {
						if (board[i][j] != currentDisc && board[i][j] != null) {

							if (board[(i + 1)][(j + 1)] == currentDisc) {
								validDownR = true;
							}

						} else {
							break;
						}
					}
				}
			}

			if (validDownR) {

				for (int i = row + 1, j = col + 1; i < getBoardSize() && j < getBoardSize(); i++, j++) {
					if ((i + 1) < getBoardSize() && (j + 1) < getBoardSize()) {
						if (board[i][j] != currentDisc && board[i][j] != null) {

							board[i][j] = currentDisc;

						} else {
							break;
						}
					}
				}

			}
		}

		// DIAG DOWN/LEFT
		boolean validDownL = false;

		if (row < getBoardSize() && col > 1) {
			for (int i = row + 1, j = col - 1; i < getBoardSize() && j >= 0; i++, j--) {
				if ((i + 1) < getBoardSize() && (j - 1) >= 0) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						if (board[(i + 1)][j - 1] == currentDisc) {
							validDownL = true;
						}

					} else {
						break;
					}
				}
			}
		}

		if (validDownL) {

			for (int i = row + 1, j = col - 1; i < getBoardSize() && j >= 0; i++, j--) {
				if ((i + 1) < getBoardSize() && (j - 1) >= 0) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						board[i][j] = currentDisc;

					} else {
						break;
					}
				}
			}
		}

		// DIAG UP/LEFT
		boolean validUpL = false;

		if (row > 1 && col > 1) {
			for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
				if ((i - 1) >= 0 && (j - 1) >= 0) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						if (board[(i - 1)][j - 1] == currentDisc) {
							validUpL = true;
						}

					} else {
						break;
					}
				}
			}
		}

		if (validUpL) {
			for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
				if ((i - 1) >= 0 && (j - 1) >= 0) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						board[i][j] = currentDisc;

					} else {
						break;
					}
				}
			}
		}

		// DIAG UP/RIGHT
		boolean validUpR = false;

		if (row > 1 && col < getBoardSize()) {
			for (int i = row - 1, j = col + 1; i >= 0 && j < getBoardSize(); i--, j++) {
				if ((i - 1) >= 0 && (j + 1) < getBoardSize()) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						if (board[(i - 1)][j + 1] == currentDisc) {
							validUpR = true;
						}

					} else {
						break;
					}
				}
			}
		}

		if (validUpR) {

			for (int i = row - 1, j = col + 1; i >= 0 && j < getBoardSize(); i--, j++) {
				if ((i - 1) >= 0 && (j + 1) < getBoardSize()) {
					if (board[i][j] != currentDisc && board[i][j] != null) {

						board[i][j] = currentDisc;

					} else {
						break;
					}
				}
			}
		}

		// KEEP THIS CODE
		// prepare for next turn
		if (!isGameOver()) {
			setNextTurn();
		}
	}

	/**
	 * Returns true if the board is full, false otherwise.
	 * 
	 * @return true if the board is full, false otherwise
	 */
	public boolean isBoardFull() {
		// REPLACE NEXT LINE WITH YOUR IMPLEMENTATION
		int pieces = 0;

		for (int i = 0; i < getBoardSize(); i++) {
			for (int j = 0; j < getBoardSize(); j++) {
				if (board[i][j] == Disc.WHITE || board[i][j] == Disc.BLACK) {
					pieces++;
				}
			}
		}

		// if there are no null spaces the the board is full
		if (pieces == (getBoardSize() * getBoardSize())) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Returns true if the game is over, false otherwise. Game is over if the board
	 * is full or no valid move is available for both players.
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return isBoardFull() || (isValidMoveAvailableForDisc(Disc.WHITE) == false
				&& isValidMoveAvailableForDisc(Disc.BLACK) == false);
	}

	/**
	 * Determines and returns the winner or tie information. If the game is not
	 * over, returns null.
	 * 
	 * @return winner or tie information
	 */
	public Winner whoWon() {
		if (!isGameOver()) {
			return null;
		} else {

			// REPLACE NEXT LINE WITH YOUR IMPLEMENTATION
			int blackCount = 0;
			int whiteCount = 0;

			for (int i = 0; i < getBoardSize(); i++) {
				for (int j = 0; j < getBoardSize(); j++) {
					if (board[i][j] == Disc.BLACK) {
						blackCount++;
					} else if (board[i][j] == Disc.WHITE) {
						whiteCount++;
					}
				}
			}

			//would be helpful to see at the end of the game
			//System.out.println("Player 1: " + getPlayer1Disc());
			//System.out.println("Player 2: " + getPlayer2Disc());
			//System.out.println("White count: " + whiteCount);
			//System.out.println("Black count: " + blackCount);

			if (getPlayer1Disc() == Disc.BLACK && blackCount > whiteCount) {
				return Winner.PLAYER1;
			} else if (getPlayer1Disc() == Disc.WHITE && whiteCount > blackCount) {
				return Winner.PLAYER1;
			} else if (getPlayer2Disc() == Disc.BLACK && blackCount > whiteCount) {
				return Winner.PLAYER2;
			} else if (getPlayer2Disc() == Disc.WHITE && whiteCount > blackCount) {
				return Winner.PLAYER2;
			} else {
				return Winner.TIE;
			}

		}

	}

	/**
	 * Returns a String object representing this game board.
	 * 
	 * @return a string representation of the value of this object
	 */
	public String toString() {
		String str = "\n  ";
		for (int i = 1; i <= board.length; i++) {
			str += i + " ";
		}
		str = str.stripTrailing() + "\n";
		for (int i = 0; i < board.length; i++) {
			str += (i + 1) + " ";
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == Disc.WHITE) {
					str += "W ";
				} else if (board[i][j] == Disc.BLACK) {
					str += "B ";
				} else {
					str += "- ";
				}
			}
			str = str.stripTrailing() + "\n";
		}
		return str;
	}
}
