package Assignment.Users.Client;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClientList {
    public static ArrayList<Client> clientList = new ArrayList<Client>();

    // Method to read clients from clients.txt
    public static List<Client> readClientsFromTXT(String filePath) throws IOException, ParseException {
        List<Client> clients = new ArrayList<>();
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            br.readLine(); // Skip the header

            while ((line = br.readLine()) != null) {

                // Split the line by space, but respect quoted text for fields like address
                String[] data = line.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");



                if (data.length != 12) {
                    System.err.println("Error: Expected 12 fields but found " + data.length);
                    System.err.println("Offending line: " + line);  // Print the offending line for debugging
                    continue;  // Skip this line and move to the next
                }

                // Remove quotes from the address field (data[6]) if present
                data[6] = data[6].replace("\"", "");

                // Parse fields accordingly
                int clientNumber = Integer.parseInt(data[0]);
                String username = data[1];
                String password = data[2];
                String fullName = data[3] + " " + data[4]; // Combine first and last name
                Date dob = new SimpleDateFormat("yyyy-MM-dd").parse(data[5]);
                String address = data[6];  // Use the quoted address
                String phoneNumber = data[7];
                String email = data[8];
                String userType = data[9];
                String status = data[10];
                String membership = data[11];

                // Create and add the client to the list
                Client client = new Client(clientNumber, username, password, fullName, dob, address, phoneNumber, email, userType, status, membership);
                clients.add(client);
            }
        }

        return clients;
    }


}
