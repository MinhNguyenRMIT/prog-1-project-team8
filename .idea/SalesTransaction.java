import java.time.LocalDate;
import java.util.List;

public class SalesTransaction {

    private int transactionID;

    private LocalDate transactionDate;

    private int clientID;

    private int salesID;

    private List<String> purchasedItems;

    private double discount;

    private double totalAmount;

    // Additional notes about the transaction
    private String additionalNotes;

}