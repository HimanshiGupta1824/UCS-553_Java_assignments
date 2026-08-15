package food.service;

import food.model.FoodOrder;
import food.model.Discountable;

public class OrderService {

    public double calculateDiscount(Discountable order) {
        return order.applyDiscount();
    }

    public double calculateDeliveryCharge(FoodOrder order) {
        return order.calculateDeliveryCharge();
    }

    public double calculateFinalPayable(FoodOrder order) {

        double discount = 0;

        if (order instanceof Discountable) {
            discount = ((Discountable) order).applyDiscount();
        }

        double deliveryCharge = order.calculateDeliveryCharge();

        return order.getAmount() - discount + deliveryCharge;
    }
}