import java.util.*;

public class RoutePlanner {
    
    public static boolean routeExists(int fromRow, int fromColumn, int toRow, int toColumn,
                                      boolean[][] mapMatrix) {
        // Check bounds and validity
        if (mapMatrix == null || mapMatrix.length == 0 || mapMatrix[0].length == 0) {
            return false;
        }
        
        int rows = mapMatrix.length;
        int cols = mapMatrix[0].length;
        
        // Check if coordinates are within bounds
        if (fromRow < 0 || fromRow >= rows || fromColumn < 0 || fromColumn >= cols ||
            toRow < 0 || toRow >= rows || toColumn < 0 || toColumn >= cols) {
            return false;
        }
        
        // Check if start and end positions are roads
        if (!mapMatrix[fromRow][fromColumn] || !mapMatrix[toRow][toColumn]) {
            return false;
        }
        
        // If start and end are the same position
        if (fromRow == toRow && fromColumn == toColumn) {
            return true;
        }
        
        // BFS to find path
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];
        
        // Start from the initial position
        queue.offer(new int[]{fromRow, fromColumn});
        visited[fromRow][fromColumn] = true;
        
        // Directions: up, down, left, right
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int currentRow = current[0];
            int currentCol = current[1];
            
            // Explore all 4 directions
            for (int[] direction : directions) {
                int newRow = currentRow + direction[0];
                int newCol = currentCol + direction[1];
                
                // Check if new position is valid, unvisited, and is a road
                if (newRow >= 0 && newRow < rows && 
                    newCol >= 0 && newCol < cols && 
                    !visited[newRow][newCol] && 
                    mapMatrix[newRow][newCol]) {
                    
                    // Check if we reached the destination
                    if (newRow == toRow && newCol == toColumn) {
                        return true;
                    }
                    
                    // Mark as visited and add to queue
                    visited[newRow][newCol] = true;
                    queue.offer(new int[]{newRow, newCol});
                }
            }
        }
        
        return false; // No path found
    }
    
    public static void main(String[] args) {
        boolean[][] mapMatrix = {
            {true,  false, false},
            {true,  true,  false},
            {false, true,  true}
        };
        
        System.out.println("Route from (0,0) to (2,2): " + routeExists(0, 0, 2, 2, mapMatrix));
        
        // Additional test cases
        System.out.println("\n=== Additional Test Cases ===");
        
        // Test same position
        System.out.println("Same position (1,1) to (1,1): " + routeExists(1, 1, 1, 1, mapMatrix));
        
        // Test adjacent positions
        System.out.println("Adjacent (0,0) to (1,0): " + routeExists(0, 0, 1, 0, mapMatrix));
        
        // Test blocked path
        System.out.println("Blocked path (0,0) to (0,2): " + routeExists(0, 0, 0, 2, mapMatrix));
        
        // Test invalid positions
        System.out.println("Out of bounds (0,0) to (5,5): " + routeExists(0, 0, 5, 5, mapMatrix));
        
        // Test starting from blocked position
        System.out.println("From blocked (0,1) to (2,2): " + routeExists(0, 1, 2, 2, mapMatrix));
        
        // Trace the actual path for the main example
        System.out.println("\n=== Path Visualization ===");
        visualizePath(mapMatrix);
    }
    
    // Helper method to visualize the matrix and possible path
    public static void visualizePath(boolean[][] mapMatrix) {
        System.out.println("Matrix layout (R = Road, X = Blocked):");
        for (int i = 0; i < mapMatrix.length; i++) {
            for (int j = 0; j < mapMatrix[i].length; j++) {
                System.out.print((mapMatrix[i][j] ? "R" : "X") + " ");
            }
            System.out.println();
        }
        
        System.out.println("\nPossible path from (0,0) to (2,2):");
        System.out.println("(0,0) -> (1,0) -> (1,1) -> (2,1) -> (2,2)");
    }
}