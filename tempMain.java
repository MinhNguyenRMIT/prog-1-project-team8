import Users.Client;
import Users.Employee;
import Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class tempMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<User> users = new ArrayList<>();
        UserManager userManager = new UserManager();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Read users from CSV file
        try (BufferedReader br = new BufferedReader(new FileReader("Users/users.csv"))) {
            String line;

            // Skip the header row
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String type = data[0];
                String username = data[1];
                String password = data[2];
                String fullName = data[3];
                Date dob = dateFormat.parse(data[4]);  // This line was causing the issue
                String address = data[5];
                int phoneNumber = Integer.parseInt(data[6]);
                String email = data[7];
                String userType = data[8];
                String status = data[9];
                String additionalInfo = data[10];

                if (type.equals("Users.Client")) {
                    users.add(new Client(username, password, fullName, dob, address, phoneNumber, email, userType, status, additionalInfo));
                } else if (type.equals("Users.Employee")) {
                    users.add(new Employee(username, password, fullName, dob, address, phoneNumber, email, userType, status, additionalInfo));
                }
            }
        } catch (IOException | ParseException e) {
            System.err.println("An error occurred while reading the users.csv file: " + e.getMessage());
        }


        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Welcome! Please choose an option:");
            if (userManager.getCurrentUser() == null) {
                System.out.println("1. Login");
                System.out.println("2. Exit");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        userManager.login(users, scanner);
                        break;
                    case 2:
                        isRunning = false;
                        break;
                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            } else {
                System.out.println("1. Review Log History");
                System.out.println("2. Modify Personal Information");

                // Additional options for Users.Employee
                if (userManager.getCurrentUser() instanceof Employee) {
                    System.out.println("3. Calculate Revenue");
                    System.out.println("4. List Services");
                    System.out.println("5. Logout");
                }

                // Additional options for Users.Client
                else if (userManager.getCurrentUser() instanceof Client) {
                    System.out.println("3. View Transactions");
                    System.out.println("4. Manage Membership");
                    System.out.println("5. Logout");
                }

                // Logout option for regular Users.User
                else {
                    System.out.println("3. Logout");
                }

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        userManager.reviewLogHistory();
                        break;
                    case 2:
                        userManager.modifyUserInfo(scanner);
                        break;

                    // Users.Employee-specific options
                    case 3:
                        if (userManager.getCurrentUser() instanceof Employee employee) {
                            System.out.print("Enter period (day/week/month): ");
                            String period = scanner.nextLine();
                            employee.calculateRevenue(period);
                        } else if (userManager.getCurrentUser() instanceof Client client) {
                            client.viewTransactions();
                        } else {
                            userManager.logout();
                        }
                        break;

                    case 4:
                        if (userManager.getCurrentUser() instanceof Employee employee) {
                            employee.listServices();
                        } else if (userManager.getCurrentUser() instanceof Client client) {
                            client.manageMembership();
                        }
                        break;

                    case 5:
                        if (userManager.getCurrentUser() instanceof Employee || userManager.getCurrentUser() instanceof Client) {
                            userManager.logout();
                        } else {
                            System.out.println("Invalid option. Please try again.");
                        }
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                }
            }
        }

        scanner.close();
        System.out.println("Thank you for using our system!");
    }
}
