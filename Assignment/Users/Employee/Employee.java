package Assignment.Users.Employee;

import Assignment.Object.Car.Car;
import Assignment.Transaction.SalesTransaction;
import Assignment.Users.User;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Employee extends User {
    private final int employeeID;
    private final String position;
    private static final String transactionsTXTPath = "Assignment/Data/SalesTransaction/transaction_read.txt";
    private static final String servicesFilePath = "Assignment/Data/Services/service_read.txt";
    public static List<SalesTransaction> transactions = new ArrayList<>();

    public Employee(int employeeID, String username, String password, String fullName, Date dob, String address, String phoneNumber, String email, String userType, String status, String position) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.employeeID = employeeID;
        this.position = position;
    }

    public int getEmployeeID() {
        return employeeID;
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




    public List<SalesTransaction> readTransactionsFromCSV() {
        List<SalesTransaction> transactions = new ArrayList<>();
        String line;
        String delimiter = " ";

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
                int employeeID = Integer.parseInt(data[1]);
                int clientID = Integer.parseInt(data[2]);
                LocalDate transactionDate = LocalDate.parse(data[3]);
                double transactionAmount = Double.parseDouble(data[4]);

        // Assuming the CSV doesn't store `purchasedItems` and `discount`, you can initialize them as:
                ArrayList<Car> purchasedItems = new ArrayList<>();  // Empty list for now
                double discount = 0.0;  // Default discount

                // Create SalesTransaction object
                SalesTransaction transaction = new SalesTransaction(
                        transactionID,
                        transactionDate,
                        clientID,
                        employeeID,
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

        // Set the time limit based on the period (similar to calculateRevenue)
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
                System.out.println("Invalid period. Please enter '1', '2', or '3'.");
                return;
        }


        List<String> servicesWithinPeriod = new ArrayList<>();

        // Read the services.txt file
        try (BufferedReader br = new BufferedReader(new FileReader(servicesFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(" ");

                // Assuming that the service date is in the second column (index 1)
                LocalDate serviceDate = LocalDate.parse(data[1]);

                // Manually calculate the difference in days
                long daysBetween = Math.abs(serviceDate.toEpochDay() - now.toEpochDay());

                // Check if the service falls within the specified time limit
                if (daysBetween <= timeLimit) {
                    servicesWithinPeriod.add(line); // Add the service to the list
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
}
