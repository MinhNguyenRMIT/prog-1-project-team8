package Users;

import java.util.Date;

public class Employee extends User {
    private final String position;

    public Employee(String username, String password, String fullName, Date dob, String address, int phoneNumber, String email, String userType, String status, String position) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void calculateRevenue(String period) {
        // calculate revenue (day/week/month)
        System.out.println("Calculating revenue for " + getFullName() + " for the period: " + period);
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
