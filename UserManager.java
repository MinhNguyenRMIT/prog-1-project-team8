import Users.User;

import java.util.List;
import java.util.Scanner;

class UserManager {
    private User currentUser;

    public UserManager() {
        this.currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean login(List<User> users, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                currentUser = user;
                currentUser.LOGIN();
                System.out.println("Login successful! Welcome, " + currentUser.getFullName() + ".");
                return true;
            }
        }
        System.out.println("Invalid username or password. Please try again.");
        return false;
    }

    public void logout() {
        if (currentUser != null) {
            currentUser.LOGOUT();
            currentUser = null;
            System.out.println("You have been logged out.");
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void reviewLogHistory() {
        if (currentUser != null) {
            currentUser.reviewLogHistory();
        } else {
            System.out.println("No user is currently logged in.");
        }
    }

    public void modifyUserInfo(Scanner scanner) {
        if (currentUser != null) {
            System.out.print("Enter new full name: ");
            String newFullName = scanner.nextLine();
            // fullName is now immutable, so this method would need to be removed or revised in future updates
        } else {
            System.out.println("No user is currently logged in.");
        }
    }
}