package Assignment.Users;

import Assignment.Users.Client.Client;
import Assignment.Users.Client.ClientList;
import Assignment.Users.Employee.Employee;
import Assignment.Users.Employee.EmployeeList;
import Assignment.Users.Manager.Manager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class User {
    private final String username;
    private final String password;
    private final String fullName;
    private final Date dob;
    private final String address;
    private final String phoneNo;
    private final String email;
    private final String userType;
    private final String status;
    private final List<String> logHistory;

    // Constructor for initializing Users.User attributes
    public User(String username, String password, String fullName, Date dob, String address, String phoneNumber, String email, String userType, String status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
        this.phoneNo = phoneNumber;
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

    public String getPhoneNumber() {
        return phoneNo;
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

    public static List<User> loadAllUsers() throws IOException, ParseException {
        List<User> allUsers = new ArrayList<>();

        // Load Employees
        List<Employee> employees = EmployeeList.readEmployeesFromTXT("Assignment/Data/Employees/employees.txt");

        allUsers.addAll(employees);

        // Load Clients
        List<Client> clients = ClientList.readClientsFromTXT("Assignment/Data/Client/clients.txt");

        allUsers.addAll(clients);

        // Load Managers
        List<Manager> managers = Manager.readManagersFromTXT("Assignment/Data/Manager/manager.txt");

        allUsers.addAll(managers);

        System.out.println("Total users loaded: " + allUsers.size());
        return allUsers;
    }

    // Methods for user actions
    public static User LOGIN(List<User> userList) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine().trim();
        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        System.out.println("Attempting login for username: " + inputUsername);
        System.out.println("Total users in system: " + userList.size());

        System.out.println("--------------------------------------------------");

        for (User user : userList) {
            // Verify if this user is an Employee, Client, or Manager
            if (user instanceof Employee && user.getUsername().equalsIgnoreCase(inputUsername) && user.getPassword().equals(inputPassword)) {
                System.out.println("Login successful! Welcome, " + user.getFullName() + ". You are logged in as an Employee.");
                return user;
            } else if (user instanceof Client && user.getUsername().equalsIgnoreCase(inputUsername) && user.getPassword().equals(inputPassword)) {
                System.out.println("Login successful! Welcome, " + user.getFullName() + ". You are logged in as a Client.");
                return user;
            } else if (user instanceof Manager && user.getUsername().equalsIgnoreCase(inputUsername) && user.getPassword().equals(inputPassword)) {
                System.out.println("Login successful! Welcome, " + user.getFullName() + ". You are logged in as a Manager.");
                return user;
            }
        }

        System.out.println("Invalid credentials.");
        return null;
    }


    public void LOGOUT() {
    }

    // This method will read from userLog.txt and display logs for the current user based on their username and userType
    public void reviewLogHistory() {
        String filename = "Assignment/Data/userLog.txt";
        try (Scanner scanner = new Scanner(new File(filename))) {

            System.out.println("----------------------------------");
            System.out.println("Log history for " + getFullName() + " (" + getUserType() + "):");
            System.out.println("----------------------------------");

            System.out.println("-----------------------------------------------------------" +
                    "---------------------------------------------------------");
            while (scanner.hasNextLine()) {
                String log = scanner.nextLine();
                if (log.contains("Username: " + getUsername()) && log.contains("UserType: " + getUserType())) {
                    System.out.println(log);
                }

            } System.out.println("-----------------------------------------------------------" +
                    "---------------------------------------------------------");

        } catch (IOException e) {
            System.err.println("An error occurred while reading the log history: " + e.getMessage());
        }
    }

    public void exportLogHistoryToTXT() {
        String filename = "Assignment/Data/userLog.txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            String logEntry = "Username: " + getUsername() + ", Full Name: " + getFullName() +
                    ", UserType: " + getUserType() + ", Status: " + getStatus() +
                    ", Logged in at: " + new Date();
            logHistory.add(logEntry); // Store the log entry in memory

            writer.write(logEntry);  // Write the log entry to the file
            writer.newLine();

        } catch (IOException e) {
            System.err.println("An error occurred while exporting the log history: " + e.getMessage());
        }
    }
}


