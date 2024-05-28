import java.util.*;

public class BestFirstSearch1 {
    static class Edge {
        private String placeFrom;
        private int placeFromId;
        private String placeTo;
        private int placeToId;
        private int distance;
        private int heuristics;

        Edge(String placeFrom, int placeFromId, String placeTo, int placeToId, int distance, int heuristics) {
            this.placeFrom = placeFrom;
            this.placeFromId = placeFromId;
            this.placeTo = placeTo;
            this.placeToId = placeToId;
            this.distance = distance;
            this.heuristics = heuristics;
        }

    }

    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();

    public BestFirstSearch1(int n) {
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public static void insert(int p1, int p2, String placeFrom, String placeTo, int distance, int heuristics) {
        graph.get(p1).add(new Edge(placeFrom, p1, placeTo, p2, distance, heuristics));
        graph.get(p2).add(new Edge(placeTo, p2, placeFrom, p1, distance, heuristics));
    }

    // best first search algorithm
    public static void bestFirstSearch(int startNode, int endNode) {
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(edge -> edge.heuristics));
        Set<Integer> visited = new HashSet<>();
        pq.offer(graph.get(startNode).get(0)); // Start with the initial node
        int total_distance = 0;
        ArrayList<Integer> path = new ArrayList<>();

        while (!pq.isEmpty()) {
            Edge currentEdge = pq.poll();

            int currentPlaceToId = currentEdge.placeToId;

            if (!visited.contains(currentPlaceToId)) {
                visited.add(currentPlaceToId);
                path.add(currentPlaceToId);
                total_distance += currentEdge.distance;

                if (currentPlaceToId == endNode) {
                    System.out.println("Path: " + path);
                    System.out.println("Total Path Cost: " + total_distance);
                    return;
                }

                for (Edge neighbor : graph.get(currentPlaceToId)) {
                    if (!visited.contains(neighbor.placeToId)) {
                        pq.offer(neighbor);
                    }
                }
            }
        }
        System.out.println("Destination not reached!");
    }

    public static void main(String args[]) {
        int n = 20;
        BestFirstSearch1 map = new BestFirstSearch1(n);

        // insert the nodes
        // 0= Arad
        // 1=Timisioara
        // 2=Sibiu
        // 3=Zerind
        // 4=Lugoj
        // 5=Riminucu Vilcea
        // 6=Fagarus
        // 7=Oradea
        // 8=Mehadia
        // 9=Craiova
        // 10=Pitesti
        // 11=Bucharest
        // 12=Dr obdeta
        // 13=Giurgia
        // 14=Ur zieemi
        // 15=Hirsova
        // 16=Eforie
        // 17=Vasiui
        // 18=Iasi
        // 19=Neamut
        map.insert(0, 0, "Arad", "Arad", 0, 366);
        map.insert(0, 1, "Arad", "Zerind", 75, 374);
        map.insert(0, 2, "Arad", "Timisoara", 118, 329);
        map.insert(0, 3, "Arad", "Sibiu", 140, 253);
        map.insert(1, 4, "Zerind", "Oradea", 71, 380);
        map.insert(2, 5, "Timisoara", "Lugoj", 111, 244);
        map.insert(3, 4, "Sibiu", "Oradea", 151, 380);
        map.insert(3, 6, "Sibiu", "Fagras", 99, 178);
        map.insert(3, 7, "Sibiu", "Rimnicu Vilcea", 80, 193);
        map.insert(5, 8, "Lugoj", "Mehadia", 70, 241);
        map.insert(8, 19, "Mehadia", "Dobreta", 75, 242);
        map.insert(19, 11, "Dobreta", "Craiova", 120, 160);
        map.insert(6, 9, "Fagras", "Bucharest", 211, 0);
        map.insert(7, 10, "Rimnicu Vilcea", "Pitesti", 97, 98);
        map.insert(7, 11, "Rimnicu Vilcea", "Craiova", 146, 160);
        map.insert(10, 9, "Pitesti", "Bucharest", 101, 0);
        map.insert(10, 11, "Pitesti", "Craiova ", 138, 160);
        map.insert(9, 12, "Bucharest", "Giurgiu", 90, 77);
        map.insert(9, 16, "Bucharest", "Urziceni", 85, 80);
        map.insert(14, 13, "Iasi", "Neamt", 87, 234);
        map.insert(15, 14, "Vaslui", "Iasi", 92, 226);
        map.insert(16, 15, "Urziceni", "Vaslui", 142, 199);
        map.insert(16, 17, "Urziceni", "Hirsova", 98, 151);
        map.insert(17, 18, "Hirsova", "Eforie", 86, 161);

        bestFirstSearch(0, 9); // Start the search from the initial node

    }
}
