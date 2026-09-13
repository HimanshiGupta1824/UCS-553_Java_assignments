public class Q2_NestedTryCatch {

    public static void main(String[] args) {

        try {
            System.out.println("Outer try block started.");

            try {
                
                int a = 10 / 0;
                System.out.println(a);

            } catch (ArithmeticException e) {
                System.out.println("Inner Catch: ArithmeticException caught.");
            }

            try {
                
                int[] arr = {1, 2, 3};
                System.out.println(arr[5]);

            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Inner Catch: ArrayIndexOutOfBoundsException caught.");
            }

            
            String str = null;
            System.out.println(str.length());

        } catch (NullPointerException e) {
            System.out.println("Outer Catch: NullPointerException caught.");
        }

        System.out.println("Program continues normally.");
    }
}