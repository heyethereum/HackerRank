class Node {
    public int value;
    public Node left, right;
    
    public Node(int value, Node left, Node right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
    
    @Override
    public String toString() {
        return "Node(" + value + ")";
    }
}

public class BinarySearchTree {
    
    // ITERATIVE SOLUTION (Recommended - More efficient)
    public static boolean contains(Node root, int value) {
        Node current = root;
        
        while (current != null) {
            if (value == current.value) {
                return true; // Found the value
            } else if (value < current.value) {
                current = current.left; // Go left (smaller values)
            } else {
                current = current.right; // Go right (larger values)
            }
        }
        
        return false; // Value not found
    }
    
    // RECURSIVE SOLUTION (Alternative)
    public static boolean containsRecursive(Node root, int value) {
        // Base case: empty tree or null node
        if (root == null) {
            return false;
        }
        
        // Found the value
        if (value == root.value) {
            return true;
        }
        
        // Recursively search left or right subtree
        if (value < root.value) {
            return containsRecursive(root.left, value);
        } else {
            return containsRecursive(root.right, value);
        }
    }
    
    public static void main(String[] args) {
        // Build the example tree: n2(2) with n1(1) and n3(3)
        Node n1 = new Node(1, null, null);
        Node n3 = new Node(3, null, null);
        Node n2 = new Node(2, n1, n3);
        
        System.out.println("=== BST Search Demo ===");
        System.out.println("Tree structure:");
        System.out.println("    2");
        System.out.println("   / \\");
        System.out.println("  1   3");
        System.out.println();
        
        // Test the example case
        System.out.println("Testing contains(n2, 3):");
        System.out.println("Result: " + contains(n2, 3)); // Should return true
        System.out.println();
        
        // Test all possible searches
        System.out.println("=== Complete Test Suite ===");
        int[] testValues = {1, 2, 3, 0, 4, -1, 5};
        
        for (int testValue : testValues) {
            boolean iterativeResult = contains(n2, testValue);
            boolean recursiveResult = containsRecursive(n2, testValue);
            
            System.out.println("Searching for " + testValue + ":");
            System.out.println("  Iterative: " + iterativeResult);
            System.out.println("  Recursive: " + recursiveResult);
            System.out.println("  Both match: " + (iterativeResult == recursiveResult));
            System.out.println();
        }
    }
    

    
}