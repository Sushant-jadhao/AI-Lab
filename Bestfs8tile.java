

import java.util.*;

public class Bestfs8tile {
    public static int [][] initial = {
        {1, 2, 3},
        {4, 6, 0},
        {5, 8, 7}
    };

    public static int[][] goalState = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0},
    };

    public static int levels = 1;


    public static void printStates(int[][] states){
        System.out.println("Best First Search");

        for(int[] list : states){
            System.out.println(Arrays.toString(list));
        }
        System.out.println("Levels Required: " + levels);
    }


    public static List<int[][]> nextStates(int[][] state){
        int dx[] = {0, 0, 1, -1};
        int dy[] = {1, -1, 0, 0};
        int x=0,y=0;
        List<int[][]> result = new ArrayList<>();

        outerloop:for(int i=0; i<3; i++){
            for(int j =0; j<3; j++){
                if(state[i][j] == 0){
                    x = i; y = j;
                    break outerloop;
                }
            }
        }

        for(int k = 0; k < 4; k++){
            int newx = x + dx[k];
            int newy = y + dy[k];

            if(newx < 3 && newy < 3 && newx >=0 && newy >= 0){
                int neww[][] = new int[3][3];
                for(int i=0;i< 3; i++){
                    neww[i] = state[i].clone();
                }

                neww[x][y] = state[newx][newy];
                neww[newx][newy] = 0;
                result.add(neww);
            }
        }

        levels++;
        return result;
    }

    public static int heuristic(int[][] state) {
        int distance = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int value = state[i][j];
                if (value != 0) {
                    int targetRow = (value - 1) / 3; // Target row index in goalState
                    int targetCol = (value - 1) % 3; // Target column index in goalState
                    distance += Math.abs(i - targetRow) + Math.abs(j - targetCol);
                }
            }
        }
        return distance;
    }
    

    public static void best(){
        PriorityQueue<int[][]> queue = new PriorityQueue<>(Comparator.comparingInt(Bestfs8tile::heuristic));
        Set<String> visited = new HashSet<>();
        queue.offer(initial);
        while (!queue.isEmpty()) {
            int[][] current = queue.poll();
            if(Arrays.deepEquals(current, goalState)){
                printStates(current);
                return;
            }
            if(!visited.contains(Arrays.deepToString(current))){
                visited.add(Arrays.deepToString(current));
                List<int[][]> states = nextStates(current);
                for(int[][] state : states){
                    if(!visited.contains(Arrays.deepToString(state))){
                        queue.offer(state);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        best();
    }
}