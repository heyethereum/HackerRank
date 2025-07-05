public class SortedSearch {
    
    // OPTIMAL SOLUTION: Binary Search - O(log n)
    public static int countNumbers(int[] sortedArray, int lessThan) {
        if (sortedArray == null || sortedArray.length == 0) {
            return 0;
        }
        
        // Use binary search to find the leftmost position where lessThan could be inserted
        int left = 0;
        int right = sortedArray.length;
        
        while (left < right) {
            int mid = left + (right - left) / 2;
            
            if (sortedArray[mid] < lessThan) {
                left = mid + 1;  // Move right, mid could be included
            } else {
                right = mid;     // Move left, mid is >= lessThan
            }
        }
        
        // 'left' now points to the first element >= lessThan
        // So elements [0, left-1] are all < lessThan
        return left;
    }
    
    // ALTERNATIVE: Using Arrays.binarySearch (built-in)
    public static int countNumbersBuiltIn(int[] sortedArray, int lessThan) {
        if (sortedArray == null || sortedArray.length == 0) {
            return 0;
        }
        
        int insertionPoint = java.util.Arrays.binarySearch(sortedArray, lessThan);
        
        if (insertionPoint >= 0) {
            // lessThan found in array, return its index
            return insertionPoint;
        } else {
            // lessThan not found, insertionPoint = -(insertion point) - 1
            return -(insertionPoint + 1);
        }
    }
    
    // BRUTE FORCE: Linear Search - O(n) for comparison
    public static int countNumbersLinear(int[] sortedArray, int lessThan) {
        if (sortedArray == null || sortedArray.length == 0) {
            return 0;
        }
        
        int count = 0;
        for (int num : sortedArray) {
            if (num < lessThan) {
                count++;
            } else {
                break; // Since array is sorted, no more elements will be < lessThan
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        // Test the main example
        System.out.println("=== Main Example ===");
        int[] array = {1, 3, 5, 7};
        int lessThan = 4;
        
        System.out.println("Array: " + java.util.Arrays.toString(array));
        System.out.println("Count elements < " + lessThan + ":");
        
        int result = countNumbers(array, lessThan);
        System.out.println("Binary Search Result: " + result);
        System.out.println("Expected: 2 (elements 1 and 3 are < 4)");
        System.out.println();
        
        // Test all three approaches
        System.out.println("=== Comparing All Approaches ===");
        System.out.println("Binary Search:  " + countNumbers(array, lessThan));
        System.out.println("Built-in:       " + countNumbersBuiltIn(array, lessThan));
        System.out.println("Linear Search:  " + countNumbersLinear(array, lessThan));
        System.out.println();

    }
    
}