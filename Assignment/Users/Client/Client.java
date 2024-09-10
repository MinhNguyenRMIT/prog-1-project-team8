package Assignment.Users.Client;

import Assignment.Users.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Client extends User {
    public static String membership;
    private final int clientID;
    private static final String clientsTXTPath = "Assignment/Data/Client/clients.tst";
    public static ArrayList<Client> clientList = new ArrayList<>();

    public Client(int clientID, String username, String password, String fullName, Date dob, String address, String phoneNumber, String email, String userType, String status, String membership) {
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.clientID = clientID;
        this.membership = membership;
    }

    public int getClientID() {
        return clientID;
    }

    public String getMembershipType() {
        return membership;
    }

    public void viewSalesTransaction() {
        String line;
        String csvSplitBy = ",";

        System.out.println("Viewing transactions for Client ID: " + getClientID());

        try (BufferedReader br = new BufferedReader(new FileReader(clientsTXTPath))) {
            br.readLine(); // Skip the header line

            while ((line = br.readLine()) != null) {
                String[] transaction = line.split(csvSplitBy);

                int transactionClientID = Integer.parseInt(transaction[1]);
                String transactionID = transaction[0];
                String employeeID = transaction[2];
                String date = transaction[3];
                String totalAmount = transaction[4];

                if (transactionClientID == this.clientID) {
                    System.out.println("TransactionID: " + transactionID + ", Date: " + date + ", Total Amount: $" + totalAmount);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading transactions: " + e.getMessage());
        }
    }

    // Method to read clients from clients.txt
    public static List<Client> readClientsFromTXT(String filePath) throws IOException, ParseException {
        List<Client> clients = new ArrayList<>();
        String line;
        String delimiter = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header

            while ((line = br.readLine()) != null) {
                String[] data = line.split(delimiter);

                int clientID = Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String fullName = data[3];
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(data[4]);
                String address = data[5];
                String phoneNumber = data[6];
                String email = data[7];
                String userType = data[8];
                String status = data[9];

                Client client = new Client(clientID, username, password, fullName, dob, address, phoneNumber, email, userType, status, membership);
                clients.add(client);
            }
        }

        return clients;
    }
}