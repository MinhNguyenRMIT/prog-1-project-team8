package Assignment.Users.Client;

import Assignment.Object.Car.Car;
import Assignment.Transaction.SalesTransaction;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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
}
