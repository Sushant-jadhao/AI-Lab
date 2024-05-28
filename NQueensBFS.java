import java.util.*;

public class NQueensBFS {

    public static class State {
        int row;
        char[][] board;

        State(int row, char[][] board) {
            this.row = row;
            this.board = board;
        }
    }

    public static boolean isSafe(int row, int col, char[][] board) {
        int n = board.length;

        // Check column
        for (int r = 0; r < row; r++) {
            if (board[r][col] == 'Q') {
                return false;
            }
        }

        // Check upper left diagonal
        for (int r = row - 1, c = col - 1; r >= 0 && c >= 0; r--, c--) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        // Check upper right diagonal
        for (int r = row - 1, c = col + 1; r >= 0 && c < n; r--, c++) {
            if (board[r][c] == 'Q') {
                return false;
            }
        }

        return true;
    }

    public static List<List<String>> nqueenBfs(int n) {
        List<List<String>> allBoards = new ArrayList<>();

        Queue<State> q = new LinkedList<>();
        char initial[][] = new char[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(initial[i], '.');
        }
        q.offer(new State(0, initial));

        while (!q.isEmpty()) {
            State current = q.poll();
            int row = current.row;
            char[][] board = current.board;

            if (row == n) {
                List<String> newBoard = new ArrayList<>();
                for (char[] list : board) {
                    newBoard.add(new String(list));
                }
                allBoards.add(newBoard);
                continue;
            }

            for (int col = 0; col < n; col++) {
                if (isSafe(row, col, board)) {
                    char[][] next = new char[n][n];
                    for (int i = 0; i < n; i++) {
                        next[i] = board[i].clone();
                    }
                    next[row][col] = 'Q';
                    q.offer(new State(row + 1, next));
                }
            }
        }

        return allBoards;
    }

    public static void main(String args[]) {
        int n = 5;
        List<List<String>> boards = nqueenBfs(n);
        for (List<String> board : boards) {
            for (String row : board) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
