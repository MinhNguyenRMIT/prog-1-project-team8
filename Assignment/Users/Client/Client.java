package Assignment.Users.Client;

import Assignment.Users.User;
import Assignment.Users.Employee.Employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Date;

public class Client extends User {
    public static String membership;
    private int clientNumber;
    private static final String clientsTXTPath = "Assignment/Data/Client/clients.txt";

    public Client(int clientNumber, String username, String password, String fullName, Date dob, String address, String phoneNumber, String email, String userType, String status, String membership) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.clientNumber = clientNumber;
        this.membership = membership;
    }

    public int getClientID() {
        return clientNumber;
    }

    public String getMembershipType() {
        return membership;
    }

    public void getClientInfo() {
        System.out.println("------------------------------------------------");
        System.out.println(
                "\n--------------" + "\nCLIENT INFO " + "\n--------------" +
                        "\nFull Name: " + getFullName() +
                        "\nBirthdate: " + getDob() +
                        "\nEmail: " + getEmail() +
                        "\nAddress: " + getAddress() +
                        "\nPhoneNumber: " + getPhoneNumber() +
                        "\nStatus: " + getStatus() +
                        "\nMembership: " + getMembershipType()
        );
        System.out.println("------------------------------------------------");
    }

}