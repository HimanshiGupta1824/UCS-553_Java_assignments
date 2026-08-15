import java.util.Scanner;

class q6{
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);
        int num1, num2, x, y, r, gcd, lcm;
        System.out.print("Enter first number: ");
        num1 = scanner.nextInt();
        System.out.print("Enter second number: ");
        num2 = scanner.nextInt();

        x = num1;
        y = num2;
        r = x % y;
        while(r != 0){
            x = y;
            y = r;
            r = x % y;
        }
        gcd = y;
        lcm = (num1 * num2) / gcd;
        scanner.close();
        System.out.println("LCM = " + lcm);
    }
}