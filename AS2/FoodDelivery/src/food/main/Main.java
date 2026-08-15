package food.main;

import food.model.FoodOrder;
import food.model.RegularOrder;
import food.model.PremiumOrder;
import food.model.Discountable;
import food.utility.OrderUtility;
import food.service.OrderService;

public class Main {

    public static void main(String[] args) {

        OrderUtility utility = new OrderUtility();
        OrderService service = new OrderService();

        FoodOrder[] orders = new FoodOrder[6];

        orders[0] = new RegularOrder(101, "Rahul", 1000);
        orders[1] = new PremiumOrder(102, "Priya", 2000);
        orders[2] = new RegularOrder(103, "Amit", 1500);
        orders[3] = new PremiumOrder(104, "Sneha", 2500);
        orders[4] = new RegularOrder(105, "Arjun", 800);
        orders[5] = new PremiumOrder(106, "Neha", 3000);

        for (FoodOrder order : orders) {

            System.out.println("----------------------------------");

            System.out.println(
                utility.generateOrderSummary(order)
            );

            double discount = service.calculateDiscount(
                (Discountable) order
            );

            double deliveryCharge =
                service.calculateDeliveryCharge(order);

            double finalPayable =
                service.calculateFinalPayable(order);

            System.out.println("Discount: Rs. " + discount);
            System.out.println("Delivery Charge: Rs. " + deliveryCharge);
            System.out.println("Final Payable: Rs. " + finalPayable);

            System.out.println("----------------------------------");
        }

        FoodOrder.displayTotalOrders();
    }
}