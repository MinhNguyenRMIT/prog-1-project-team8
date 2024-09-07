package Users;

import java.util.Date;

public class Client extends User {
    private final String membership;

    public Client(String username, String password, String fullName, Date dob, String address, int phoneNumber, String email, String userType, String status, String membership) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.membership = membership;
    }

    public String getMembershipType() {
        return membership;
    }

    public void viewTransactions() {
        // View client's transactions
        System.out.println("Viewing transactions for " + getFullName());
    }

    public void manageMembership() {
        // Manage the client's membership
        System.out.println("Managing membership for " + getFullName());
    }
}
