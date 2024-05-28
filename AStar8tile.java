import java.util.*;

public class AStar8tile {
    static int levels = 0;

    private static final int[][] initial_state = {
        { 1, 2, 3 },
        { 4, 0, 5 },
        { 6, 7, 8 }
};

// Define the goal state of the puzzle
private static final int[][] goal_state = {
        { 1, 2, 3 },
        { 4, 5, 6 },
        { 7, 8, 0 }
};


    // Define a function to calculate the Manhattan distance heuristic
    private static int heuristic(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] != 0) {
                    int x_goal = (state[i][j] - 1) / 3;
                    int y_goal = (state[i][j] - 1) % 3;
                    distance += Math.abs(x_goal - i) + Math.abs(y_goal - j);
                }
            }
        }
        return distance+levels;
    }




    // Define a function to find the possible next states from the current state
    private static List<int[][]> nextStates(int[][] state) {
        List<int[][]> next_states = new ArrayList<>();
        int[] dx = { 0, 0, 1, -1 }; // Possible moves: right, left, down, up
        int[] dy = { 1, -1, 0, 0 };
        int x = -1, y = -1;
        outerloop: for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (state[i][j] == 0) {
                    x = i;
                    y = j;
                    break outerloop;
                }
            }
        }
        for (int k = 0; k < 4; k++) {
            int new_x = x + dx[k];
            int new_y = y + dy[k];
            if (new_x >= 0 && new_x < 3 && new_y >= 0 && new_y < 3) {
                //clonee the originl statee
                int[][] new_state = new int[state.length][];
                for(int i=0; i<state.length; i++){
                    new_state[i] = state[i].clone();
                }
                new_state[x][y] = new_state[new_x][new_y];
                new_state[new_x][new_y] = 0;
                next_states.add(new_state);
            }
        }
        levels++;
        return next_states;
    }

    private static void printStates(int [][] state){
        for (int[] row : state) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("heuristics: "+  heuristic(state));
        System.out.println("\n");
    }


    // Define the A* algorithm
    private static int[][] aStar(int[][] initial_state, int[][] goal_state) {
        Set<String> visited = new HashSet<>();
        PriorityQueue<int[][]> priority_queue = new PriorityQueue<>(Comparator.comparingInt(AStar8tile::heuristic));
        priority_queue.offer(initial_state);
        while (!priority_queue.isEmpty()) {
            int[][] current_state = priority_queue.poll();

            

            if (Arrays.deepEquals(current_state, goal_state)) {
                return current_state;
            }
            visited.add(Arrays.deepToString(current_state));
            for (int[][] next_state : nextStates(current_state)) {
                if (!visited.contains(Arrays.deepToString(next_state))) {
                    priority_queue.offer(next_state);
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {

        System.out.println("initial State:");
        printStates(initial_state);

        // Solve the 8 puzzle problem using A*
        int[][] solution = aStar(initial_state, goal_state);

        // Print the solution
        if (solution != null) {
            System.out.println("Solution found:");
            for (int[] row : solution) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("Levels required: "+ levels);
        } else {
            System.out.println("No solution found.");
        }
    }
}
