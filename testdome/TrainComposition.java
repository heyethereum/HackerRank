import java.util.*;

public class TrainComposition {
    private Deque<Integer> wagons;
    
    public TrainComposition() {
        // LinkedList implements Deque and provides O(1) operations at both ends
        this.wagons = new LinkedList<>();
    }
    
    // Attach wagon to the left side (front) of the train
    public void attachWagonFromLeft(int wagonId) {
        wagons.addFirst(wagonId);
    }
    
    // Attach wagon to the right side (back) of the train
    public void attachWagonFromRight(int wagonId) {
        wagons.addLast(wagonId);
    }
    
    // Detach and return wagon from the left side (front) of the train
    public int detachWagonFromLeft() {
        if (wagons.isEmpty()) {
            throw new RuntimeException("No wagons to detach");
        }
        return wagons.removeFirst();
    }
    
    // Detach and return wagon from the right side (back) of the train
    public int detachWagonFromRight() {
        if (wagons.isEmpty()) {
            throw new RuntimeException("No wagons to detach");
        }
        return wagons.removeLast();
    }
    
    // Helper method to visualize the current train composition
    public void printTrain() {
        if (wagons.isEmpty()) {
            System.out.println("Train is empty");
            return;
        }
        
        System.out.print("Train: ");
        System.out.print("[");
        boolean first = true;
        for (Integer wagon : wagons) {
            if (!first) System.out.print(", ");
            System.out.print(wagon);
            first = false;
        }
        System.out.println("] (left to right)");
    }
    
    // Helper method to check if train is empty
    public boolean isEmpty() {
        return wagons.isEmpty();
    }
    
    // Helper method to get current size
    public int size() {
        return wagons.size();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Train Composition Demo ===");
        
        TrainComposition train = new TrainComposition();
        
        System.out.println("1. Starting with empty train:");
        train.printTrain();
        System.out.println();
        
        System.out.println("2. Attach wagon 7 from left:");
        train.attachWagonFromLeft(7);
        train.printTrain();
        System.out.println();
        
        System.out.println("3. Attach wagon 13 from left:");
        train.attachWagonFromLeft(13);
        train.printTrain();
        System.out.println();
        
        System.out.println("4. Detach wagon from right:");
        int detachedRight = train.detachWagonFromRight();
        System.out.println("Detached: " + detachedRight + " (expected: 7)");
        train.printTrain();
        System.out.println();
        
        System.out.println("5. Detach wagon from left:");
        int detachedLeft = train.detachWagonFromLeft();
        System.out.println("Detached: " + detachedLeft + " (expected: 13)");
        train.printTrain();
        System.out.println();
    }
   
}