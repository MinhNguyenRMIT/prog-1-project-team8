package Assignment.Users.Employee;

import Assignment.Object.Car.Car;
import Assignment.Transaction.SalesTransaction;
import Assignment.Users.User;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Employee extends User {
    private final int employeeNumber;
    private final String position;
    private static final String transactionsTXTPath = "Assignment/Data/SalesTransaction/transaction_read.txt";
    private static final String servicesFilePath = "Assignment/Data/Services/service_read.txt";
    public static List<SalesTransaction> transactions = new ArrayList<>();

    public Employee(int employeeNumber, String username, String password, String fullName, Date dob, String address, String phoneNumber, String email, String userType, String status, String position) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.employeeNumber = employeeNumber;
        this.position = position;
    }

    public int getEmployeeID() {
        return employeeNumber;
    }

    public String getPosition() {
        return position;
    }


    public static void calculateRevenue(List<SalesTransaction> transactions, String period) {
        LocalDate now = LocalDate.now(); // Use LocalDate

        long daysLimit = 0;

        // Predefined values for day, week, month, and year in terms of days
        switch (period.toLowerCase()) {
            case "day":
                daysLimit = 1;
                break;
            case "week":
                daysLimit = 7;
                break;
            case "month":
                daysLimit = 30;
                break;
            case "year":
                daysLimit = 365;
                break;
            default:
                System.out.println("Invalid period. Please enter 'day', 'week', 'month', or 'year'.");
                return;
        }

        // Calculate total revenue for the selected period
        double totalRevenue = 0.0;
        for (SalesTransaction transaction : transactions) {
            LocalDate transactionDate = transaction.getTransactionDate();
            if (transactionDate != null) {
                long daysBetween = now.toEpochDay() - transactionDate.toEpochDay(); // Time difference in days

                if (daysBetween <= daysLimit) {
                    totalRevenue += transaction.getTotalAmount();
                }
            }
        }

        System.out.println("-----------------------------------------------");
        switch (period) {
            case "day" -> System.out.println("The total revenue in the past day is: $" + totalRevenue);
            case "week" -> System.out.println("The total revenue in the past week is: $" + totalRevenue);
            case "month" -> System.out.println("The total revenue in the past month is: $" + totalRevenue);
            case "year" -> System.out.println("The total revenue in the past year is: $" + totalRevenue);
        }
        System.out.println("-----------------------------------------------");
    }

    public List<SalesTransaction> readTransactionsFromTXT() {
        List<SalesTransaction> transactions = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(transactionsTXTPath))) {
            br.readLine(); // Skip the header

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove leading or trailing spaces
                String[] data = line.split(" "); // Split on one or more spaces

                if (data.length != 5) {
                    System.err.println("Error: Expected 5 fields but found " + data.length);
                    System.err.println("Offending line: " + line);
                    continue; // Skip invalid lines
                }

                // Parse each field from the transaction file
                int transactionID = Integer.parseInt(data[0]);
                int employeeNumber = Integer.parseInt(data[1]);
                int clientNumber = Integer.parseInt(data[2]);
                LocalDate transactionDate = LocalDate.parse(data[3]);
                double transactionAmount = Double.parseDouble(data[4]);


                ArrayList<Car> purchasedItems = new ArrayList<>();
                double discount = 0.0;  // Default discount

                // Create SalesTransaction object
                SalesTransaction transaction = new SalesTransaction(
                        transactionID,
                        transactionDate,
                        clientNumber,
                        employeeNumber,
                        purchasedItems,
                        discount,
                        transactionAmount
                );

                transactions.add(transaction);
            }

        } catch (IOException e) {
            System.err.println("Error reading Transaction Data file: " + e.getMessage());
        }

        return transactions;
    }

    public static void listServices(String period) {
        LocalDate now = LocalDate.now();
        long timeLimit;

        switch (period.toLowerCase()) {
            case "day":
                timeLimit = 1;  // 1 day
                break;
            case "week":
                timeLimit = 7;  // 7 days in a week
                break;
            case "month":
                timeLimit = 30; // Approximate month duration in days
                break;
            default:
                System.out.println("Invalid period. Please enter 'day', 'week', or 'month'.");
                return;
        }

        List<String> servicesWithinPeriod = new ArrayList<>();
        String servicesFilePath = "Assignment/Data/Services/service_read.txt";


        try (BufferedReader br = new BufferedReader(new FileReader(servicesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();

                // Check if the line is incomplete
                while (line.contains("[") && !line.contains("]")) {
                    // Read the next line and append it to the current line
                    String nextLine = br.readLine();
                    if (nextLine != null) {
                        line += " " + nextLine.trim();  // Append the next line
                    }
                }

                // Ignore lines that don't have at least 6 components (Service ID, Date, etc.)
                if (line.isEmpty() || line.split(" ").length < 6) {
                    System.err.println("Invalid line format: " + line);
                    continue;
                }

                try {
                    String[] data = line.split(" ", 6);

                    // Parse the date in the second column (index 1)
                    LocalDate serviceDate = LocalDate.parse(data[1]);
                    long daysBetween = Math.abs(serviceDate.toEpochDay() - now.toEpochDay());

                    // Check if the service falls within the specified time limit
                    if (daysBetween <= timeLimit) {
                        servicesWithinPeriod.add(line);
                    }

                } catch (DateTimeParseException e) {
                    System.err.println("Invalid date format in line: " + line);
                    continue;  // Skip this line if the date is invalid
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading services.txt file: " + e.getMessage());
        }

        // Display the services that fall within the period
        if (servicesWithinPeriod.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println(" No services found within the given period.");
            System.out.println("--------------------------------------------");
        } else {
            System.out.println("Services in the past " + period + ":");
            for (String service : servicesWithinPeriod) {
                System.out.println(service);
            }
        }
    }

    public static void listCars() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the year to filter cars: ");
        int yearInput = scanner.nextInt();  // Get the user input for the year

        List<Car> carsInYear = new ArrayList<>();
        String filePath = "Assignment/Data/Car/cars_read.txt";  // Path to the car data file

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");  // Assuming the car data is comma-separated

                if (data.length == 7) {  // Check if we have all the required fields
                    int carID = Integer.parseInt(data[0].trim());
                    String model = data[1].trim();
                    int year = Integer.parseInt(data[2].trim());
                    double milage = Double.parseDouble(data[3].trim());
                    String color = data[4].trim();
                    String status = data[5].trim();
                    double price = Double.parseDouble(data[6].trim());

                    // Check if the car was released in the year provided by the user
                    if (year == yearInput) {
                        carsInYear.add(new Car(carID, model, year, milage, color, status, price));
                    }
                } else {
                    System.err.println("Error: Invalid data format in line: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading cars.txt file: " + e.getMessage());
        }

        // Display the cars that match the specified year
        if (carsInYear.isEmpty()) {
            System.out.println("--------------------------------------------");
            System.out.println(" No cars found for the year " + yearInput + ".");
            System.out.println("--------------------------------------------");
        } else {
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s\n", "CNUM", "Model", "Year", "Milage", "Color", "Status", "Price");
            for (Car car : carsInYear) {
                System.out.printf("%-10d %-10s %-10d %-10.2f %-10s %-10s %-10.2f\n",
                        car.getCNumber(), car.getModel(), car.getYear(), car.getMileage(), car.getColor(), car.getStatus(), car.getPrice());
            }
        }
    }
}
