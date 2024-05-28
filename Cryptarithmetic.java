import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.*;

public class Cryptarithmetic {

    public static void main(String[] args) {
        String words[] = new String[2];
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the First Word");
        words[0] = sc.next();
        System.out.println("Enter the Second Word");
        words[1] = sc.next();
        System.out.println("Enter the Result string");
        String result = sc.next();
        
        Map<Character, Integer> letterToDigit = new HashMap<>();
        Set<Integer> usedDigits = new HashSet<>();
        
        if (solve(words, result, letterToDigit, usedDigits, 0)) {
            System.out.println("Solution found!");
            for (Map.Entry<Character, Integer> entry : letterToDigit.entrySet()) {
                System.out.println(entry.getKey() + " = " + entry.getValue());
            }
        } else {
            System.out.println("No solution found.");
        }
    }

    private static boolean solve(String[] words, String result, Map<Character, Integer> letterToDigit, Set<Integer> usedDigits, int index) {
        if (index == getUniqueCharacters(words, result).size()) {
            return isValid(words, result, letterToDigit);
        }

        Character currentChar = getUniqueCharacters(words, result).get(index);

        for (int digit = 0; digit <= 9; digit++) {
            if (!usedDigits.contains(digit)) {
                letterToDigit.put(currentChar, digit);
                usedDigits.add(digit);

                if (solve(words, result, letterToDigit, usedDigits, index + 1)) {
                    return true;
                }

                letterToDigit.remove(currentChar);
                usedDigits.remove(digit);
            }
        }

        return false;
    }

    private static boolean isValid(String[] words, String result, Map<Character, Integer> letterToDigit) {
        int sum = 0;

        for (String word : words) {
            sum += toNumber(word, letterToDigit);
        }

        return sum == toNumber(result, letterToDigit);
    }

    private static int toNumber(String word, Map<Character, Integer> letterToDigit) {
        int number = 0;

        for (char c : word.toCharArray()) {
            number = number * 10 + letterToDigit.get(c);
        }

        return number;
    }

    private static List<Character> getUniqueCharacters(String[] words, String result) {
        Set<Character> uniqueChars = new HashSet<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                uniqueChars.add(c);
            }
        }
        for (char c : result.toCharArray()) {
            uniqueChars.add(c);
        }
        return new ArrayList<>(uniqueChars);
    }
}
