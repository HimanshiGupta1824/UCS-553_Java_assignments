import java.util.Optional;

public class Q2 {

    public static void main(String[] args) {

        
        Optional<String> email =
                Optional.of("student@example.com");

       
        Optional<String> emptyEmail = Optional.empty();

        
        System.out.print("Student email: ");
        email.ifPresent(System.out::println);

        System.out.print("Empty email display: ");
        emptyEmail.ifPresent(System.out::println);
        System.out.println("(No email available)");
 // 4. Provide a default email using orElse()
        String defaultEmail =
                emptyEmail.orElse("default@example.com");

        System.out.println("Default email: " + defaultEmail);

        
        try {
            String requiredEmail = emptyEmail.orElseThrow(
                () -> new IllegalStateException("Email is missing")
            );

            System.out.println("Required email: " + requiredEmail);

        } catch (IllegalStateException e) {
            System.out.println("Exception: " + e.getMessage());
        }
    }
}