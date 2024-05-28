import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class MagicSquare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the magic square (must be an odd number): ");
        int n = scanner.nextInt();

        // Ensure the size is odd
        if (n % 2 == 0) {
            System.out.println("The size of the magic square must be an odd number.");
            return;
        }

        int[][] magicSquare = new int[n][n];
        int magicConstant = n * (n * n + 1) / 2;

        if (solveMagicSquare(magicSquare, 0, 0, new HashSet<>(), n)) {
            System.out.println("Magic Constant: " + magicConstant);
            printMagicSquare(magicSquare);
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solveMagicSquare(int[][] magicSquare, int row, int col, Set<Integer> usedNumbers, int n) {
        if (row == n) {
            return isMagicSquare(magicSquare, n);
        }

        if (col == n) {
            return solveMagicSquare(magicSquare, row + 1, 0, usedNumbers, n);
        }

        for (int num = 1; num <= n * n; num++) {
            if (!usedNumbers.contains(num)) {
                magicSquare[row][col] = num;
                usedNumbers.add(num);

                if (solveMagicSquare(magicSquare, row, col + 1, usedNumbers, n)) {
                    return true;
                }

                magicSquare[row][col] = 0;
                usedNumbers.remove(num);
            }
        }

        return false;
    }

    private static boolean isMagicSquare(int[][] magicSquare, int n) {
        int magicConstant = n * (n * n + 1) / 2;

        // Check rows and columns
        for (int i = 0; i < n; i++) {
            int rowSum = 0;
            int colSum = 0;
            for (int j = 0; j < n; j++) {
                rowSum += magicSquare[i][j];
                colSum += magicSquare[j][i];
            }
            if (rowSum != magicConstant || colSum != magicConstant) {
                return false;
            }
        }

        // Check diagonals
        int diag1Sum = 0;
        int diag2Sum = 0;
        for (int i = 0; i < n; i++) {
            diag1Sum += magicSquare[i][i];
            diag2Sum += magicSquare[i][n - i - 1];
        }
        return diag1Sum == magicConstant && diag2Sum == magicConstant;
    }

    private static void printMagicSquare(int[][] magicSquare) {
        for (int[] row : magicSquare) {
            for (int num : row) {
                System.out.print(num + "\t");
            }
            System.out.println();
        }
    }
}
