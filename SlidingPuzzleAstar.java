import java.util.*;

public class SlidingPuzzleAstar {

    public static class State {
        int[][] board;
        int g; // cost to reach this state
        int h; // heuristic cost (misplaced tiles)
        int x, y; // coordinates of the blank (zero) tile

        State(int[][] board, int g, int h, int x, int y) {
            this.board = new int[board.length][board.length];
            for (int i = 0; i < board.length; i++) {
                this.board[i] = board[i].clone();
            }
            this.g = g;
            this.h = h;
            this.x = x;
            this.y = y;
        }
    }

    public static void findZero(int[][] board, int[] zeroPos) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == 0) {
                    zeroPos[0] = i;
                    zeroPos[1] = j;
                    return;
                }
            }
        }
    }

    public static void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static boolean isGoalState(int[][] board, int[][] goal) {
        return Arrays.deepEquals(board, goal);
    }

    public static int findMisplacedTiles(int[][] board, int[][] goal) {
        int count = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != 0 && board[i][j] != goal[i][j]) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void aStar(int[][] board, int[][] goal) {
        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.g + a.h));
        int[] zeroPos = new int[2];
        findZero(board, zeroPos);
        pq.add(new State(board, 0, findMisplacedTiles(board, goal), zeroPos[0], zeroPos[1]));

        while (!pq.isEmpty()) {
            State current = pq.poll();
            printBoard(current.board);

            if (isGoalState(current.board, goal)) {
                System.out.println("Goal State Reached");
                return;
            }

            int[] dx = {0, 0, -1, 1};
            int[] dy = {1, -1, 0, 0};

            for (int i = 0; i < 4; i++) {
                int newX = current.x + dx[i];
                int newY = current.y + dy[i];

                if (newX >= 0 && newX < board.length && newY >= 0 && newY < board.length) {
                    swap(current.board, current.x, current.y, newX, newY);
                    int newH = findMisplacedTiles(current.board, goal);
                    pq.add(new State(current.board, current.g + 1, newH, newX, newY));
                    swap(current.board, current.x, current.y, newX, newY); // revert the move
                }
            }
        }
    }

    public static void swap(int[][] board, int x1, int y1, int x2, int y2) {
        int temp = board[x1][y1];
        board[x1][y1] = board[x2][y2];
        board[x2][y2] = temp;
    }

    public static void main(String[] args) {
        int[][] initial = {
            {2, 8, 3},
            {1, 6, 4},
            {7, 5, 0}
        };

        int[][] goal = {
            {1, 2, 3},
            {8, 0, 4},
            {7, 6, 5}
        };

        aStar(initial, goal);
    }
}
