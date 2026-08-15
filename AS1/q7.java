import java.util.Scanner;
class q7{

    public static void main(String args[])
    {
        Scanner scanner = new Scanner(System.in);
        int num, count = 0;
        System.out.print("Enter an integer: ");
        num = scanner.nextInt();
        while(num != 0)
        {
            num = num / 10;
            count++;
        }
        System.out.println("Number of digits = " + count);
        scanner.close();
    }
}