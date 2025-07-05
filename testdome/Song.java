import java.util.*;

public class Song {
    private String name;
    private Song nextSong;
    
    public Song(String name) {
        this.name = name;
    }
    
    public void setNextSong(Song nextSong) {
        this.nextSong = nextSong;
    }
    
    public String getName() {
        return name;
    }
    
    public Song getNextSong() {
        return nextSong;
    }
    
    // OPTIMAL SOLUTION: Floyd's Cycle Detection (Tortoise and Hare)
    // Time: O(n), Space: O(1)
    public boolean isInRepeatingPlaylist() {
        if (nextSong == null) {
            return false; // Single song with no next song = no cycle
        }
        
        Song slow = this;      // Tortoise: moves 1 step at a time
        Song fast = this;      // Hare: moves 2 steps at a time
        
        // Move pointers until they meet or fast reaches end
        do {
            slow = slow.nextSong;           // Move slow pointer 1 step
            
            if (fast.nextSong == null) {
                return false;               // Reached end, no cycle
            }
            fast = fast.nextSong;           // Move fast pointer 1 step
            
            if (fast.nextSong == null) {
                return false;               // Reached end, no cycle
            }
            fast = fast.nextSong;           // Move fast pointer another step (total 2 steps)
            
        } while (slow != fast);
        
        return true; // Pointers met, cycle detected!
    }
    
    // ALTERNATIVE SOLUTION: Using HashSet
    // Time: O(n), Space: O(n)
    public boolean isInRepeatingPlaylistHashSet() {
        Set<Song> visited = new HashSet<>();
        Song current = this;
        
        while (current != null) {
            if (visited.contains(current)) {
                return true; // Found a cycle
            }
            visited.add(current);
            current = current.nextSong;
        }
        
        return false; // Reached end without cycle
    }
    
    // Helper method to visualize the playlist
    public void printPlaylist(int maxSteps) {
        Song current = this;
        Set<Song> visited = new HashSet<>();
        int steps = 0;
        
        System.out.print("Playlist: ");
        while (current != null && steps < maxSteps) {
            if (visited.contains(current)) {
                System.out.print("→ [CYCLE DETECTED: " + current.name + "]");
                break;
            }
            
            System.out.print(current.name);
            visited.add(current);
            current = current.nextSong;
            steps++;
            
            if (current != null && steps < maxSteps) {
                System.out.print(" → ");
            }
        }
        
        if (current == null) {
            System.out.print(" → END");
        } else if (steps >= maxSteps) {
            System.out.print(" → ...");
        }
        
        System.out.println();
    }
    
    public static void main(String[] args) {
        System.out.println("=== Song Playlist Cycle Detection ===");
        System.out.println();
        
        // Test Case 1: Repeating playlist (cycle)
        System.out.println("Test 1: Repeating Playlist");
        Song first = new Song("Hello");
        Song second = new Song("Eye of the tiger");
        first.setNextSong(second);
        second.setNextSong(first);
        
        first.printPlaylist(10);
        System.out.println("Is repeating (Floyd's): " + first.isInRepeatingPlaylist());
        System.out.println("Is repeating (HashSet): " + first.isInRepeatingPlaylistHashSet());
        System.out.println("Expected: true");
        System.out.println();
        
        // Test Case 2: Non-repeating playlist (no cycle)
        System.out.println("Test 2: Non-repeating Playlist");
        Song song1 = new Song("Song 1");
        Song song2 = new Song("Song 2");
        Song song3 = new Song("Song 3");
        song1.setNextSong(song2);
        song2.setNextSong(song3);
        // song3.nextSong remains null
        
        song1.printPlaylist(10);
        System.out.println("Is repeating (Floyd's): " + song1.isInRepeatingPlaylist());
        System.out.println("Is repeating (HashSet): " + song1.isInRepeatingPlaylistHashSet());
        System.out.println("Expected: false");
        System.out.println();
        
    }
   
}