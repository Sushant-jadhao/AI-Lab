public class sudoku {
    public static void main(String[] args) {
        // Example of how to use the solveBoard method
        int[][] sudoku_board = {
                { 0, 0, 0, 0, 0, 0, 1, 0, 7 },
                { 8, 9, 0, 0, 0, 0, 0, 2, 0 },
                { 0, 0, 0, 0, 2, 0, 9, 0, 0 },
                { 0, 7, 0, 0, 9, 0, 0, 4, 0 },
                { 0, 0, 0, 2, 8, 0, 5, 9, 1 },
                { 0, 0, 0, 4, 0, 0, 0, 0, 0 },
                { 2, 0, 0, 0, 0, 4, 0, 0, 8 },
                { 0, 8, 0, 3, 5, 0, 0, 0, 0 },
                { 0, 4, 7, 0, 1, 0, 3, 0, 0 }
        };

        // int[][] board = new int[9][9];
        sudoku sudokuSolver = new sudoku();
        System.out.println("-------------------Solve the sudoku Problem---------------------");
        System.out.println("--------------------------Question------------------------------");
        sudokuSolver.printBoard(sudoku_board);
        // System.out.println("---------------------------------------------");
        System.out.println("---------------------Solved Board-------------------------");
        sudokuSolver.solveBoard(sudoku_board);
    }

    public void solveBoard(int[][] board) {
        if (solve(board)) {
            printBoard(board);
        } else {
            System.out.println("No solution exists.");
        }
    }

    private boolean solve(int[][] board) {
        int[] emptyCell = findEmptyCell(board);
        int row = emptyCell[0];
        int col = emptyCell[1];

        // If no empty cell is found, the board is solved
        if (row == -1 && col == -1) {
            return true;
        }

        for (int num = 1; num <= 9; num++) {
            if (isValid(board, row, col, num)) {
                board[row][col] = num;

                // Recursive call to solve the next empty cell
                if (solve(board)) {
                    return true;
                }

                // Backtrack if the current solution doesn't lead to a solution
                board[row][col] = 0;
            }
        }

        // No valid number found for the current empty cell
        return false;
    }

    public int[] findEmptyCell(int[][] board) {
        int[] coordinates = { -1, -1 }; // Default value for row and column

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == 0) {
                    coordinates[0] = i; // Row index
                    coordinates[1] = j; // Column index
                    return coordinates;
                }
            }
        }
        return coordinates; // If no empty cell found, return default values
    }

    public boolean isValidRow(int[][] board, int row, int num) {
        for (int i = 0; i < 9; i++) {
            if (board[row][i] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidCol(int[][] board, int col, int num) {
        for (int j = 0; j < 9; j++) {
            if (board[j][col] == num) {
                return false;
            }
        }
        return true;
    }

    public boolean isValidBox(int[][] board, int startRow, int startCol, int num) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isValid(int[][] board, int row, int col, int num) {
        return isValidRow(board, row, num) && isValidCol(board, col, num)
                && isValidBox(board, row - row % 3, col - col % 3, num);
    }

    public void printBoard(int[][] board) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
