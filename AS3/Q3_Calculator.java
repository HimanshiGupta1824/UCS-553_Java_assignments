import java.util.Scanner;

public class Q3_Calculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter first number: ");
            double num1 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter second number: ");
            double num2 = Double.parseDouble(sc.nextLine());

            System.out.print("Enter operator (+, -, *, /): ");
            String operator = sc.nextLine();

            double result;

            switch (operator) {

                case "+":
                    result = num1 + num2;
                    System.out.println("Result = " + result);
                    break;

                case "-":
                    result = num1 - num2;
                    System.out.println("Result = " + result);
                    break;

                case "*":
                    result = num1 * num2;
                    System.out.println("Result = " + result);
                    break;

                case "/":
                    if (num2 == 0) {
                        throw new ArithmeticException("Division by zero is not allowed.");
                    }

                    result = num1 / num2;
                    System.out.println("Result = " + result);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid operator.");
            }

        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values.");

        } catch (ArithmeticException e) {
            System.out.println("Error: " + e.getMessage());

        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());

        } finally {
            sc.close();
            System.out.println("Calculator execution completed.");
        }
    }
}