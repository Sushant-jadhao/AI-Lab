import java.util.*;
public class graphcoloring{
    final int V=4;
    int color[];
    

    boolean isSafe(int v,int graph[][],int color[],int c)
    {
        for(int i=0;i<V;i++)
        {
        if(graph[v][i]==1 && c==color[i]){
            return false;
        }
    }
        return true;
    }
    boolean graphcoloringUtil(int graph[][],int m,int color[],int v)
    {
        if(v==V)
        {
            return true;
        }
        for(int c=1;c<=m;c++)
        {
            if(isSafe(v, graph, color, c)){
                color[v]=c;

                if(graphcoloringUtil(graph, m, color, v+1)){
                    return true;
                    
                }
                color[v]=0;
            }
        }
        return false;
    }
    boolean graphcoloring(int graph[][],int m){
        color=new int[V];
        for(int i=0;i<V;i++)
        {
            color[i]=0;
        }
        if(!graphcoloringUtil(graph,m,color, 0)){
            System.out.println("Solution does not exist");
        }
        printSolution(color);
        return true;
    }
    void printSolution(int color[])
    {
        System.out.println("Solution exist :");
        for(int i=0;i<V;i++)
        {
            System.out.println(i+1+"-"+color[i]+" ");

        }
    }
    public static void main(String[] args) {
        graphcoloring color=new graphcoloring();
         /* Create following graph and test whether it is
           3 colorable
          (1)---(2)
           | \   |
           |  \  |
           |   \ |
          (3)---(4)
        */
        int graph[][]={
            {0,1,1,1},
            {1,0,0,1},
            {1,0,0,1},
            {1,1,1,0}
        };
        int m=3;
        color.graphcoloring(graph, m);
    }
}