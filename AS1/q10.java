import java.util.Scanner;

public class q10
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int num, original, reverse = 0, r;
        System.out.print("Enter a number: ");
        num = scanner.nextInt();
        original = num;
        while(num != 0)
        {
            r = num % 10;
            reverse = reverse * 10 + r;
            num = num / 10;
        }

        if(original == reverse)
            System.out.println("Palindrome");
        else
            System.out.println("Not Palindrome");
        scanner.close();
    }
}