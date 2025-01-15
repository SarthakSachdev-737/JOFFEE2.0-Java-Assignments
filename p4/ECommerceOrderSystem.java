import java.util.Scanner;


class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}


class DeliveryNotAvailableException extends Exception {
    public DeliveryNotAvailableException(String message) {
        super(message);
    }
}


abstract class Order {
    protected String productType;
    protected int stock;
    protected String deliveryLocation;

    public Order(String productType, int stock, String deliveryLocation) {
        this.productType = productType;
        this.stock = stock;
        this.deliveryLocation = deliveryLocation;
    }

    public abstract void placeOrder() throws OutOfStockException;

    public void calculateDelivery() throws DeliveryNotAvailableException {
        if (deliveryLocation.equalsIgnoreCase("Outside")) {
            throw new DeliveryNotAvailableException("Delivery not available for the entered location.");
        }
        System.out.println("Delivery is available for your location.");
    }
}


class ElectronicsOrder extends Order {
    public ElectronicsOrder(int stock, String deliveryLocation) {
        super("Electronics", stock, deliveryLocation);
    }

    @Override
    public void placeOrder() throws OutOfStockException {
        if (stock <= 0) {
            throw new OutOfStockException("Out of stock!");
        }
        System.out.println("Electronics order placed successfully.");
    }
}


class GroceryOrder extends Order {
    public GroceryOrder(int stock, String deliveryLocation) {
        super("Grocery", stock, deliveryLocation);
    }

    @Override
    public void placeOrder() throws OutOfStockException {
        if (stock <= 0) {
            throw new OutOfStockException("Out of stock!");
        }
        System.out.println("Grocery order placed successfully.");
    }
}


public class ECommerceOrderSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter product type (Electronics/Grocery): ");
            String productType = scanner.nextLine();

            System.out.println("Enter stock availability: ");
            int stock = scanner.nextInt();
            scanner.nextLine(); 

            System.out.println("Enter delivery location (Within/Outside): ");
            String deliveryLocation = scanner.nextLine();

            Order order;
            if (productType.equalsIgnoreCase("Electronics")) {
                order = new ElectronicsOrder(stock, deliveryLocation);
            } else if (productType.equalsIgnoreCase("Grocery")) {
                order = new GroceryOrder(stock, deliveryLocation);
            } else {
                System.out.println("Invalid product type!");
                return;
            }

            try {
                order.placeOrder();
                order.calculateDelivery();
            } catch (OutOfStockException e) {
                System.out.println("Exception: " + e.getMessage());
            } catch (DeliveryNotAvailableException e) {
                System.out.println("Exception: " + e.getMessage());
            }

        } finally {
            System.out.println("Performing cleanup...");
            scanner.close();
        }
    }
}
