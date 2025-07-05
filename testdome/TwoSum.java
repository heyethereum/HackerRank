import java.util.*;

public class TwoSum {
    
    // OPTIMAL SOLUTION: O(n) time, O(n) space
    public static int[] findTwoSum(int[] list, int sum) {
        if (list == null || list.length < 2) {
            return null;
        }
        
        // HashMap to store value -> index mapping
        Map<Integer, Integer> seen = new HashMap<>();
        
        for (int i = 0; i < list.length; i++) {
            int complement = sum - list[i];
            
            // Check if complement exists in our map
            if (seen.containsKey(complement)) {
                // Found the pair! Return indices
                return new int[]{seen.get(complement), i};
            }
            
            // Store current number and its index
            seen.put(list[i], i);
        }
        
        return null; // No pair found
    }
    
    // BRUTE FORCE SOLUTION: O(n²) time, O(1) space
    public static int[] findTwoSumBruteForce(int[] list, int sum) {
        if (list == null || list.length < 2) {
            return null;
        }
        
        for (int i = 0; i < list.length; i++) {
            for (int j = i + 1; j < list.length; j++) {
                if (list[i] + list[j] == sum) {
                    return new int[]{i, j};
                }
            }
        }
        
        return null;
    }
    
    // TWO POINTER SOLUTION: O(n log n) time due to sorting, O(1) extra space
    // Note: This changes the original array order and returns original indices
    public static int[] findTwoSumTwoPointer(int[] list, int sum) {
        if (list == null || list.length < 2) {
            return null;
        }
        
        // Create array of [value, originalIndex] pairs
        int[][] valueIndexPairs = new int[list.length][2];
        for (int i = 0; i < list.length; i++) {
            valueIndexPairs[i][0] = list[i];
            valueIndexPairs[i][1] = i;
        }
        
        // Sort by values
        Arrays.sort(valueIndexPairs, (a, b) -> Integer.compare(a[0], b[0]));
        
        int left = 0, right = list.length - 1;
        
        while (left < right) {
            int currentSum = valueIndexPairs[left][0] + valueIndexPairs[right][0];
            
            if (currentSum == sum) {
                // Return original indices in ascending order
                int idx1 = valueIndexPairs[left][1];
                int idx2 = valueIndexPairs[right][1];
                return new int[]{Math.min(idx1, idx2), Math.max(idx1, idx2)};
            } else if (currentSum < sum) {
                left++;
            } else {
                right--;
            }
        }
        
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Two Sum Solutions ===");
        
        int[] testArray = {3, 1, 5, 7, 5, 9};
        int targetSum = 10;
        
        System.out.println("Array: " + Arrays.toString(testArray));
        System.out.println("Target sum: " + targetSum);
        System.out.println();
        
        // Test optimal solution
        System.out.println("1. OPTIMAL SOLUTION (HashMap):");
        int[] result1 = findTwoSum(testArray, targetSum);
        if (result1 != null) {
            System.out.println("Indices: " + result1[0] + " " + result1[1]);
            System.out.println("Values: " + testArray[result1[0]] + " + " + testArray[result1[1]] + " = " + targetSum);
        } else {
            System.out.println("No solution found");
        }
        System.out.println();
        
        // Test brute force
        System.out.println("2. BRUTE FORCE SOLUTION:");
        int[] result2 = findTwoSumBruteForce(testArray, targetSum);
        if (result2 != null) {
            System.out.println("Indices: " + result2[0] + " " + result2[1]);
            System.out.println("Values: " + testArray[result2[0]] + " + " + testArray[result2[1]] + " = " + targetSum);
        } else {
            System.out.println("No solution found");
        }
        System.out.println();
        
        // Test two pointer (note: this modifies the approach)
        System.out.println("3. TWO POINTER SOLUTION:");
        int[] result3 = findTwoSumTwoPointer(testArray.clone(), targetSum);
        if (result3 != null) {
            System.out.println("Indices: " + result3[0] + " " + result3[1]);
            System.out.println("Values: " + testArray[result3[0]] + " + " + testArray[result3[1]] + " = " + targetSum);
        } else {
            System.out.println("No solution found");
        }

    }
    
}