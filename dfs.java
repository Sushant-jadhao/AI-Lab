package AIPrograms;

import java.util.*;

public class dfs {
    static class State{
        int ml, cl, mr, cr;
        boolean bl;


        public State(int ml, int cl, int mr, int cr, boolean bl){
            this.ml = ml;
            this.cl = cl;
            this.mr = mr;
            this.cr = cr;
            this.bl = bl;
        }

        public boolean isValid(){
            if(ml < 0 || cl < 0 || mr < 0 || cr < 0 || (ml!=0 && ml < cl) || (mr!=0 && mr < cr)){
                return false;
            }
            return true;
        }

        public boolean isGoal(){
            return (ml == 0 && cl == 0);
        }

        @Override
        public boolean equals(Object obj){
            if(this == obj) return true;
            if(obj == null || getClass() != obj.getClass()) return false;
            State s = (State) obj;
            return ml==s.ml && cl == s.cl && mr == s.mr && cr == s.cr && bl == s.bl;
        }

        @Override
        public int hashCode(){
            return Objects.hash(ml, cl, mr, cr, bl);
        }

    }


    public static List<State>  nextStates (State current){
        int m_moves [] = {1, 0 , 2, 0, 1};
        int c_moves[] = {0, 1, 0, 2, 1};
        List<State> newStates = new ArrayList<>();

        for(int i=0 ; i< m_moves.length; i++){
            int ml = m_moves[i];
            int cl = c_moves[i];
            int mr = -ml;
            int cr = -cl;
            if(current.bl){
                State newS = new State(current.ml - ml, current.cl - cl, current.mr - mr, current.cr - cr, false);
                if(newS.isValid()){
                    newStates.add(newS);
                }
            }else{
                State newS = new State(current.ml + ml, current.cl + cl, current.mr + mr, current.cr + cr, true);
                if(newS.isValid()){
                    newStates.add(newS);
                }
            }
        }
        return newStates;
    }

    public static void printStates(Map<State, State>parent , State goal){
        List<State> result = new ArrayList<>();
        State curState = goal;
        while(curState != null){
            result.add(curState);
            curState = parent.get(curState);
        }

        Collections.reverse(result);

        for(State s : result){
            System.out.print("M: "+s.ml + " C: "+s.cl);
            if(s.bl){
                System.out.print("|Boat      |");
            }else{
                System.out.print("|      Boat|");
            }
            System.out.println("M: "+s.mr + " C: "+s.cr);
        }
    }

    public static void dfsMC(){
        Stack<State> stack = new Stack<>();
        Set<State> visited = new HashSet<>();
        Map<State, State> parent = new HashMap<>();

        State initiState = new State(3, 3, 0, 0, true);
        stack.push(initiState);
        visited.add(initiState);

        while(!stack.isEmpty()){
            State current = stack.pop();

            if(current.isGoal()){
                printStates(parent, current);
                return;
            }

            List<State> nextState = nextStates(current);
            for(State state : nextState){
                if(!visited.contains(state)){
                    visited.add(state);
                    stack.push(state);
                    parent.put(state, current);
                }
            }
        }
        
    }

    public static void main(String[] args) {
        System.out.println("Depth First Search");
        dfsMC();
    }

}
