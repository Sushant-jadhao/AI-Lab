import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

class State {
    int jug1;
    int jug2;
    String actions;

    State(int jug1, int jug2, String actions) {
        this.jug1 = jug1;
        this.jug2 = jug2;
        this.actions = actions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        State state = (State) o;
        return jug1 == state.jug1 && jug2 == state.jug2;
    }

    @Override
    public int hashCode() {
        return 31 * jug1 + jug2;
    }
}

public class WaterJugProblem {

    static void solveWaterJugProblem(int capacity1, int capacity2, int target) {
        Set<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();

        State initialState = new State(0, 0, "Initial State\n");
        queue.add(initialState);

        while (!queue.isEmpty()) {
            State currentState = queue.poll();

            // If the target is reached
            if (currentState.jug1 == target || currentState.jug2 == target) {
                System.out.println("Solution Found:\n" + currentState.actions);
                return;
            }

            // If this state is already visited, skip it
            if (visited.contains(currentState)) {
                continue;
            }

            visited.add(currentState);

            // Generate all possible next states
            int jug1 = currentState.jug1;
            int jug2 = currentState.jug2;
            String actions = currentState.actions;

            // Fill jug1
            if (jug1 < capacity1) {
                queue.add(new State(capacity1, jug2, actions + "Fill jug1\n"));
            }

            // Fill jug2
            if (jug2 < capacity2) {
                queue.add(new State(jug1, capacity2, actions + "Fill jug2\n"));
            }

            // Empty jug1
            if (jug1 > 0) {
                queue.add(new State(0, jug2, actions + "Empty jug1\n"));
            }

            // Empty jug2
            if (jug2 > 0) {
                queue.add(new State(jug1, 0, actions + "Empty jug2\n"));
            }

            // Pour jug1 into jug2
            int pourJug1ToJug2 = Math.min(jug1, capacity2 - jug2);
            if (pourJug1ToJug2 > 0) {
                queue.add(new State(jug1 - pourJug1ToJug2, jug2 + pourJug1ToJug2, actions + "Pour jug1 into jug2\n"));
            }

            // Pour jug2 into jug1
            int pourJug2ToJug1 = Math.min(jug2, capacity1 - jug1);
            if (pourJug2ToJug1 > 0) {
                queue.add(new State(jug1 + pourJug2ToJug1, jug2 - pourJug2ToJug1, actions + "Pour jug2 into jug1\n"));
            }
        }

        System.out.println("No solution found.");
    }

    public static void main(String[] args) {
        int capacity1 = 3;
        int capacity2 = 5;
        int target = 4;

        solveWaterJugProblem(capacity1, capacity2, target);
    }
}
