public class Q1 {

    public static <T extends Number> double sum(T[] array) {
        double total = 0.0;

        for (T value : array) {
            total += value.doubleValue();
        }

        return total;
    }

    public static void main(String[] args) {
        Integer[] integers = {10, 20, 30, 40};
        Float[] floats = {1.5f, 2.5f, 3.5f};
        Double[] doubles = {10.5, 20.5, 30.5};

        System.out.println("Integer sum: " + sum(integers));
        System.out.println("Float sum: " + sum(floats));
        System.out.println("Double sum: " + sum(doubles));
    }
}