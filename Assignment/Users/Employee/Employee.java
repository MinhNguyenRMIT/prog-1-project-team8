package Assignment.Users.Employee;

import Assignment.Transaction.SalesTransaction;
import Assignment.Users.User;

import java.util.Date;
import java.util.List;

public class Employee extends User {
    private final String position;

    public Employee(String username, String password, String fullName, Date dob, String address, int phoneNumber, String email, String userType, String status, String position) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void calculateRevenue(List<SalesTransaction> transactions, String period) {
        Date now = new Date();
        long timeLimit = 0;

        // Predefined values for day, month, and year in milliseconds
        switch (period.toLowerCase()) {
            case "day":
                timeLimit = 86400000L; // 1 day = 86,400,000 ms
                break;
            case "month":
                timeLimit = (long) 2.628E9; // 1 month = 2.628E+9 ms (30 days)
                break;
            case "year":
                timeLimit = (long) 3.1536E10; // 1 year = 3.1536E+10 ms (365 days)
                break;
            default:
                System.out.println("Invalid period. Please enter 'day', 'month', or 'year'.");
                return;
        }

        // Calculate total revenue for the selected period
        double totalRevenue = 0;
//        for (SalesTransaction transaction : transactions) {
//            LocalDate transactionDate = transaction.getTransactionDate();
//            long timeDifference = now.getTime() - transactionDate.getTime(); // Time difference in ms
//
//            if (timeDifference <= timeLimit) {
//                totalRevenue += transaction.getTotalAmount();
//            }
//        }

        // Display the total revenue
        System.out.println("The total revenue in the past " + period + " is: " + totalRevenue);
    }

    public void listServices() {
        // List services
        System.out.println("Listing services performed by " + getFullName());
    }

    public void processTransaction() {
        // Transactions
        System.out.println("Processing transaction for " + getFullName());
    }
}
