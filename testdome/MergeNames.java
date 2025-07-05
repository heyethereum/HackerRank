import java.util.*;
import java.util.stream.Stream;

public class MergeNames {
    
    public static String[] uniqueNames(String[] names1, String[] names2) {
        // Use LinkedHashSet to maintain insertion order and remove duplicates
        Set<String> uniqueSet = new LinkedHashSet<>();
        
        // Add all names from first array
        if (names1 != null) {
            for (String name : names1) {
                if (name != null) {
                    uniqueSet.add(name);
                }
            }
        }
        
        // Add all names from second array
        if (names2 != null) {
            for (String name : names2) {
                if (name != null) {
                    uniqueSet.add(name);
                }
            }
        }
        
        // Convert set to array
        return uniqueSet.toArray(new String[0]);
    }
    
    // Alternative implementation using Collections.addAll
    public static String[] uniqueNamesAlternative1(String[] names1, String[] names2) {
        Set<String> uniqueSet = new LinkedHashSet<>();
        
        if (names1 != null) {
            Collections.addAll(uniqueSet, names1);
        }
        if (names2 != null) {
            Collections.addAll(uniqueSet, names2);
        }
        
        return uniqueSet.toArray(new String[0]);
    }
    
    // Alternative implementation using Streams (Java 8+)
    public static String[] uniqueNamesAlternative2(String[] names1, String[] names2) {
        return Stream.concat(
                names1 != null ? Arrays.stream(names1) : Stream.empty(),
                names2 != null ? Arrays.stream(names2) : Stream.empty()
            )
            .filter(Objects::nonNull) // Filter out null values
            .distinct() // Remove duplicates
            .toArray(String[]::new);
    }
    
    // Alternative using HashSet + sorting if order doesn't matter
    public static String[] uniqueNamesSorted(String[] names1, String[] names2) {
        Set<String> uniqueSet = new HashSet<>();
        
        if (names1 != null) {
            Collections.addAll(uniqueSet, names1);
        }
        if (names2 != null) {
            Collections.addAll(uniqueSet, names2);
        }
        
        String[] result = uniqueSet.toArray(new String[0]);
        Arrays.sort(result); // Sort alphabetically
        Arrays.sort(result, Collections.reverseOrder()); // reverse sort
        return result;
    }
    
    public static void main(String[] args) {
        // Test the exact example from the problem
        System.out.println("=== Problem Example ===");
        String[] names1 = {"Ava", "Emma", "Olivia"};
        String[] names2 = {"Olivia", "Sophia", "Emma"};
        
        System.out.println("Array 1: " + Arrays.toString(names1));
        System.out.println("Array 2: " + Arrays.toString(names2));
        
        String[] result = uniqueNames(names1, names2);
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("Expected: Ava, Emma, Olivia, Sophia (in any order)");
        System.out.println();
        
        // Test all implementations
        System.out.println("=== Testing All Implementations ===");
        System.out.println("Main solution: " + Arrays.toString(uniqueNames(names1, names2)));
        System.out.println("Alternative 1: " + Arrays.toString(uniqueNamesAlternative1(names1, names2)));
        System.out.println("Alternative 2: " + Arrays.toString(uniqueNamesAlternative2(names1, names2)));
        System.out.println("Sorted version: " + Arrays.toString(uniqueNamesSorted(names1, names2)));
        System.out.println();
        
   
    }
    
}