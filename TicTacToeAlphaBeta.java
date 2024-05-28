import java.util.Scanner;

public class TicTacToeAlphaBeta {

    static final int SIZE = 3;

    // Function to initialize the board
    public static void initializeBoard(char[][] board) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                board[i][j] = '-';
            }
        }
    }

    // Function to print the board
    public static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(" " + board[i][j] + " ");
                if (j < SIZE - 1) {
                    System.out.print("|");
                }
            }
            System.out.println();
            if (i < SIZE - 1) {
                for (int k = 0; k < SIZE; k++) {
                    System.out.print("---");
                    if (k < SIZE - 1) {
                        System.out.print("|");
                    }
                }
                System.out.println();
            }
        }
        System.out.println();
    }

    // Function to check if the board is full
    public static boolean isBoardFull(char[][] board) {
        for (int i = 0; i < SIZE; ++i) {
            for (int j = 0; j < SIZE; ++j) {
                if (board[i][j] == '-')
                    return false;
            }
        }
        return true;
    }

    // Function to check if the game is over
    public static boolean isGameOver(char[][] board) {
        return evaluateBoard(board) != 0 || isBoardFull(board);
    }

    // Function to evaluate the board
    public static int evaluateBoard(char[][] board) {
        // Check rows
        for (int i = 0; i < SIZE; ++i) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) {
                if (board[i][0] == 'X')
                    return 10;
                else
                    return -10;
            }
        }
        // Check columns
        for (int j = 0; j < SIZE; ++j) {
            if (board[0][j] != '-' && board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                if (board[0][j] == 'X')
                    return 10;
                else
                    return -10;
            }
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) {
            if (board[0][0] == 'X')
                return 10;
            else
                return -10;
        }
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) {
            if (board[0][2] == 'X')
                return 10;
            else
                return -10;
        }
        return 0;
    }

    // Function to implement the minimax algorithm with alpha-beta pruning
    public static int minimax(char[][] board, int depth, boolean isMax, int alpha, int beta) {
        int score = evaluateBoard(board);

        if (score == 10)
            return score;
        if (score == -10)
            return score;
        if (isBoardFull(board))
            return 0;

        if (isMax) {
            int best = -1000;

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'X';
                        best = Math.max(best, minimax(board, depth + 1, false, alpha, beta));
                        board[i][j] = '-';
                        alpha = Math.max(alpha, best);
                        if (beta <= alpha)
                            break;
                    }
                }
            }
            return best;
        } else {
            int best = 1000;

            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (board[i][j] == '-') {
                        board[i][j] = 'O';
                        best = Math.min(best, minimax(board, depth + 1, true, alpha, beta));
                        board[i][j] = '-';
                        beta = Math.min(beta, best);
                        if (beta <= alpha)
                            break;
                    }
                }
            }
            return best;
        }
    }

    // Function to find the optimal move for AI
    public static void findBestMove(char[][] board) {
        int bestVal = -1000;
        int row = -1, col = -1;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    board[i][j] = 'X';
                    int moveVal = minimax(board, 0, false, -1000, 1000);
                    board[i][j] = '-';

                    if (moveVal > bestVal) {
                        row = i;
                        col = j;
                        bestVal = moveVal;
                    }
                }
            }
        }
        System.out.println("Computer's Move: (" + row + ", " + col + ")");
        board[row][col] = 'X';
    }

    // Function for player's move
    public static void playerMove(char[][] board) {
        Scanner scanner = new Scanner(System.in);
        int row, col;
        while (true) {
            System.out.print("Enter your move (row and column): ");
            row = scanner.nextInt();
            col = scanner.nextInt();
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
                board[row][col] = 'O';
                break;
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = new char[SIZE][SIZE];
        initializeBoard(board);
        System.out.println("Welcome to Tic-Tac-Toe!");
        System.out.println("You are 'O' and the computer is 'X'.");
        System.out.println("Player's move is represented as (row, column) from (0,0) to (2,2).");
        System.out.println("Let's begin!");

        int turn = 0;
        while (!isGameOver(board)) {
            printBoard(board);
            if (turn == 0)
                playerMove(board);
            else
                findBestMove(board);
            turn = 1 - turn;
        }

        printBoard(board);
        int score = evaluateBoard(board);
        if (score == 10)
            System.out.println("Computer wins!");
        else if (score == -10)
            System.out.println("Player wins!");
        else
            System.out.println("It's a draw!");
    }
}
