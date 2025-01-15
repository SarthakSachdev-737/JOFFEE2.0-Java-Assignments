import java.io.*;
import java.util.Scanner;

class Employee implements Serializable {
    private String name;
    private int id;
    private String department;
    private double salary;

    public Employee(String name, int id, String department, double salary) {
        this.name = name;
        this.id = id;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" +
               "ID: " + id + "\n" +
               "Department: " + department + "\n" +
               "Salary: " + salary;
    }
}

class FileHandler {
    private final File file;

    public FileHandler(String fileName) {
        this.file = new File(fileName);
    }

    public void saveEmployee(Employee employee) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            if (!file.canWrite()) {
                throw new IOException("Cannot write to the file.");
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(employee);
                System.out.println("Employee saved successfully!");
            }
        } catch (IOException e) {
            System.err.println("Error saving employee details: " + e.getMessage());
        }
    }

    public Employee fetchEmployee() {
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found.");
            }

            if (!file.canRead()) {
                throw new IOException("Cannot read from the file.");
            }

            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (Employee) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error fetching employee details: " + e.getMessage());
            return null;
        }
    }

    public void displayFileProperties() {
        System.out.println("File properties:");
        System.out.println("File size: " + file.length() + " bytes");
        System.out.println("Can read: " + file.canRead());
        System.out.println("Can write: " + file.canWrite());
    }
}

public class EmployeeManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();

        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();

        scanner.nextLine();
        System.out.print("Enter department: ");
        String department = scanner.nextLine();

        System.out.print("Enter salary: ");
        double salary = scanner.nextDouble();

        Employee employee = new Employee(name, id, department, salary);

        FileHandler fileHandler = new FileHandler("employee_data.dat");

        System.out.println("Saving employee details to file...");
        fileHandler.saveEmployee(employee);

        System.out.println("Fetching employee details from file...");
        Employee retrievedEmployee = fileHandler.fetchEmployee();

        if (retrievedEmployee != null) {
            System.out.println("Employee Details:");
            System.out.println(retrievedEmployee);
        }

        fileHandler.displayFileProperties();

        scanner.close();
    }
}
