import java.util.*;

public class MazeBfsDFs {

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // Up, Down, Left, Right
    
    public static void printStates(int[][] maze, Point currentt) {
        maze[currentt.x][currentt.y] = 3;
        System.out.println("\n Maze state: ");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 3) {
                    System.out.print('P' + "\t");
                } else {
                    System.out.print(maze[i][j] + "\t");
                }
            }
            System.out.println();
        }
    }

    // Breadth-first search (BFS) for solving the maze
    static boolean solveMazeBFS(int[][] maze, Point start, Point goal) {
        Queue<Point> queue = new LinkedList<>();
        Set<Point> visited = new HashSet<>();
        queue.add(start);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            if (current.x == goal.x && current.y == goal.y){
                
                printStates(maze, current);
                return true;
                
            }
            visited.add(current);
            // printStates(maze, current);
            maze[current.x][current.y] = 3;

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                Point next = new Point(newX, newY);

                if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length
                        && maze[newX][newY] == 0 && !visited.contains(next)) {
                    queue.add(next);
                }
            }
        }
        return false;
    }
    
    
    static boolean solveMazeDFS(int[][] maze, Point start, Point goal) {
        Stack<Point> queue = new Stack<>();
        Set<Point> visited = new HashSet<>();
        queue.push(start);

        while (!queue.isEmpty()) {
            Point current = queue.pop();
            if (current.x == goal.x && current.y == goal.y){
                
                printStates(maze, current);
                return true;
                
            }
            visited.add(current);
            // printStates(maze, current);
            maze[current.x][current.y] = 3;

            for (int[] dir : directions) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];
                Point next = new Point(newX, newY);

                if (newX >= 0 && newX < maze.length && newY >= 0 && newY < maze[0].length
                        && maze[newX][newY] == 0 && !visited.contains(next)) {
                    queue.push(next);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] maze = {
                { 0, 1, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 0 }
        };
        Point start = new Point(0, 0);
        Point goal = new Point(3, 5);

        System.out.println("BFS: " + solveMazeBFS(maze, start, goal));
        
        int[][] mazed = {
                { 0, 1, 0, 0, 1, 0 },
                { 0, 0, 0, 0, 0, 1 },
                { 1, 0, 0, 1, 0, 0 },
                { 0, 1, 0, 0, 0, 0 }
        };
        Point startd = new Point(0, 0);
        Point goald = new Point(3, 5);

        System.out.println("DFS: " + solveMazeDFS(mazed, startd, goald));
    }
}
