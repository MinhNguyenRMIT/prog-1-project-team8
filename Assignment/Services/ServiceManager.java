package Assignment.Services;

import Assignment.Part.AutoPart;
import Assignment.Users.Client;
import jdk.vm.ci.meta.Local;

import java.time.LocalDate;
import java.util.*;
import java.io.*;


public class ServiceManager implements Serializable {
    public static ArrayList<Service> serviceList = new ArrayList<>();
    private static Scanner scanner;
    ListIterator<Service> li = null;

    //CRUD
    // Create Service
    public static void createService() throws IOException {
        String file = "Assignment/Data/Service/Service.txt";  // Path to the services file
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
        System.out.println("Enter the Cost: ");
        double serviceCost = scanner.nextDouble();

        // Input for replaced parts (if any)
        List<AutoPart> replacedParts = new ArrayList<>();
        System.out.println("Do you want to add replaced parts? (yes/no): ");
        String addParts = scanner.next();
        while (addParts.equalsIgnoreCase("yes")) {
            // Collect all required details for Assignment.Part.AutoPart
            System.out.println("Enter Part ID: ");
            String partId = scanner.next();
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
            String notes = scanner.next();

            // Add part to list using the full constructor
            replacedParts.add(new AutoPart(partId, partName, manufacturer, partNumber, condition, warranty, partCost, notes));

            System.out.println("Do you want to add another part? (yes/no): ");
            addParts = scanner.next();
        }

        // Additional notes about the service
        System.out.println("Enter any additional notes for the service: ");
        scanner.nextLine(); // Consume the leftover newline
        String notes = scanner.nextLine();

        // Add service to the list using the updated constructor
        serviceList.add(new Service(serviceNum, serviceDate, clientId, mechanicId, serviceType, replacedParts, serviceCost, notes));

        // Save to file
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(serviceList);
        oos.close();
    }
    //Read
    public static void viewServices() throws IOException, ClassNotFoundException {
        String file = "Assignment/Data/Service/Service.txt";
        File newFile = new File(file);
        ObjectInputStream ois = null;

        // Check if file exists and load services
        if (newFile.isFile()) {
            ois = new ObjectInputStream(new FileInputStream(file));
            serviceList = (ArrayList<Service>) ois.readObject();
            ois.close();
        }

        // Display service details
        System.out.printf("%-10s %-13s %-10s %-10s %-10s %-25s %-10s %-10s \n", "ServiceID", "Date", "ClientID", "MechanicID", "Type","Replace parts", "Cost", "Notes");
        ListIterator<Service> li = serviceList.listIterator();
        while (li.hasNext()) {
            System.out.println(li.next());
        }
    }
    // Delete Service by ID
    public static void deleteService() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String file = "Assignment/Data/Service/Service.txt";  // Path to the services file
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
    //Search By ID
    public static void searchService(int serviceID) {
        boolean found = false;
        ListIterator<Service> li = serviceList.listIterator();

        // Display header
        System.out.printf("%-10s %-15s %-10s %-10s %-15s %-10s \n", "ServiceID", "Assignment.Services.Service Date", "ClientID", "MechanicID", "Assignment.Services.Service Type", "Cost");

        // Search and display service if found
        while (li.hasNext()) {
            Service service = li.next();
            if (service.getServiceId() == serviceID) {
                System.out.println(service);
                found = true;
            }
        }

        // If not found, notify user
        if (!found) {
            System.out.println("Assignment.Services.Service not found!");
        }
    }
}

