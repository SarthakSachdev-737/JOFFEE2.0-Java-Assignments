import java.util.Scanner;

class Person {
    private String name;
    private double baseSalary;

    public Person(String name, double baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    public double calculateSalary() {
        return baseSalary;
    }
}


class Doctor extends Person {
    private static final double ALLOWANCE_RATE = 0.30; 

    public Doctor(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (getBaseSalary() * ALLOWANCE_RATE);
    }
}


class Nurse extends Person {
    private static final double ALLOWANCE_RATE = 0.15; 

    public Nurse(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (getBaseSalary() * ALLOWANCE_RATE);
    }
}


class OtherStaff extends Person {
    private static final double ALLOWANCE_RATE = 0.10; 

    public OtherStaff(String name, double baseSalary) {
        super(name, baseSalary);
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (getBaseSalary() * ALLOWANCE_RATE);
    }
}

 
public class HospitalStaffManagement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the role (Doctor/Nurse/OtherStaff): ");
        String role = scanner.nextLine();

        System.out.println("Enter the name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the base salary: ");
        double baseSalary = scanner.nextDouble();

        Person person;

        switch (role.toLowerCase()) {
            case "doctor":
                person = new Doctor(name, baseSalary);
                break;
            case "nurse":
                person = new Nurse(name, baseSalary);
                break;
            case "otherstaff":
                person = new OtherStaff(name, baseSalary);
                break;
            default:
                System.out.println("Invalid role entered!");
                return;
        }

        System.out.println("Role: " + role);
        System.out.println("Name: " + person.getName());
        System.out.println("Computed Salary: Rs " + person.calculateSalary());
    }
}
