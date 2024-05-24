import java.util.Scanner;

public class TicTacToe {
    private static final char PLAYER = 'X';
    private static final char COMPUTER = 'O';
    private static final char EMPTY = ' ';

    private char[][] board = new char[3][3];

    public TicTacToe() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = EMPTY;
            }
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        printBoard();

        while (true) {
            playerMove(scanner);
            clearScreen();
            printBoard();
            if (isGameOver()) {
                break;
            }

            computerMove();
            clearScreen();
            printBoard();
            if (isGameOver()) {
                break;
            }
        }

        printBoard();
    }

    private void playerMove(Scanner scanner) {
        while (true) {
            System.out.println("Enter your move (row and column, 0-2): ");
            System.out.print("Row: ");
            int row = scanner.nextInt();
            System.out.print("Column: ");
            int col = scanner.nextInt();

            if (row >= 0 && col >= 0 && row < 3 && col < 3 && board[row][col] == EMPTY) {
                board[row][col] = PLAYER;
                break;
            } else {
                System.out.println("This move is not valid. Try again.");
            }
        }
    }

    private void computerMove() {
        int[] bestMove = minimax(2, COMPUTER);
        board[bestMove[0]][bestMove[1]] = COMPUTER;
    }

    private boolean isGameOver() {
        if (hasWon(PLAYER)) {
            System.out.println("Player wins!");
            return true;
        }
        if (hasWon(COMPUTER)) {
            System.out.println("Computer wins!");
            return true;
        }
        if (isBoardFull()) {
            System.out.println("The game is a tie!");
            return true;
        }
        return false;
    }

    private boolean hasWon(char player) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player) {
                return true;
            }
        }
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        System.out.println("  0 1 2");
        for (int i = 0; i < 3; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j]);
                if (j < 2)
                    System.out.print("|");
            }
            System.out.println();
            if (i < 2) {
                System.out.println("  -----");
            }
        }
        System.out.println();
    }

    private int[] minimax(int depth, char player) {
        int[] bestMove = new int[] { -1, -1 };
        int bestScore = (player == COMPUTER) ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        if (hasWon(COMPUTER)) {
            return new int[] { -1, -1, 1 };
        } else if (hasWon(PLAYER)) {
            return new int[] { -1, -1, -1 };
        } else if (isBoardFull() || depth == 0) {
            return new int[] { -1, -1, 0 };
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == EMPTY) {
                    board[i][j] = player;
                    int score = minimax(depth - 1, (player == COMPUTER) ? PLAYER : COMPUTER)[2];
                    board[i][j] = EMPTY;

                    if (player == COMPUTER) {
                        if (score > bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    } else {
                        if (score < bestScore) {
                            bestScore = score;
                            bestMove[0] = i;
                            bestMove[1] = j;
                        }
                    }
                }
            }
        }

        return new int[] { bestMove[0], bestMove[1], bestScore };
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        new TicTacToe().play();
    }
}
