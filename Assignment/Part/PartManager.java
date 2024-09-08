package Assignment.Part;

import java.io.*;
import java.util.*;

public class PartManager implements Serializable {
    public static ArrayList<AutoPart> autoPartList = new ArrayList<>();
    private static Scanner scanner;

    // Create Assignment.Part.AutoPart
    public static void createAutoPart() throws IOException {
        String file = "Assignment/Data/Parts/autoparts.txt";  // Path to the autoparts file
        File newFile = new File(file);
        scanner = new Scanner(System.in);

        // Input and validate unique part ID
        String partId;
        while (true) {
            System.out.println("Enter the Part ID: ");
            partId = scanner.next();
            boolean exists = false;
            for (AutoPart part : autoPartList) {
                if (part.getPartId().equals(partId)) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.out.println("This Part ID already exists, try again.");
            } else {
                break;
            }
        }

        // Input other details from user
        System.out.println("Enter Part Name: ");
        String partName = scanner.next();
        System.out.println("Enter Manufacturer: ");
        String manufacturer = scanner.next();
        System.out.println("Enter Part Number: ");
        String partNumber = scanner.next();
        System.out.println("Enter Condition: ");
        String condition = scanner.next();
        System.out.println("Enter Warranty: ");
        String warranty = scanner.next();
        System.out.println("Enter Part Cost: ");
        double partCost = scanner.nextDouble();
        System.out.println("Enter Notes: ");
        scanner.nextLine(); // Consume leftover newline
        String notes = scanner.nextLine();

        // Add part to the list
        autoPartList.add(new AutoPart(partId, partName, manufacturer, partNumber, condition, warranty, partCost, notes));

        // Save to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(autoPartList);
        oos.close();
    }

    // View AutoParts
    public static void viewAutoParts() throws IOException, ClassNotFoundException {
        String file = "Assignment/Data/Parts/autoparts.txt";
        File newFile = new File(file);
        ObjectInputStream ois = null;

        // Check if file exists and load parts
        if (newFile.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            autoPartList = (ArrayList<AutoPart>) ois.readObject();
            ois.close();
        }

        // Display part details
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-10s\n", "PartID", "Part Name", "Manufacturer", "Part Number", "Condition", "Cost", "Warranty");
        ListIterator<AutoPart> li = autoPartList.listIterator();
        while (li.hasNext()) {
            System.out.println(li.next());
        }
    }

    // Delete Assignment.Part.AutoPart by ID
    public static void deleteAutoPart(String partId) throws IOException {
        String file = "Assignment/Data/Parts/autoparts.txt";  // Path to the autoparts file
        File newFile = new File(file);
        boolean found = false;
        ListIterator<AutoPart> li = autoPartList.listIterator();

        // Iterate and remove part if ID matches
        while (li.hasNext()) {
            AutoPart part = li.next();
            if (part.getPartId().equals(partId)) {
                li.remove();
                System.out.println("Part has been found and removed.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Part ID not found!");
        }

        // Save updated list to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(autoPartList);
        oos.close();
    }

    // Update Assignment.Part.AutoPart by ID
    public static void updateAutoPart(String partId) throws IOException {
        String file = "Assignment/Data/Parts/autoparts.txt";  // Path to the autoparts file
        File newFile = new File(file);
        boolean found = false;
        ListIterator<AutoPart> li = autoPartList.listIterator();

        // Search for the part by ID
        while (li.hasNext()) {
            AutoPart part = li.next();
            if (part.getPartId().equals(partId)) {
                found = true;

                // Prompt the user to update the fields
                scanner = new Scanner(System.in);
                System.out.println("Enter the new Part Name: ");
                String newPartName = scanner.next();
                System.out.println("Enter the new Manufacturer: ");
                String newManufacturer = scanner.next();
                System.out.println("Enter the new Part Number: ");
                String newPartNumber = scanner.next();
                System.out.println("Enter the new Condition: ");
                String newCondition = scanner.next();
                System.out.println("Enter the new Warranty: ");
                String newWarranty = scanner.next();
                System.out.println("Enter the new Part Cost: ");
                double newPartCost = scanner.nextDouble();
                System.out.println("Enter new Notes: ");
                scanner.nextLine(); // Consume leftover newline
                String newNotes = scanner.nextLine();

                // Set the new values for the part
                part.setPartName(newPartName);
                part.setManufacturer(newManufacturer);
                part.setPartNumber(newPartNumber);
                part.setCondition(newCondition);
                part.setWarranty(newWarranty);
                part.setCost(newPartCost);
                part.setNotes(newNotes);

                System.out.println("Part has been updated.");
                break;
            }
        }

        if (!found) {
            System.out.println("Part ID not found!");
        }

        // Save the updated list to the file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(autoPartList);
        oos.close();
    }


    // Search Assignment.Part.AutoPart by ID
    public static void searchAutoPart(String partId) {
        boolean found = false;
        ListIterator<AutoPart> li = autoPartList.listIterator();

        // Display header
        System.out.printf("%-10s %-15s %-15s %-15s %-10s %-10s %-10s\n", "PartID", "Part Name", "Manufacturer", "Part Number", "Condition", "Cost", "Warranty");

        // Search and display part if found
        while (li.hasNext()) {
            AutoPart part = li.next();
            if (part.getPartId().equals(partId)) {
                System.out.println(part);
                found = true;
            }
        }

        // If not found, notify user
        if (!found) {
            System.out.println("Part not found!");
        }
    }
}
