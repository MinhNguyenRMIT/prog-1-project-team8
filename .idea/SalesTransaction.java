import java.time.LocalDate;
import java.util.List;

public class SalesTransaction {

    private String transactionID;

    private LocalDate transactionDate;

    private String clientID;

    private String salespersonID;

    private List<String> purchasedItems;

    private double discount;

    private double totalAmount;

    // Additional notes about the transaction
    private String notes;

}