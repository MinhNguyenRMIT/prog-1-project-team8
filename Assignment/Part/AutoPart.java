package Assignment.Part;

import java.io.Serializable;
import java.util.ArrayList;

public class AutoPart implements Serializable  {
    private int partId;          // Unique ID for the part (formatted as p-number)
    private String partName;        // Name of the part
    private String manufacturer;    // Manufacturer of the part
    private String partNumber;      // Part number
    private String condition;       // Condition of the part (new, used, refurbished)
    private String warranty;        // Warranty information
    private double cost;            // Cost of the part
    private String notes;

    public static ArrayList<AutoPart> partsList = new ArrayList<AutoPart>();
    // Additional notes

    // Constructor, getters, and setters...

    public AutoPart(int partId, String partName, String manufacturer, String partNumber,
                    String condition, String warranty, double cost, String notes) {
        this.partId = partId;
        this.partName = partName;
        this.manufacturer = manufacturer;
        this.partNumber = partNumber;
        this.condition = condition;
        this.warranty = warranty;
        this.cost = cost;
        this.notes = notes;
    }


    public String toString(){
        return String.format("%-10s %-15s %-15s %-15s %-10s %-10s %-10s %-10s\n",partId, partName, manufacturer, partNumber, condition, warranty, cost, notes);
    }
    // Getters and Setters...

    public int getPartId() {
        return partId;
    }

    public void setPartId(int partId) {
        this.partId = partId;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
