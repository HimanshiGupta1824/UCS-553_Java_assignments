import java.util.Scanner;

class InvalidUsernameException extends Exception {

    public InvalidUsernameException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {

    public InvalidPasswordException(String message) {
        super(message);
    }
}

class AccountLockedException extends Exception {

    public AccountLockedException(String message) {
        super(message);
    }
}

public class Q6_LoginSystem {

    static final String VALID_USERNAME = "admin";
    static final String VALID_PASSWORD = "12345";

    static int failedAttempts = 0;
    static boolean accountLocked = false;

    static void login(String username, String password)
            throws InvalidUsernameException,
                   InvalidPasswordException,
                   AccountLockedException {

        if (accountLocked) {
            throw new AccountLockedException(
                    "Account is locked due to multiple failed attempts."
            );
        }

        if (!username.equals(VALID_USERNAME)) {
            failedAttempts++;

            if (failedAttempts >= 5) {
                accountLocked = true;
            }

            throw new InvalidUsernameException(
                    "Invalid username."
            );
        }

        if (!password.equals(VALID_PASSWORD)) {
            failedAttempts++;

            if (failedAttempts >= 5) {
                accountLocked = true;
            }

            throw new InvalidPasswordException(
                    "Invalid password."
            );
        }

        
        failedAttempts = 0;
        System.out.println("Login successful!");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        for (int attempt = 1; attempt <= 5; attempt++) {

            try {

                System.out.println("\nAttempt " + attempt);

                System.out.print("Enter username: ");
                String username = sc.nextLine();

                System.out.print("Enter password: ");
                String password = sc.nextLine();

                login(username, password);

               
                break;

            } catch (InvalidUsernameException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (InvalidPasswordException e) {

                System.out.println("Error: " + e.getMessage());

            } catch (AccountLockedException e) {

                System.out.println("Error: " + e.getMessage());
                break;
            }

            if (failedAttempts >= 5) {
                System.out.println(
                        "Account locked after 5 failed login attempts."
                );
            } else {
                System.out.println(
                        "Remaining attempts: " + (5 - failedAttempts)
                );
            }
        }

        sc.close();
    }
}