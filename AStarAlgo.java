import java.util.*;
class Node{
    public String cityname;
    public final double h_scores;
    public double g_scores;
    public double f_scores;
    public Edge[] adjacencies;
    public Node parent;

    Node(String name,double hval)
    {
      cityname=name;
      h_scores=hval;
    }
    public String toString()
    {
        return cityname;
    }

}
class Edge{
    public double cost;
    public Node target;

    Edge(Node targetNode,double costval)
    {
        target=targetNode;
        cost=costval;
    }
}
public class AStarAlgo{
    public static void main(String[] args) {
        Node n1 = new Node("Arad", 366);
        Node n2 = new Node("Zerind", 374);
        Node n3 = new Node("Oradea", 380);
        Node n4 = new Node("Sibiu", 253);
        Node n5 = new Node("Fagaras", 178);
        Node n6 = new Node("Rimnicu Vilcea", 193);
        Node n7 = new Node("Pitesti", 98);
        Node n8 = new Node("Timisoara", 329);
        Node n9 = new Node("Lugoj", 244);
        Node n10 = new Node("Mehadia", 241);
        Node n11 = new Node("Drobeta", 242);
        Node n12 = new Node("Craiova", 160);
        Node n13 = new Node("Bucharest", 0);
        Node n14 = new Node("Giurgiu", 77);

        // Initialize the edges
        n1.adjacencies = new Edge[]{
                new Edge(n2, 75),
                new Edge(n4, 140),
                new Edge(n8, 118)
        };

        n2.adjacencies = new Edge[]{
                new Edge(n1, 75),
                new Edge(n3, 71)
        };

        n3.adjacencies = new Edge[]{
                new Edge(n2, 71),
                new Edge(n4, 151)
        };

        n4.adjacencies = new Edge[]{
                new Edge(n1, 140),
                new Edge(n5, 99),
                new Edge(n3, 151),
                new Edge(n6, 80),
        };

        n5.adjacencies = new Edge[]{
                new Edge(n4, 99),
                new Edge(n13, 211)
        };

        n6.adjacencies = new Edge[]{
                new Edge(n4, 80),
                new Edge(n7, 97),
                new Edge(n12, 146)
        };

        n7.adjacencies = new Edge[]{
                new Edge(n6, 97),
                new Edge(n13, 101),
                new Edge(n12, 138)
        };

        n8.adjacencies = new Edge[]{
                new Edge(n1, 118),
                new Edge(n9, 111)
        };

        n9.adjacencies = new Edge[]{
                new Edge(n8, 111),
                new Edge(n10, 70)
        };

        n10.adjacencies = new Edge[]{
                new Edge(n9, 70),
                new Edge(n11, 75)
        };

        n11.adjacencies = new Edge[]{
                new Edge(n10, 75),
                new Edge(n12, 120)
        };

        n12.adjacencies = new Edge[]{
                new Edge(n11, 120),
                new Edge(n6, 146),
                new Edge(n7, 138)
        };

        n13.adjacencies = new Edge[]{
                new Edge(n7, 101),
                new Edge(n14, 90),
                new Edge(n5, 211)
        };

        n14.adjacencies = new Edge[]{
                new Edge(n13, 90)
        };
        AStarAlgo(n1,n13);
        List<Node> path=printPath(n13);
        System.out.println("Path:"+path);
    }
   
    public static List<Node> printPath(Node targetNode)
    {
        List<Node> path=new ArrayList<>();
        double pathCost = 0;
        for(Node node=targetNode;node!=null;node=node.parent)
        {
            path.add(node);
        }
        Collections.reverse((path));
        for(int i=0;i<path.size()-1;i++)
        {
            Node current=path.get(i);
            Node next=path.get(i+1);

            for(Edge e:current.adjacencies)
            {
                if(e.target.equals(next)){
                      pathCost+=e.cost;
                      break;
                }
            }
        }
        System.out.println("Path Cost: " + pathCost);
        return path;
    }

    public static void  AStarAlgo(Node source,Node goal)
    {
           Set<Node> visited=new HashSet<>();
           PriorityQueue<Node> pq=new PriorityQueue<>(20,new Comparator<Node>() {
              public int compare(Node i,Node j)
              {
                return Double.compare(i.f_scores, j.f_scores);
              }
           });
           source.g_scores=0;
           pq.add(source);
           boolean found=false;

           while(!pq.isEmpty() && !found)
           {
            Node current=pq.poll();
              if(current.cityname.equals(goal.cityname))
              {
                        found=true;
                        break;
              }
              visited.add(current);

              for(Edge e :current.adjacencies)
              {
                Node child=e.target;
                double cost=e.cost;
                double temp_g_scores=current.g_scores+cost;
                double temp_f_scores=temp_g_scores+child.h_scores;
              
              if(visited.contains(child) && temp_f_scores>=child.f_scores)
              {
                      continue;
              }
              if(!pq.contains(child) || temp_f_scores<child.f_scores)
              {
                child.parent=current;
                child.g_scores=temp_g_scores;
                child.f_scores=temp_f_scores;

                if(pq.contains(child))
                {
                    pq.remove(child);

                }
                pq.add(child);
              }
            
            }
           }

    }
}