
import java.util.*;

public class bfs {
    static class State {
        int mr, ml, cr, cl;
        boolean bl;

        State(int ml, int cl, int mr, int cr, boolean bl) {
            this.mr = mr;
            this.ml = ml;
            this.cr = cr;
            this.cl = cl;
            this.bl = bl;
        }

        public boolean isValid() {
            if (ml < 0 || mr < 0 || cr < 0 || cl < 0 || (ml != 0 && ml < cl) || (mr != 0 && mr < cr)) {
                return false;
            }

            return true;
        }

        public boolean isGoal() {
            return (ml == 0 && cl == 0);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            State s = (State) obj;
            return ml == s.ml && cl == s.cl && mr == s.mr && cr == s.cr && bl == s.bl;
        }

        @Override
        public int hashCode() {
            return Objects.hash(ml, cl, mr, cr, bl);
        }
    }

    public static List<State> nextStates(State current) {
        int m_moves[] = { 1, 0, 2, 0, 1 };
        int c_moves[] = { 0, 1, 0, 2, 1 };
        List<State> next = new ArrayList<>();

        for (int i = 0; i < m_moves.length; i++) {
            int ml = m_moves[i];
            int cl = c_moves[i];
            int mr = -ml;
            int cr = -cl;
            if (current.bl) {
                State newState = new State(current.ml - ml, current.cl - cl, current.mr - mr, current.cr - cr, false);
                if (newState.isValid()) {
                    next.add(newState);
                }
            } else {
                State newState = new State(current.ml + ml, current.cl + cl, current.mr + mr, current.cr + cr, true);
                if (newState.isValid()) {
                    next.add(newState);
                }
            }
        }
        return next;
    }

    public static void printStates(Map<State, State> parent, State goalState) {
        List<State> result = new ArrayList<>();
        State current = goalState;
        while (current != null) {
            result.add(current);
            current = parent.get(current);
        }
        Collections.reverse(result);
        for (State s : result) {

            System.out.print("M: " + s.ml + " C: " + s.cl);
            if (s.bl) {
                System.out.print("  |boat           |  ");
            } else {
                System.out.print("  |           boat|  ");
            }
            System.out.println("M: " + s.mr + " C: " + s.cr);
        }
    }

    public static void bfsMC() {
        Queue<State> q = new LinkedList<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parent = new HashMap<>();

        State initial = new State(3, 3, 0, 0, true);
        q.offer(initial);
        visited.add(initial);

        while (!q.isEmpty()) {
            State current = q.poll();

            if (current.isGoal()) {
                printStates(parent, current);
                return;
            }

            List<State> newStates = nextStates(current);
            for (State state : newStates) {
                if (!visited.contains(state)) {
                    visited.add(state);
                    q.offer(state);
                    parent.put(state, current);
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(
                "Bredth First Search Missionire and Cannibals Problem");
        bfsMC();
    }
}
