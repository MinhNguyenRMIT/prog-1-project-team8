package Assignment.Part;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    public static void main(String[] args) {
        List<AutoPart> autoPartList = new ArrayList<>();
        Random random = new Random();
        String[] conditions = {"New", "Used", "Refurbish"};
        String[] manufacturers = {"Honda", "Toyota", "Ford", "BMW", "Audi"};
        String[] notesArray = {"None", "Check for defects", "Limited stock"};
        String[] partNames = {"Mirror", "Engine", "Brake", "Tire", "Battery"};
        String[] warranties = {"None", "1 year", "2 years", "3 years"};

        for (int i = 0; i < 31; i++) {
            double cost = Math.round((random.nextDouble() * 1000)/100.00);
            int partId = random.nextInt(35);
            String condition = conditions[random.nextInt(conditions.length)];
            String manufacturer = manufacturers[random.nextInt(manufacturers.length)];
            String notes = notesArray[random.nextInt(notesArray.length)];
            String partName = partNames[random.nextInt(partNames.length)];
            String partNumber = String.valueOf(random.nextInt(1000));
            String warranty = warranties[random.nextInt(warranties.length)];

            AutoPart part = new AutoPart(partId, partName, manufacturer, partNumber, condition, warranty, cost, notes);
            autoPartList.add(part);
        }

        try (FileOutputStream fos = new FileOutputStream("autoparts.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(autoPartList);
            System.out.println("Data has been serialized and saved to autoparts.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
