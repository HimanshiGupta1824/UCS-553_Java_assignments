import java.util.Scanner;

public class q5{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int num1, num2, r;
        System.out.print("Enter first number: ");
        num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
       num2 = scanner.nextInt();

        r = num1 % num2;
        while(r != 0){
        
            num1 = num2;
            num2 = r;
            r = num1 % num2;
        }
        
        System.out.println("GCD = " + num2);
        scanner.close();
    }

}