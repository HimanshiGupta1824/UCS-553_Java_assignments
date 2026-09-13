import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class ApplicationException extends Exception {

    public ApplicationException(String message) {
        super(message);
    }
}



class ProductException extends ApplicationException {

    public ProductException(String message) {
        super(message);
    }
}

class ProductNotFoundException extends ProductException {

    public ProductNotFoundException(String message) {
        super(message);
    }
}

class OutOfStockException extends ProductException {

    public OutOfStockException(String message) {
        super(message);
    }
}



class PaymentException extends ApplicationException {

    public PaymentException(String message) {
        super(message);
    }
}

class InvalidPaymentException extends PaymentException {

    public InvalidPaymentException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends PaymentException {

    public InsufficientFundsException(String message) {
        super(message);
    }
}



class OrderException extends ApplicationException {

    public OrderException(String message) {
        super(message);
    }
}

class EmptyCartException extends OrderException {

    public EmptyCartException(String message) {
        super(message);
    }
}



class Product {

    int id;
    String name;
    double price;
    int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}


class ShoppingCart {

    private List<Product> cart = new ArrayList<>();

    public void addProduct(Product product)
            throws OutOfStockException {

        if (product.stock <= 0) {
            throw new OutOfStockException(
                    product.name + " is out of stock."
            );
        }

        cart.add(product);
        product.stock--;

        System.out.println(product.name +
                " added to cart successfully.");
    }

    public void removeProduct(int productId)
            throws ProductNotFoundException {

        for (Product product : cart) {

            if (product.id == productId) {
                cart.remove(product);
                product.stock++;

                System.out.println(
                        product.name +
                        " removed from cart."
                );

                return;
            }
        }

        throw new ProductNotFoundException(
                "Product not found in cart."
        );
    }

    public double getTotal()
            throws EmptyCartException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                    "Cart is empty."
            );
        }

        double total = 0;

        for (Product product : cart) {
            total += product.price;
        }

        return total;
    }

    public void displayCart()
            throws EmptyCartException {

        if (cart.isEmpty()) {
            throw new EmptyCartException(
                    "Cart is empty."
            );
        }

        System.out.println("\n----- Shopping Cart -----");

        for (Product product : cart) {
            System.out.println(
                    product.id + " - " +
                    product.name + " - Rs." +
                    product.price
            );
        }

        System.out.println("Total = Rs." + getTotal());
    }
}



class PaymentService {

    public void makePayment(
            double amount,
            double availableFunds,
            String paymentMethod)
            throws InvalidPaymentException,
                   InsufficientFundsException {

        if (paymentMethod == null ||
                paymentMethod.trim().isEmpty()) {

            throw new InvalidPaymentException(
                    "Invalid payment method."
            );
        }

        if (!paymentMethod.equalsIgnoreCase("card") &&
            !paymentMethod.equalsIgnoreCase("upi")) {

            throw new InvalidPaymentException(
                    "Unsupported payment method."
            );
        }

        if (availableFunds < amount) {

            throw new InsufficientFundsException(
                    "Insufficient funds. Required: Rs." +
                    amount +
                    ", Available: Rs." +
                    availableFunds
            );
        }

        System.out.println(
                "Payment of Rs." +
                amount +
                " successful using " +
                paymentMethod
        );
    }
}



public class Q7_ShoppingCartApplication {

    static List<Product> products = new ArrayList<>();

    static {
        products.add(
                new Product(1, "Laptop", 50000, 5)
        );

        products.add(
                new Product(2, "Mobile", 25000, 3)
        );

        products.add(
                new Product(3, "Headphones", 2000, 0)
        );
    }


    static Product searchProduct(int id)
            throws ProductNotFoundException {

        for (Product product : products) {

            if (product.id == id) {
                return product;
            }
        }

        throw new ProductNotFoundException(
                "Product with ID " +
                id +
                " not found."
        );
    }


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ShoppingCart cart = new ShoppingCart();

        try {

            System.out.println("Available Products:");

            for (Product product : products) {
                System.out.println(
                        product.id + ". " +
                        product.name +
                        " - Rs." +
                        product.price +
                        " (Stock: " +
                        product.stock + ")"
                );
            }

            System.out.print(
                    "\nEnter product ID to add: "
            );

            int id = sc.nextInt();

            Product product = searchProduct(id);

            cart.addProduct(product);

            cart.displayCart();


            System.out.print(
                    "\nEnter available funds: Rs."
            );

            double funds = sc.nextDouble();

            System.out.print(
                    "Enter payment method (card/upi): "
            );

            String method = sc.next();

            double total = cart.getTotal();

            PaymentService payment =
                    new PaymentService();

            payment.makePayment(
                    total,
                    funds,
                    method
            );

            System.out.println(
                    "\nOrder placed successfully!"
            );

        } catch (ProductNotFoundException e) {

            System.out.println(
                    "Product Error: " +
                    e.getMessage()
            );

        } catch (OutOfStockException e) {

            System.out.println(
                    "Stock Error: " +
                    e.getMessage()
            );

        } catch (InvalidPaymentException e) {

            System.out.println(
                    "Payment Error: " +
                    e.getMessage()
            );

        } catch (InsufficientFundsException e) {

            System.out.println(
                    "Payment Error: " +
                    e.getMessage()
            );

        } catch (EmptyCartException e) {

            System.out.println(
                    "Order Error: " +
                    e.getMessage()
            );

        } catch (ApplicationException e) {

       
            System.out.println(
                    "Application Error: " +
                    e.getMessage()
            );

        } finally {

            sc.close();

            System.out.println(
                    "Shopping application execution completed."
            );
        }
    }
}