package Users;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    private final String username;
    private final String password;
    private final String fullName;
    private final Date dob;
    private final String address;
    private final int phoneNumber;
    private final String email;
    private final String userType;
    private final String status;
    private final List<String> logHistory;

    // Constructor for initializing Users.User attributes
    public User(String username, String password, String fullName, Date dob, String address, int phoneNumber, String email, String userType, String status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.userType = userType;
        this.status = status;
        this.logHistory = new ArrayList<>();
    }

    // Getters for user attributes
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public Date getDob() {
        return dob;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getUserType() {
        return userType;
    }

    public String getStatus() {
        return status;
    }

    // Methods for user actions
    public void LOGIN() {
        String logEntry = "Users.User " + username + " logged in at " + new Date();
        logHistory.add(logEntry);
        System.out.println(logEntry);
    }

    public void LOGOUT() {
        String logEntry = "Users.User " + username + " logged out at " + new Date();
        logHistory.add(logEntry);
        System.out.println(logEntry);
    }

    public void reviewLogHistory() {
        System.out.println("Log history for " + fullName + ":");
        for (String log : logHistory) {
            System.out.println(log);
        }
    }

    public void exportLogHistoryToCSV(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String log : logHistory) {
                writer.write(log);
                writer.newLine();
            }
            System.out.println("Log history exported to " + filename);
        } catch (IOException e) {
            System.err.println("An error occurred while exporting the log history: " + e.getMessage());
        }
    }
}


