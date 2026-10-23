import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.Scanner;

public class Q4 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

       
        System.out.print("Enter event name: ");
        String eventName = sc.nextLine();

        System.out.print("Enter event date (yyyy-MM-dd): ");
        LocalDate eventDate = LocalDate.parse(sc.nextLine());

        System.out.print("Enter start time (HH:mm): ");
        LocalTime startTime = LocalTime.parse(sc.nextLine());

        System.out.print("Enter venue (press Enter if absent): ");
        String venueInput = sc.nextLine().trim();

        Optional<String> venue = venueInput.isEmpty()
                ? Optional.empty()
                : Optional.of(venueInput);

        
        System.out.println("\n--- Event Details ---");
        System.out.println("Event Name: " + eventName);
        System.out.println("Event Date: " + eventDate);
        System.out.println("Start Time: " + startTime);

        
        LocalDate today = LocalDate.now();

        if (eventDate.isAfter(today)) {
            System.out.println("Event Status: Future");
        } else if (eventDate.isEqual(today)) {
            System.out.println("Event Status: Today");
        } else {
            System.out.println("Event Status: Past");
        }

        
        long daysRemaining =
                ChronoUnit.DAYS.between(today, eventDate);

        System.out.println("Days Remaining: " + daysRemaining);

       
        LocalTime endTime = startTime.plusHours(2);

        System.out.println("End Time: " + endTime);

        
        System.out.println("Venue: "
                + venue.orElse("Venue not specified"));

        sc.close();
    }
}