public class TapeEquilibrium {
    
    public static int solutionOptimized(int[] A) {
        int total = 0;
        
        // Calculate total sum
        for (int num : A) {
            total += num;
        }
        
        int minDiff = Integer.MAX_VALUE;
        int leftSum = 0;
        
        for (int i = 1; i < A.length; i++) {
            leftSum += A[i - 1];
            int rightSum = total - leftSum;
            int diff = Math.abs(leftSum - rightSum);
            minDiff = Math.min(minDiff, diff);
        }
        
        return minDiff;
    }
    
    // Alternative implementation with enhanced readability
    public static int findMinTapeEquilibrium(int[] A) {
        if (A.length < 2) {
            throw new IllegalArgumentException("Array must have at least 2 elements");
        }
        
        int total = 0;
        for (int value : A) {
            total += value;
        }
        
        int minDifference = Integer.MAX_VALUE;
        int leftSum = 0;
        
        // Try each possible split point (1 to A.length-1)
        for (int splitPoint = 1; splitPoint < A.length; splitPoint++) {
            leftSum += A[splitPoint - 1];
            int rightSum = total - leftSum;
            int difference = Math.abs(leftSum - rightSum);
            
            if (difference < minDifference) {
                minDifference = difference;
            }
        }
        
        return minDifference;
    }
    
    public static void main(String[] args) {
        // Test cases
        System.out.println("=== Tape Equilibrium Java Solution ===");
        System.out.println();
        
        // Test case 1: Example from problem
        int[] test1 = {3, 1, 2, 4, 3};
        System.out.println("Test 1: " + java.util.Arrays.toString(test1));
        System.out.println("Result: " + solutionOptimized(test1));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test case 2: Simple case
        int[] test2 = {1, 2};
        System.out.println("Test 2: " + java.util.Arrays.toString(test2));
        System.out.println("Result: " + solutionOptimized(test2));
        System.out.println("Expected: 1");
        System.out.println();
        
        // Test case 3: All same values
        int[] test3 = {2, 2, 2, 2};
        System.out.println("Test 3: " + java.util.Arrays.toString(test3));
        System.out.println("Result: " + solutionOptimized(test3));
        System.out.println("Expected: 0");
        System.out.println();
        
        // Test case 4: Negative numbers
        int[] test4 = {-1, -2, -3, 1, 2, 3};
        System.out.println("Test 4: " + java.util.Arrays.toString(test4));
        System.out.println("Result: " + solutionOptimized(test4));
    }
    
   
}