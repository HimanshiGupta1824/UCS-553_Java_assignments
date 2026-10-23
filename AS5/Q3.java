import java.util.Objects;
import java.util.Optional;

class Result<T extends Number> {

    private final String studentName;
    private final Optional<T> marks;
    private final double maximumMarks;
    private final double passPercentage;

    public Result(String studentName, Optional<T> marks,
                  double maximumMarks, double passPercentage) {

        this.studentName = Objects.requireNonNull(
                studentName, "Student name cannot be null");

        this.marks = Objects.requireNonNull(
                marks, "Optional marks cannot be null");

        if (maximumMarks <= 0) {
            throw new IllegalArgumentException(
                    "Maximum marks must be positive");
        }

        if (passPercentage < 0 || passPercentage > 100) {
            throw new IllegalArgumentException(
                    "Pass percentage must be between 0 and 100");
        }

        this.maximumMarks = maximumMarks;
        this.passPercentage = passPercentage;
    }

    public double calculatePercentage(T mark) {
        return (mark.doubleValue() / maximumMarks) * 100.0;
    }

    public boolean hasPassed(T mark) {
        return calculatePercentage(mark) >= passPercentage;
    }

    public void displayResult() {
        System.out.println("Student: " + studentName);

        marks.ifPresentOrElse(mark -> {
            double percentage = calculatePercentage(mark);

            System.out.println("Marks: " + mark);
            System.out.println("Percentage: " + percentage + "%");
            System.out.println("Result: "
                    + (hasPassed(mark) ? "PASS" : "FAIL"));

        }, () -> {
            System.out.println("Marks are not available.");
        });
    }
}

public class Q3 {

    public static void main(String[] args) {

        Result<Integer> student1 = new Result<>(
                "Rahul", Optional.of(85), 100, 40);

        Result<Double> student2 = new Result<>(
                "Priya", Optional.of(32.5), 50, 40);

        Result<Integer> student3 = new Result<>(
                "Aman", Optional.empty(), 100, 40);

        student1.displayResult();
        System.out.println();

        student2.displayResult();
        System.out.println();

        student3.displayResult();
    }
}