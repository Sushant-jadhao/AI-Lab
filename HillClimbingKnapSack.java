
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class HillClimbingKnapSack {
    

    static class Item {
        int weight;
        int value;

        Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    // Function to calculate the total weight and value of selected items
    public static int[] calculateWeightAndValue(List<Item> items, List<Integer> state) {
        int totalWeight = 0;
        int totalValue = 0;
        for (int i = 0; i < state.size(); i++) {
            if (state.get(i) == 1) {
                totalWeight += items.get(i).weight;
                totalValue += items.get(i).value;
            }
        }
        return new int[]{totalWeight, totalValue};
    }

    // Generate N neighbors by flipping one bit (include/exclude an item)
    public static List<List<Integer>> generateNeighbors(List<Integer> state, int N) {
        List<List<Integer>> neighbors = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < N; i++) {
            List<Integer> neighbor = new ArrayList<>(state);
            int index = rand.nextInt(state.size());
            neighbor.set(index, 1 - neighbor.get(index)); // Flip the bit
            neighbors.add(neighbor);
        }
        return neighbors;
    }

    // Hill climbing algorithm to solve the knapsack problem
    public static List<Integer> nthHillClimbing(List<Item> items, int capacity, int N) {
        Random rand = new Random();
        List<Integer> current = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            current.add(rand.nextInt(2)); // Generate an initial random solution
        }

        while (true) {
            int[] currentResult = calculateWeightAndValue(items, current);
            int currentWeight = currentResult[0];
            int currentValue = currentResult[1];

            if (currentWeight > capacity) {
                // If the current solution is not feasible, fix it by excluding random items
                List<Integer> indices = new ArrayList<>();
                for (int i = 0; i < current.size(); i++) {
                    if (current.get(i) == 1) {
                        indices.add(i);
                    }
                }
                int fixIndex = indices.get(rand.nextInt(indices.size()));
                current.set(fixIndex, 0);
                continue;
            }

            List<List<Integer>> neighbors = generateNeighbors(current, N);
            List<Integer> bestNeighbor = current;
            int bestValue = currentValue;

            for (List<Integer> neighbor : neighbors) {
                int[] neighborResult = calculateWeightAndValue(items, neighbor);
                int neighborWeight = neighborResult[0];
                int neighborValue = neighborResult[1];

                if (neighborWeight <= capacity && neighborValue > bestValue) {
                    bestNeighbor = neighbor;
                    bestValue = neighborValue;
                }
            }

            if (bestNeighbor.equals(current)) {
                break;
            }
            current = bestNeighbor;
        }

        return current;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of items: ");
        int numItems = scanner.nextInt();

        List<Item> items = new ArrayList<>();
        for (int i = 0; i < numItems; i++) {
            System.out.print("Enter weight and value for item " + (i + 1) + ": ");
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            items.add(new Item(weight, value));
        }

        System.out.print("Enter the capacity of the knapsack: ");
        int capacity = scanner.nextInt();

        System.out.print("Enter the number of neighbors to generate (N): ");
        int N = scanner.nextInt();

        List<Integer> solution = nthHillClimbing(items, capacity, N);
        int[] result = calculateWeightAndValue(items, solution);

        System.out.println("Solution: " + solution);
        System.out.println("Total weight: " + result[0]);
        System.out.println("Total value: " + result[1]);
    }
}
