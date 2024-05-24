import java.util.Scanner;

public class TicTacToeNonAi {
    // Function to convert a number from 1-9 to row and column indices
    static void convertToRowCol(int number, int[] rowCol) {
        rowCol[0] = (number - 1) / 3;
        rowCol[1] = (number - 1) % 3;
    }

    // Function to print the Tic-Tac-Toe board
    static void printBoard(char[][] board) {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println("\n-------------");
        }
    }

    // Function to check if a player has won
    static boolean isWinner(char[][] board, char player) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == player && board[i][1] == player && board[i][2] == player) ||
                    (board[0][i] == player && board[1][i] == player && board[2][i] == player)) {
                return true; // Player has won
            }
        }
        if ((board[0][0] == player && board[1][1] == player && board[2][2] == player) ||
                (board[0][2] == player && board[1][1] == player && board[2][0] == player)) {
            return true; // Player has won
        }
        return false; // No winner yet
    }

    // Function to check if the board is full
    static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false; // Board is not full
                }
            }
        }
        return true; // Board is full
    }

    // Function to get an empty corner
    static boolean getEmptyCorner(char[][] board, int[] rowCol) {
        int[][] emptyCorners = { { 0, 0 }, { 0, 2 }, { 2, 0 }, { 2, 2 } };
        for (int[] corner : emptyCorners) {
            if (board[corner[0]][corner[1]] == ' ') {
                rowCol[0] = corner[0];
                rowCol[1] = corner[1];
                return true; // Found an empty corner
            }
        }
        return false; // No empty corner found
    }

    // Function for the computer to make a move
    static void makeComputerMove(char[][] board) {
        // Check if the opponent placed an "X" in the center
        if (board[1][1] == 'X') {
            // Respond by placing an "O" in one of the corners
            int[] rowCol = new int[2];
            if (getEmptyCorner(board, rowCol)) {
                board[rowCol[0]][rowCol[1]] = 'O';
            }
        } else {
            // If the center is empty, place an "O" in the center
            if (board[1][1] == ' ') {
                board[1][1] = 'O';
            } else {
                // Place an "O" in the first available empty cell
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (board[i][j] == ' ') {
                            board[i][j] = 'O';
                            return;
                        }
                    }
                }
            }
        }
    }

    // Main game loop
    static void playTicTacToe() {
        char[][] board = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };
        boolean playerTurn = true; // true for player X, false for player O
        Scanner scanner = new Scanner(System.in);

        while (true) {
            printBoard(board);

            if (playerTurn) {
                // Player X's turn
                System.out.print("Player X, enter a number from 1 to 9: ");
                int number = scanner.nextInt();

                int[] rowCol = new int[2];
                convertToRowCol(number, rowCol);

                if (number >= 1 && number <= 9 && board[rowCol[0]][rowCol[1]] == ' ') {
                    board[rowCol[0]][rowCol[1]] = 'X';
                    playerTurn = false;
                } else {
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                // Player O's turn (Computer)
                makeComputerMove(board);
                playerTurn = true;
            }

            if (isWinner(board, 'X')) {
                printBoard(board);
                System.out.println("Player X wins!");
                break;
            } else if (isWinner(board, 'O')) {
                printBoard(board);
                System.out.println("Player O wins!");
                break;
            } else if (isBoardFull(board)) {
                printBoard(board);
                System.out.println("It's a tie!");
                break;
            }
        }
    }

    public static void main(String[] args) {
        playTicTacToe();
    }
}
