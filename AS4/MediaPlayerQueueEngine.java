import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class MediaPlayerQueueEngine {

    static class Song {
        private final String title;
        private final String artist;

        public Song(String title, String artist) {
            this.title = title;
            this.artist = artist;
        }

        public String getTitle() {
            return title;
        }

        public String getArtist() {
            return artist;
        }

       
        public boolean sameSong(Song other) {
            return other != null
                    && title.trim().equalsIgnoreCase(other.title.trim())
                    && artist.trim().equalsIgnoreCase(other.artist.trim());
        }

        @Override
        public String toString() {
            return "\"" + title + "\" by " + artist;
        }
    }

    private final LinkedList<Song> playlist = new LinkedList<>();

    public boolean addSong(Song song) {
        if (song == null) {
            System.out.println("Cannot add an empty song.");
            return false;
        }
        if (containsSong(song)) {
            System.out.println("Duplicate skipped: " + song);
            return false;
        }

        playlist.addLast(song);
        System.out.println("Added to playlist: " + song);
        return true;
    }

    public boolean addPrioritySong(Song song) {
        if (song == null) {
            System.out.println("Cannot add an empty song.");
            return false;
        }
        if (containsSong(song)) {
            System.out.println("Duplicate skipped: " + song);
            return false;
        }

        playlist.addFirst(song);
        System.out.println("Priority song added to front: " + song);
        return true;
    }

    private boolean containsSong(Song target) {
        for (Song song : playlist) {
            if (song.sameSong(target)) {
                return true;
            }
        }
        return false;
    }

    public boolean removeSong(String title, String artist) {
        ListIterator<Song> iterator = playlist.listIterator();

        while (iterator.hasNext()) {
            Song current = iterator.next();
            if (current.getTitle().trim().equalsIgnoreCase(title.trim())
                    && current.getArtist().trim().equalsIgnoreCase(artist.trim())) {
                iterator.remove();
                System.out.println("Removed: " + current);
                return true;
            }
        }

        System.out.println("Song not found: \"" + title + "\" by " + artist);
        return false;
    }

    public void displayPlaylist() {
        System.out.println("\n--- Current Playlist ---");
        if (playlist.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        int position = 1;
        for (Song song : playlist) {
            System.out.println(position++ + ". " + song);
        }
    }
 
    public void traverseForwardAndBackward() {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        ListIterator<Song> iterator = playlist.listIterator();

        System.out.println("\nForward traversal:");
        while (iterator.hasNext()) {
            System.out.println("  " + iterator.next());
        }

        System.out.println("\nBackward traversal:");
        while (iterator.hasPrevious()) {
            System.out.println("  " + iterator.previous());
        }
    }

   
    public void interactiveNavigation(Scanner scanner) {
        if (playlist.isEmpty()) {
            System.out.println("Playlist is empty.");
            return;
        }

        ListIterator<Song> iterator = playlist.listIterator();
        Song current = iterator.next();
        System.out.println("Now playing: " + current);

        while (true) {
            System.out.println("\nNavigation: [N]ext, [P]revious, [L]ist, [Q]uit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim().toUpperCase();

            switch (choice) {
                case "N":
                    if (iterator.hasNext()) {
                        current = iterator.next();
                        System.out.println("Now playing: " + current);
                    } else {
                        System.out.println("Already at the end of the playlist.");
                    }
                    break;

                case "P":
                    
                    if (iterator.hasPrevious()) {
                        Song previous = iterator.previous();
                        if (iterator.hasPrevious()) {
                            current = iterator.previous();
                            iterator.next(); 
                            System.out.println("Now playing: " + current);
                        } else {
                            iterator.next(); 
                            System.out.println("Already at the beginning of the playlist.");
                        }
                    } else {
                        System.out.println("Already at the beginning of the playlist.");
                    }
                    break;

                case "L":
                    displayPlaylist();
                    break;

                case "Q":
                    return;

                default:
                    System.out.println("Invalid choice. Enter N, P, L, or Q.");
            }
        }
    }

    public static void main(String[] args) {
        MediaPlayerQueueEngine player = new MediaPlayerQueueEngine();

        System.out.println("===== MEDIA PLAYER QUEUE ENGINE =====");

        player.addSong(new Song("Blinding Lights", "The Weeknd"));
        player.addSong(new Song("Levitating", "Dua Lipa"));
        player.addSong(new Song("Perfect", "Ed Sheeran"));

        System.out.println("\n--- Priority insertion ---");
        player.addPrioritySong(new Song("Believer", "Imagine Dragons"));

        System.out.println("\n--- Duplicate prevention ---");
        player.addSong(new Song("levitating", "DUA LIPA"));

        System.out.println("\n--- Remove a song ---");
        player.removeSong("Perfect", "Ed Sheeran");

        player.displayPlaylist();
        player.traverseForwardAndBackward();

        
    }
}
