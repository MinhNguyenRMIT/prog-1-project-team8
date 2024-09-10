package Assignment.Users.Client;

import Assignment.Object.Car.Car;
import Assignment.Transaction.SalesTransaction;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import static Assignment.Users.Client.Client.membership;

public class ClientList {
    public static ArrayList<Client> clientList = new ArrayList<Client>();

    public static void addClient() throws IOException {
        String file = "Assignment/Data/Client/clients.txt";
        File newFile = new File(file);
        Scanner s = new Scanner(System.in);
        int clientID;
        while (true){
            System.out.println("Enter the Client ID: ");
            clientID = s.nextInt();
            boolean exists = false;
            for (Client client : clientList){
                if (client.getClientID() == clientID){
                    exists = true;
                    break;
                }
            }
            if (exists){
                System.out.println("This Client already exist, try again. ");
            }else {
                break;
            }
        }

        System.out.println("Enter transaction date. ");
        String date = s.next();
        LocalDate transactionDate = LocalDate.parse(date);
        System.out.println("Enter SalesPerson ID. ");
        int salesID = s.nextInt();
        System.out.println("Enter the Item label Cars/Part. ");
        String items = s.nextLine();
        s.nextLine();
        ArrayList<String> purchasedItems = new ArrayList<>();
        for (int i = 0; i < items.length(); i++){
            String item = s.nextLine();
            purchasedItems.add(item);
        }
        System.out.println("Enter discount. ");
        int discount = s.nextInt();
        System.out.println("Enter total amount spend. ");
        double amount = s.nextDouble();

        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(clientID);
        oos.close();
    }

    // Method to read clients from clients.txt
    public static List<Client> readClientsFromTXT(String filePath) throws IOException, ParseException {
        List<Client> clients = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header

            while ((line = br.readLine()) != null) {
                line = line.trim(); // Remove any leading or trailing spaces

                System.out.println("Parsing line: " + line); // Debug print

                // Split the line by space, but respect quoted text for fields like address
                String[] data = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

                System.out.println("Fields found: " + data.length); // Debug print for field count

                if (data.length != 12) {
                    System.err.println("Error: Expected 12 fields but found " + data.length);
                    System.err.println("Offending line: " + line);  // Print the offending line for debugging
                    continue;  // Skip this line and move to the next
                }

                // Remove quotes from the address field (data[6]) if present
                data[6] = data[6].replace("\"", "");

                // Parse fields accordingly
                int clientID = Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String fullName = data[3] + " " + data[4]; // Combine first and last name
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(data[5]);
                String address = data[6];  // Use the quoted address
                String phoneNumber = data[7];
                String email = data[8];
                String userType = data[9];
                String status = data[10];
                String membership = data[11];  // Membership field

                // Debug print for successful parsing
                System.out.println("Successfully parsed client: " + fullName);

                // Create and add the client to the list
                Client client = new Client(clientID, username, password, fullName, dob, address, phoneNumber, email, userType, status, membership);
                clients.add(client);
            }
        }

        return clients;
    }


}
