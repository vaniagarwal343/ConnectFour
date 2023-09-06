// Vani Agarwal
// CSE 123
// C0: Abstract Strategy Games
//
// A class to represent a game of ConnectFour that implements the 
// AbstractStrategyGame interface.

import java.util.*;

public class ConnectFour implements AbstractStrategyGame {
    public final int ROWS=6;
    public final int COLUMNS=7; 
    private char [][] board; 
    private boolean isXTurn;

    // Constructs a new ConnectFour game

    public ConnectFour() { 
        this.board=new char[ROWS][COLUMNS];
                for (int i = 0; i < ROWS; i++) {
                        for (int j = 0; j < COLUMNS; j++) {
                                board[i][j] = '_';
                        }
                }
        this.isXTurn=true;
    }

    // Returns whether or not the game is over 

    public boolean isGameOver() { 
        return getWinner() >= 0;
    }

    // Returns the index of the winner of the game. 
    // 1 for player 1 (X), 2 for player 2 (O), 0 if there's a tie
    // -1 if the game is not over 

    public int getWinner() {
        
        // check rows for a win
        for (int row = 0; row < board.length; row++) {
                for (int col = 0; col < board[0].length - 3; col++) {
                        if (board[row][col] != '_' &&
                            board[row][col] == board[row][col + 1] &&
                            board[row][col] == board[row][col + 2] &&
                            board[row][col] == board[row][col + 3]) {
                            return board[row][col] == 'X' ? 1 : 2;
                        }
                }
            }
                
                // check columns for a win
                for (int row = 0; row < board.length - 3; row++) {
                        for (int col = 0; col < board[0].length; col++) {
                                if (board[row][col] != '_' &&
                                        board[row][col] == board[row + 1][col] &&
                                board[row][col] == board[row + 2][col] &&
                                board[row][col] == board[row + 3][col]) {
                                        return board[row][col] == 'X' ? 1 : 2;
                                }
                        }
                }
                
                // check diagonals (positive slope)
                for (int row = 0; row < board.length - 3; row++) {
                        for (int col = 0; col < board[0].length - 3; col++) {
                                if (board[row][col] != '_' &&
                                        board[row][col] == board[row + 1][col + 1] &&
                                board[row][col] == board[row + 2][col + 2] &&
                                board[row][col] == board[row + 3][col + 3]) {
                                        return board[row][col] == 'X' ? 1 : 2;
                                }
                        }
                }
                
                // check diagonals (negative slope)
                for (int row = 3; row < board.length; row++) {
                        for (int col = 0; col < board.length - 3; col++) {
                                if (board[row][col] != '_' &&
                                        board[row][col] == board[row - 1][col + 1] &&
                                board[row][col] == board[row - 2][col + 2] &&
                                board[row][col] == board[row - 3][col + 3]) {
                                        return board[row][col] == 'X' ? 1 : 2;
                                }
                        }
                }
                
                // check for tie
                for (int row = 0; row < board.length; row++) {
                        for (int col = 0; col < board.length; col++) {
                                if (board[row][col] == '_') {
                                        // unfilled space; game not over
                                        return -1;
                                }
                        }
                }
                
                // it's a tie!
                return 0;
        }

    // Returns the index of which player's turn it is 
    // 1 if player 1 (X), 2 if player 2 (O).

    public int getNextPlayer () { 
        return isXTurn ? 1 : 2;
    }
    
    // Given the input, places an X or an O where 
    // the player specifies

    public void makeMove(Scanner input) { 
        char currPlayer = isXTurn ? 'X' : 'O';

        System.out.print("Column? "); 
        int col = input.nextInt();

        makeMove(col, currPlayer);
        isXTurn = !isXTurn;
    }

    // Private helper method for makeMove. 
    // Given a column, as well as player index,
    // places and X or an 0 in the column
    // Throws an IllegalArgumentException if the position is
    // invalid, whether that be out of bounds or already occupied 
    // Board bounds are [0, 2], for both rows and cols. 

    private void makeMove(int col, char player) { 
        if (col < 0 || col >= board[0].length ) { 
            throw new IllegalArgumentException(("Invalid board poition: " + col)); 
        }
        if (board[0][col] != '_') { 
            throw new IllegalArgumentException(("This column is full: " + col));
        }
        int pos = 5;
        for (int i = board.length - 1; i >= 0; i --) { 
            if (board[i][col] != '_') { 
                pos --;
            }
        }
        board[pos][col] = player;
    }

    // Returns a string containing instructions to play the game 

    public String instructions () { 
        String result = ""; 
        result += "Player 1 is X and goes first. Choose where to play by entering a column number,\n";
        result += "where (0) is the leftmost column and (5) is the rightmost column. Spaces\n";
        result += "that show as a _ are empty. The game ends when one player marks 4 spaces in a\n";
        result += "in which case that player wins, or when the board is full, in which\n";
        result += "case the game ends in a tie. Player 2 is O.";
        return result;
    }

    // Returns a string representation of the current state of the board

    public String toString() { 
        String result = "";
        for (int i = 0; i < board.length; i ++) { 
            for (int j = 0; j < board[i].length; j ++) { 
                result += board[i][j] + " ";
            }
            result += "\n";
        }
        return result;
    }
}
