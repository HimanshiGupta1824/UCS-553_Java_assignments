package food.model;
public abstract class FoodOrder{
   private int orderId;
   private String customerName;
   private double amount;

    public FoodOrder(int orderId,String customerName,double amount ){
    this.orderId = orderId;
    this.customerName = customerName;
    this.amount = amount;
    counter++;
   }
   public abstract double calculateDeliveryCharge();

    private static int  counter = 0;
    private static String restaurantName = "Food Express";

   public void setId(int orderId){
        this.orderId = orderId;
    }

    public void setName(String customerName){
        this.customerName = customerName;
    }

    public void setAmount(double  amount){
        this.amount = amount;
    }

    public int getId(){
        return this.orderId;
    }

    public double getAmount(){
        return this.amount;
    }
    public String getName(){
        return this.customerName;
    }

     public static void displayTotalOrders(){
        System.out.println("total orders created has been"+FoodOrder.counter);
    }

    public static String getRestaurantName() {
    return restaurantName;
    }

    public static void setRestaurantName(String restaurantName) {
    FoodOrder.restaurantName = restaurantName;
    }

}