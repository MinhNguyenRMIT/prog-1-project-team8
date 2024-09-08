package Assignment.Transaction;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SalesTransaction implements Serializable{
    private int transactionID;
    private LocalDate transactionDate;
    private int clientID;
    private int salespersonID;
    private ArrayList<String> purchasedItems;
    private double discount;
    private double totalAmount;
    private String additionalNotes;

    // Constructor
    public SalesTransaction(int transactionID, LocalDate transactionDate,int clientID, int salespersonID,ArrayList<String> purchasedItems, double discount, double totalAmount
                            ) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.purchasedItems = purchasedItems;
        this.discount = discount;
        this.totalAmount = totalAmount;
//        this.additionalNotes = additionalNotes;
    }


    public String toString(){
        return String.format("%-10s %-10s %-10s %-10s %-10s %-10s \n",transactionID, transactionDate, clientID, salespersonID, discount, totalAmount);
    }


    // Getter and Setter Methods
    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }


    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }


    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public int getSalespersonID() {
        return salespersonID;
    }
    public void setSalespersonID(int salespersonID) {
        this.salespersonID = salespersonID;
    }


    public double getTotalAmount() {
        return totalAmount;
    }

    // Method to calculate the final total amount after applying the discount
    public double calculateFinalAmount() {
        return totalAmount - (totalAmount * discount / 100);
    }


    // Method to add an item to the list of purchased items and save it to a text file
    public static void addPurchasedItem(String itemID) {
        String filename = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\_purchasedItems.txt"; // Each transaction has its own file
        try {
            // Append the purchased item to the text file
            Files.write(Paths.get(filename), (itemID + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Item " + itemID + " added to " + filename);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }


}