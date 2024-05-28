import java.util.*;

class Coord {
    int row, col;

    Coord(int r, int c) {
        row = r;
        col = c;
    }
}

public class MazeSolverDFS {

    public static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValid(char[][] maze, boolean[][] visited, int row, int col) {
        return row >= 0 && row < maze.length && col >= 0 && col < maze[0].length && maze[row][col] != '#'
                && !visited[row][col];
    }

    public static boolean dfs(char[][] maze, Coord start, Coord exit) {
        Stack<Coord> stack = new Stack<>();
        stack.push(start);

        boolean[][] visited = new boolean[maze.length][maze[0].length];

        while (!stack.isEmpty()) {
            Coord current = stack.pop();

            if (current.row == exit.row && current.col == exit.col) {
                maze[current.row][current.col] = 'F';
                printMaze(maze);
                return true;
            }

            maze[current.row][current.col] = 'O';
            visited[current.row][current.col] = true;

            int[][] directions = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
            for (int[] dir : directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if (isValid(maze, visited, newRow, newCol)) {
                    stack.push(new Coord(newRow, newCol));
                }
            }
        }

        System.out.println("No solution found.");
        return false;
    }

    public static void main(String[] args) {
        char[][] maze = {
                { 'S', ' ', ' ', '#', ' ', ' ', ' ' },
                { '#', '#', ' ', '#', ' ', '#', ' ' },
                { ' ', ' ', ' ', ' ', ' ', ' ', '#' },
                { '#', '#', ' ', '#', '#', '#', '#' },
                { '#', ' ', ' ', ' ', ' ', ' ', ' ' },
                { '#', '#', ' ', '#', '#', '#', ' ' },
                { '#', ' ', ' ', ' ', ' ', ' ', 'F' }
        };

        Coord start = new Coord(0, 0);
        Coord exit = new Coord(maze.length - 1, maze[0].length - 1);

        System.out.println("Maze:");
        printMaze(maze);

        System.out.println("\nSolution Path:");
        dfs(maze, start, exit);
    }
}
