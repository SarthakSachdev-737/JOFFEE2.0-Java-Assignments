import java.util.Scanner;

public class EventManager {

    public static class Event {
        private String name;
        private String date;
        private String type;
        private int numberOfParticipants;

        public Event(String name, String date, String type, int numberOfParticipants) {
            this.name = name;
            this.date = date;
            this.type = type;
            this.numberOfParticipants = numberOfParticipants;
        }

        public int getNumberOfParticipants() {
            return numberOfParticipants;
        }

        public String getType() {
            return type;
        }

        public double calculateEventCost() {
            double costPerParticipant;
            switch (type.toLowerCase()) {
                case "conference":
                    costPerParticipant = 20.0;
                    break;
                case "workshop":
                    costPerParticipant = 15.0;
                    break;
                case "seminar":
                    costPerParticipant = 10.0;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid event type!");
            }
            return numberOfParticipants * costPerParticipant;
        }

        @Override
        public String toString() {
            return "Event Name: " + name + "\n" +
                   "Event Type: " + type + "\n" +
                   "Date: " + date + "\n";
        }
    }

    public abstract static class RevenueCalculator {
        public abstract double calculateRevenue(Event event);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter event type (Conference/Workshop/Seminar): ");
        String type = scanner.nextLine();

        System.out.print("Enter event name: ");
        String name = scanner.nextLine();

        System.out.print("Enter event date: ");
        String date = scanner.nextLine();

        System.out.print("Enter number of participants: ");
        int numberOfParticipants = scanner.nextInt();

        Event event = new Event(name, date, type, numberOfParticipants);
        System.out.println("Event added successfully!");

        RevenueCalculator calculator = new RevenueCalculator() {
            @Override
            public double calculateRevenue(Event event) {
                double incomePerParticipant;
                switch (event.getType().toLowerCase()) {
                    case "conference":
                        incomePerParticipant = 100.0;
                        break;
                    case "workshop":
                        incomePerParticipant = 50.0;
                        break;
                    case "seminar":
                        incomePerParticipant = 30.0;
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid event type!");
                }

                double totalIncome = event.getNumberOfParticipants() * incomePerParticipant;
                double totalCost = event.calculateEventCost();

                return totalIncome - totalCost;
            }
        };

        System.out.println("Event Details:");
        System.out.println(event);
        try {
            double eventCost = event.calculateEventCost();
            double revenue = calculator.calculateRevenue(event);
            System.out.printf("Event Cost: Rs. %.2f%n", eventCost);
            System.out.printf("Revenue Generated: Rs. %.2f%n", revenue);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        scanner.close();
    }
}
