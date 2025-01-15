import java.util.Scanner;

interface Device {
    void getSpecifications();
    double calculatePrice();
}

interface Warranty {
    int getWarrantyPeriod(); 
}

abstract class Appliance implements Device,Warranty {
    protected String brand;
    protected String model;
    protected String processor;
    protected int storage; 
    protected int ram;

    public Appliance(String brand, String model, String processor, int storage, int ram) {
        this.brand = brand;
        this.model = model;
        this.processor = processor;
        this.storage = storage;
        this.ram = ram;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
}

class Smartphone extends Appliance{
    private double basePrice;
    private int warrantyMonths;

    public Smartphone(String brand, String model, String processor, int storage, int ram, double basePrice, int warrantyMonths) {
        super(brand, model, processor, storage, ram);
        this.basePrice = basePrice;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Smartphone Specifications:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Processor: " + processor);
        System.out.println("Storage: " + storage + "GB");
        System.out.println("RAM: " + ram + "GB");
    }

    @Override
    public double calculatePrice() {
        return basePrice * 1.10; 
    }

    @Override
    public int getWarrantyPeriod() {
        return warrantyMonths;
    }
}

class Laptop extends Appliance{
    private double basePrice;
    private int warrantyMonths;

    public Laptop(String brand, String model, String processor, int storage, int ram, double basePrice, int warrantyMonths) {
        super(brand, model, processor, storage, ram);
        this.basePrice = basePrice;
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public void getSpecifications() {
        System.out.println("Laptop Specifications:");
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.println("Processor: " + processor);
        System.out.println("Storage: " + storage + "GB");
        System.out.println("RAM: " + ram + "GB");
    }

    @Override
    public double calculatePrice() {
        return basePrice * 1.15; 
    }

    @Override
    public int getWarrantyPeriod() {
        return warrantyMonths;
    }
}

public class MultimediaStore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the device type (Smartphone/Laptop): ");
        String deviceType = scanner.nextLine();

        Appliance device;

        if (deviceType.equalsIgnoreCase("Smartphone")) {
            device = new Smartphone("Samsung", "Galaxy S23", "Snapdragon 8 Gen 2", 256, 8, 700, 24);
        } else if (deviceType.equalsIgnoreCase("Laptop")) {
            device = new Laptop("Dell", "XPS 15", "Intel i7-12700H", 1024, 16, 1200, 36);
        } else {
            System.out.println("Invalid device type!");
            return;
        }

        device.getSpecifications();
        System.out.printf("Price: $%.2f\n", device.calculatePrice());
        System.out.println("Warranty Period: " + ((Warranty) device).getWarrantyPeriod() + " months");
    }
}
