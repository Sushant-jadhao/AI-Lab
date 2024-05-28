import java.util.ArrayList;
import java.util.*;

public class NQueenDFS {
    public static boolean isSafe(int row, int col, char[][] board){
        //horizontal
        for(int c = 0; c <board.length; c++){
            if(board[row][c] == 'Q'){
                return false;
            }
        }

        //vertically
        for(int r = 0; r<board.length; r++){
            if(board[r][col] == 'Q'){
                return false;
            }
        }

        //upper left /
        int r = row;
        for(int c = col; c>=0 && r>=0; r--, c--){
            if(board[r][c] == 'Q'){
                return false;
            }
        }

        //upper right\
        r = row;
        for(int c = col; c<board.length && r>=0; c++, r--){
            if(board[r][c] == 'Q'){
                return false;
            }
        }

        //lower left
        r = row;
        for(int c = col; c>=0 && r<board.length; r++, c--){
            if(board[r][c] == 'Q'){
                return false;
            }
        }

        //lower right
        r = row;
        for(int c = col; c<board.length && r<board.length; r++, c++){
            if(board[r][c] == 'Q'){
                return false;
            }
        }

        return true;
    }


    public static void saveBoard(List<List<String>> allBoard, char[][] board){
        String row;
        List<String> newBoard  = new ArrayList<>();

        for(int i=0; i< board.length; i++){
            row = "";
            for(int j=0; j<board[0].length;j++){
                if(board[i][j] == 'Q'){
                    row += "Q";
                }else{
                    row += ".";
                }
            }
            newBoard.add(row);
        }
        allBoard.add(newBoard);
    }

    public static void helper(List<List<String>> allBoard, char[][] board, int col){
        if(col == board.length){
            saveBoard(allBoard, board);
            return;
        }
        for(int row = 0; row < board.length; row++){
            if(isSafe(row, col, board)){
                board[row][col] = 'Q';
                helper(allBoard, board, col+1);
                board[row][col] = '.';
            }
        }
    }

    public static List<List<String>> printQueens(int n){
        List<List<String>> allBoard = new ArrayList<>();
        char [][] board = new char[n][n];
        helper(allBoard, board, 0);

        return allBoard;
    }
    public static void main(String args[]){
        int n = 5;
        List<List<String>> board =  printQueens(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board.get(i).get(j) + " ");
                System.out.println();
            }
            System.out.println();
        }
    }
}
