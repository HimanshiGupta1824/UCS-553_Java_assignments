package food.utility;
import food.model.FoodOrder;
public class OrderUtility{

   public  boolean validateAmount(double amount){
      return amount>0;
    }
    public boolean validateCustomerName(String customerName){
        if (customerName == null) return false;
        else return ! customerName.trim().isEmpty();
    }

    public String generateOrderSummary(FoodOrder order) {

    String summary = "";

    summary += "========== ORDER SUMMARY ==========\n";
    summary += "Restaurant: " + FoodOrder.getRestaurantName() + "\n";
    summary += "Order ID: " + order.getId() + "\n";
    summary += "Customer Name: " + order.getName() + "\n";
    summary += "Amount: Rs. " + order.getAmount() + "\n";
    summary += "Delivery Charge: Rs. " + order.calculateDeliveryCharge() + "\n";

    return summary;
}





}