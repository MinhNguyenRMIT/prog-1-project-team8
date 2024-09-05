import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


public class SalesTransaction {

    private int transactionID;

    private LocalDate transactionDate;

    private int clientID;

    private int salespersonID;

    private List<String> purchasedItems;

    private double discount;

    private double totalAmount;

    private String additionalNotes;

    // Constructor
    public SalesTransaction(int transactionID, LocalDate transactionDate, int clientID, int salespersonID,
                            double discount, double totalAmount, String additionalNotes) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.discount = discount;
        this.totalAmount = totalAmount;
        this.additionalNotes = additionalNotes;
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

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    // Method to calculate the final total amount after applying the discount
    public double calculateFinalAmount() {
        return totalAmount - (totalAmount * discount / 100);
    }

    // Method to add an item to the list of purchased items and save it to a text file
    public void addPurchasedItem(String itemID) {
        String filename = transactionID + "_purchasedItems.txt"; // Each transaction has its own file
        try {
            // Append the purchased item to the text file
            Files.write(Paths.get(filename), (itemID + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Item " + itemID + " added to " + filename);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

    // Method to display transaction details
    public void displayTransactionDetails() {
        System.out.println("Transaction ID: " + transactionID);
        System.out.println("Transaction Date: " + transactionDate);
        System.out.println("Client ID: " + clientID);
        System.out.println("Sales ID: " + salespersonID);
        System.out.println("Purchased Items: " + purchasedItems);
        System.out.println("Discount: " + discount + "%");
        System.out.println("Total Amount (before discount): " + totalAmount);
        System.out.println("Final Amount (after discount): " + calculateFinalAmount());
        System.out.println("Additional Notes: " + additionalNotes);
    }

}