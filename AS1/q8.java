import java.util.Scanner;
 public class q8
{
    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int num, reverse = 0, r;
        System.out.print("Enter an integer: ");
        num = scanner.nextInt();
        while(num != 0){
            r = num % 10;
            reverse = reverse * 10 + r;
            num = num / 10;
        }
        System.out.println("Reverse = " + reverse);
        scanner.close();
    }
}