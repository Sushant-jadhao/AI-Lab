import java.util.*;
class Pair{
    int j1;
    int j2;
    List<Pair> path;
    Pair(int j1,int j2)
    {
        this.j1=j1;
        this.j2=j2;
        path=new ArrayList<>();
    }

    Pair(int j1,int j2,List<Pair> path1)
    {
         this.j1=j1;
         this.j2=j2;
         path=new ArrayList<>();
         path.addAll(path1);
         path.add(new Pair(this.j1,this.j2));
    }
}
public class waterjugbfs{
    public static void main(String[] args) {
        int jug1=5;
        int jug2=3;
        int target=2;

        getPathIfPossible(jug1,jug2,target);
    }

    public static void getPathIfPossible(int jug1,int jug2,int target)
    {
        boolean[][] visited=new boolean[jug1+1][jug2+1];
        Queue<Pair> queue=new LinkedList<>();
        Pair initialState=new Pair(0,0);
        initialState.path.add(new Pair(0,0));

        queue.offer(initialState);
        while(!queue.isEmpty()){
              Pair curr=queue.poll();

              //If water overflows either of jug
              if(curr.j1>jug1 || curr.j2>jug2 ||  visited[curr.j1][curr.j2])
              {
                continue;
              }
              visited[curr.j1][curr.j2]=true;

              if(curr.j1==target || curr.j2==target)
              {
                if(curr.j1==target)
                {
                     curr.path.add(new Pair(curr.j1,0));
                }
                else{
                    curr.path.add(new Pair(0,curr.j2));
                }
                int n=curr.path.size();
                for(int i=0;i<n;i++)
                {
                    System.out.println(curr.path.get(i).j1+" "+curr.path.get(i).j2);
                }
                return;
              }
             // rem 3 conditions
             //1.Fill the jug and let other be empty
              queue.offer(new Pair(jug1,0,curr.path));
              queue.offer(new Pair(0, jug2,curr.path));

              //2.Fill the jug and let other remain untouched
              queue.offer(new Pair(jug1,curr.j2,curr.path));
              queue.offer(new Pair(curr.j1,jug2,curr.path));

              //3.Empty the jug and let other remain untouched
              queue.offer(new Pair(0,curr.j2,curr.path));
              queue.offer(new Pair(curr.j1,0,curr.path));

              //transfer water from jug1 to jug2
              //transfer water from jug1 to jug2
              int emptyJug=jug2-curr.j2;
              int amttransfer=Math.min(curr.j1,emptyJug);
              int j1=curr.j1-amttransfer;
              int j2=curr.j2+amttransfer;
               queue.offer(new Pair(j1,j2,curr.path));

               //transfer from jug2 to jug1
               emptyJug=jug1-curr.j1;
               amttransfer=Math.min(curr.j2,emptyJug);
               j1=curr.j1+amttransfer;
               j2=curr.j2-amttransfer;
               queue.offer(new Pair(j1,j2,curr.path));
               





        }    
        System.out.println("Not Possible to obtain target");
    }
}
