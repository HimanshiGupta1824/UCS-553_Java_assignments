import java.util.Scanner;

public class q3{
    public static long factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
    public static void main(String [] args){
        Scanner scanner = new Scanner(System.in); 
         System.out.println("Enter the number");
        int num = scanner.nextInt();
         System.out.println(factorial(num));

        scanner.close();
    }
}