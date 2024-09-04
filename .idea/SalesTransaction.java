import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SalesTransaction {

    private int transactionID;

    private LocalDate transactionDate;

    private int clientID;

    private int salesID;

    private List<String> purchasedItems;

    private double discount;

    private double totalAmount;

    private String additionalNotes;

    public SalesTransaction(int transactionID, LocalDate transactionDate, int clientID, int salesID,
                            List<String> purchasedItems, double discount, double totalAmount, String notes) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salesID = this.salesID;
        this.purchasedItems = purchasedItems != null ? purchasedItems : new ArrayList<>();
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
        return salesID;
    }

    public void setSalespersonID(int salesID) {
        this.salesID = salesID;
    }

    public List<String> getPurchasedItems() {
        return purchasedItems;
    }

    public void setPurchasedItems(List<String> purchasedItems) {
        this.purchasedItems = purchasedItems;
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

    // Method to add an item to the list of purchased items
    public void addPurchasedItem(String itemID) {
        this.purchasedItems.add(itemID);
    }



}