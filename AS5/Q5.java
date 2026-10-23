import java.util.Objects;
import java.util.Optional;

class Utility {

   
    public static <T> boolean isEqual(T a, T b) {
        return Objects.equals(a, b);
    }

    
    public static <T> T getDefault(
            Optional<T> value, T defaultValue) {

        Objects.requireNonNull(value, "Optional cannot be null");

        return value.orElse(defaultValue);
    }

    
    public static <T extends Comparable<? super T>> T max(T a, T b) {

        Objects.requireNonNull(a, "First value cannot be null");
        Objects.requireNonNull(b, "Second value cannot be null");

        return a.compareTo(b) >= 0 ? a : b;
    }

    
    public static boolean isNull(Object obj) {
        return Objects.isNull(obj);
    }
}

public class Q5 {

    public static void main(String[] args) {

        
        System.out.println("Strings equal: "
                + Utility.isEqual("Java", "Java"));

        System.out.println("Integers equal: "
                + Utility.isEqual(10, 20));

        
        Optional<String> name = Optional.of("Aman");
        Optional<String> emptyName = Optional.empty();

        System.out.println("Name: "
                + Utility.getDefault(name, "Guest"));

        System.out.println("Default name: "
                + Utility.getDefault(emptyName, "Guest"));

        
        System.out.println("Maximum integer: "
                + Utility.max(25, 40));

        System.out.println("Maximum string: "
                + Utility.max("Apple", "Banana"));

        
        String value = null;
        String anotherValue = "Hello";

        System.out.println("Is value null: "
                + Utility.isNull(value));

        System.out.println("Is anotherValue null: "
                + Utility.isNull(anotherValue));
    }
}