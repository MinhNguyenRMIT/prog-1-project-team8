package Assignment.Services;

import Assignment.Object.Car.Car;
import Assignment.Part.AutoPart;
import Assignment.Transaction.SalesTransaction;

import java.time.LocalDate;
import java.util.*;
import java.io.*;

import static Assignment.Part.AutoPart.partsList;
import static Assignment.Part.PartManager.autoPartList;


public class ServiceManager implements Serializable {
    public static ArrayList<Service> serviceList = new ArrayList<>();
    public static ArrayList<AutoPart> replacedParts = new ArrayList<>();
    private static Scanner scanner;
    ListIterator<Service> li = null;

    //CRUD
    // Create Service
    public static void createService() throws IOException {
        String file = "Assignment/Data/Services/Service.txt";  // Path to the services file
        File newFile = new File(file);
        scanner = new Scanner(System.in);

        // Input and validate unique service ID
        int serviceNum;
        while (true) {
            System.out.println("Enter the Service ID: ");
            serviceNum = scanner.nextInt();
            boolean exists = false;
            for (Service service : serviceList) {
                if (service.getServiceId() == serviceNum) {
                    exists = true;
                    break;
                }
            }
            if (exists) {
                System.out.println("This ID already exists, try again.");
            } else {
                break;
            }
        }

        // Input other details from user
        System.out.println("Enter the Service Date (YYYY-MM-DD): ");
        String date = scanner.next();
        LocalDate serviceDate = LocalDate.parse(date);
        System.out.println("Enter the Client ID: ");
        int clientId = scanner.nextInt();
        System.out.println("Enter the Mechanic ID: ");
        int mechanicId = scanner.nextInt();
        System.out.println("Enter the Service Type: ");
        String serviceType = scanner.next();
        //         Input for replaced parts (if any)
        System.out.println("Do you want to add replaced parts? (yes/no): ");
        String addParts = scanner.next();
        double serviceCost = 0;
        while (addParts.equalsIgnoreCase("yes")) {
            Scanner s = new Scanner(System.in);
            AutoPart foundPart = null;
            // Collect all required details for AutoPart
            System.out.println("Enter Part ID: ");
            int partId = s.nextInt();
            for (AutoPart autoPart : autoPartList){
                if (autoPart.getPartId() == partId){
                    foundPart = autoPart;
                    serviceCost = autoPart.getCost();
                    break;
                }
            }
            // Add part to list using the full constructor
            if (foundPart != null){
                replacedParts.add(foundPart);
            }else {
                System.out.println("Part ID not found!");
            }
            System.out.println("Do you want to add another Car? (yes/no): "); //if no break out loops
            addParts = s.next();
        }

        // Additional notes about the service
        System.out.println("Enter any additional notes for the service: ");
        scanner.nextLine(); // Consume the leftover newline
        String notes = scanner.nextLine();

        // Add service to the list using the updated constructor
        serviceList.add(new Service(serviceNum, serviceDate, clientId, mechanicId, replacedParts,serviceType,serviceCost,notes));

        // Save to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(serviceList);
        oos.close();
    }
    //Read
    public static void viewServices() throws IOException, ClassNotFoundException {
        String file = "AAssignment/Data/Services/Service.txt";
        File newFile = new File(file);
        ObjectInputStream ois = null;

        // Check if file exists and load services
        if (newFile.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            serviceList = (ArrayList<Service>) ois.readObject();
            ois.close();
        }

        // Display service details
        System.out.printf("%-10s %-13s %-10s %-10s %-10s %-10s %-10s\n", "ServiceID", "Date", "ClientID", "MechanicID", "Type", "Cost", "Notes");
        ListIterator<Service> li = serviceList.listIterator();
        while (li.hasNext()) {
            System.out.println(li.next());
            for (Service service : serviceList){
                System.out.println("This is the replace part for Client ID: " + service.getServiceId());
                System.out.printf("%-10s %-10s %-15s %-10s %-10s %-10s %-10s \n", "partId", "partName", "manufacturer", "partNumber", "condition", "warranty", "partCost");
                String replacedParts = service.getReplacedParts().toString();
                replacedParts = replacedParts.substring(1, replacedParts.length() - 1); // Remove the first and last character (the brackets)
                System.out.println(replacedParts);
            }
        }
    }
    // Delete Service by ID
    public static void deleteService() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String file = "Assignment/Data/Services/Service.txt";  // Path to the services file
        File newFile = new File(file);
        boolean found = false;
        ListIterator<Service> li = serviceList.listIterator();
        System.out.println("Enter the Service ID you want to change: ");
        int serviceID = scanner.nextInt();
        // Iterate and remove service if ID matches
        while (li.hasNext()) {
            Service service = li.next();
            if (service.getServiceId() == serviceID) {
                li.remove();
                System.out.println("Assignment.Services.Service has been found and removed.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Assignment.Services.Service ID not found!");
        }

        // Save updated list to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(serviceList);
        oos.close();
    }
    public static void revenueByMechanic() { //This will calculate the total number of Revenue done by 1 mechanic
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        ListIterator<Service> li = serviceList.listIterator();
        System.out.println("Search Mechanic ID for to see revenue made by them: ");
        int mID = scanner.nextInt();
        double total = 0;
        while (li.hasNext()) {
            Service service = (Service) li.next();
            if (service.getMechanicId() == mID) {
                total += service.getServiceCost();
                found = true;
            }
        }
        if (!found) {
            System.out.println(" Mechanic doesn't exist ! ");
        }
        System.out.println("This mechanic has made " + total);
    }
}


