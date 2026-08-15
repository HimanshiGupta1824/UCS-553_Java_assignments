import java.util.Scanner;

public class q9
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int num, power, result = 1;
        System.out.print("Enter number: ");
        num = scanner.nextInt();
        System.out.print("Enter power: ");
        power = scanner.nextInt();
        for(int i = 1; i <= power; i++)
        {
            result = result * num;
        }

        System.out.println("Result = " + result);
        scanner.close();
    }
}