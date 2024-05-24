public class NQueen {
    private int[] board;
    private int solutionsCount;

    public NQueen(int n) { // Correct constructor definition
        board = new int[n];
        solutionsCount = 0;
        solve(0, n);
        System.out.println("Total solutions: " + solutionsCount);
    }

    private void solve(int row, int n) {
        if (row == n) {
            printSolution(n);
            solutionsCount++;
            return;
        }
        for (int col = 0; col < n; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                solve(row + 1, n);
            }
        }
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private void printSolution(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 5; // Change this value to solve for a different size of the board
        new NQueen(n);
    }
}
