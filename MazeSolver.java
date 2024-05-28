import java.util.*;

class Coord {
    int row, col;

    Coord(int r, int c) {
        row = r;
        col = c;
    }
}

public class MazeSolver {

    public static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static void printMaze(char[][] maze) {
        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == 'O' || cell == '*') {
                    System.out.print("o ");
                } else {
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
    }

    public static List<Coord> reconstructPath(Coord[][] parent, Coord start, Coord destination) {
        List<Coord> path = new ArrayList<>();
        Coord current = destination;

        while (!(current.row == start.row && current.col == start.col)) {
            path.add(current);
            current = parent[current.row][current.col];
        }

        Collections.reverse(path);
        return path;
    }

    public static boolean bfs(char[][] maze, Coord start, Coord destination) {
        int rows = maze.length;
        int cols = maze[0].length;

        maze[start.row][start.col] = 'O';
        Coord[][] parent = new Coord[rows][cols];

        Queue<Coord> q = new LinkedList<>();
        q.add(start);

        int[] dr = { -1, 1, 0, 0 }; // Up, Down, Left, Right
        int[] dc = { 0, 0, -1, 1 };

        while (!q.isEmpty()) {
            Coord current = q.poll();

            if (current.row == destination.row && current.col == destination.col) {
                List<Coord> path = reconstructPath(parent, start, destination);
                System.out.println("Solution Path:");
                printMaze(maze);
                return true;
            }

            for (int i = 0; i < 4; ++i) {
                int newRow = current.row + dr[i];
                int newCol = current.col + dc[i];

                if (isValid(newRow, newCol, rows, cols)
                        && (maze[newRow][newCol] == '.' || maze[newRow][newCol] == 'F')) {
                    maze[newRow][newCol] = 'O';
                    parent[newRow][newCol] = current;
                    q.add(new Coord(newRow, newCol));
                }
            }
        }

        System.out.println("No Path Found.");
        return false;
    }

    public static void main(String[] args) {
        char[][] maze = {
                { 'S', '.', '.', '#', '.', '.', '.' },
                { '#', '#', '.', '#', '.', '#', '.' },
                { '.', '.', '.', '.', '.', '.', '#' },
                { '#', '#', '.', '#', '#', '#', '#' },
                { '#', '#', '.', '.', '.', '.', '.' },
                { '#', '#', '.', '#', '#', '#', '.' },
                { '#', '.', '#', '.', '.', '.', 'F' }
        };

        Coord start = new Coord(0, 0);
        Coord destination = new Coord(6, 6);

        System.out.println("Maze:");
        printMaze(maze);

        bfs(maze, start, destination);
    }
}
