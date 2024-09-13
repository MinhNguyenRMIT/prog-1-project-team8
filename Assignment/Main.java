package Assignment;

import Assignment.Part.PartManager;
import Assignment.Services.ServiceManager;
import Assignment.Users.Client.Client;
import Assignment.Users.Employee.Employee;
import Assignment.Users.Manager.Manager;
import Assignment.Users.User;
import Assignment.Object.Car.CarList;
import Assignment.Transaction.SalesTransaction;
import Assignment.Transaction.SalesTransactionList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static Assignment.Users.Employee.Employee.transactions;

public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        CarList carList = new CarList();
        SalesTransactionList salesTransactionList = new SalesTransactionList();
        ServiceManager serviceManager = new ServiceManager();
        PartManager partManager = new PartManager();

        List<User> userList = User.loadAllUsers();

        start(carList, salesTransactionList, serviceManager, partManager, userList);
    }

    public static void start(CarList carList, SalesTransactionList salesTransactionList, ServiceManager serviceManager, PartManager partManager, List<User> userList) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        Scanner s = new Scanner(System.in);

        System.out.println("---------------------------------------------------");
        System.out.println(" WELCOME TO THE AUTO136 SERVICE MANAGEMENT SYSTEM! ");
        System.out.println("---------------------------------------------------");

        User loggedInUser = null;
        do {
            System.out.println("--------------");
            System.out.println("|Please Login|");
            System.out.println("--------------");
            loggedInUser = User.LOGIN(userList);

            if (loggedInUser != null) {
                switch (loggedInUser.getUserType()) {
                    case "Manager" -> manager((Manager) loggedInUser, carList, salesTransactionList, serviceManager, partManager);
                    case "Employee" -> employee((Employee) loggedInUser);
                    case "Client" -> client((Client) loggedInUser);
                    default -> System.out.println("Unknown user type. Returning to home screen.");
                }
            } else {
                System.out.println("Login failed. Please try again.");
            }

        } while (true);  // Keep running until the program is manually exite
    }

    public static void manager(Manager manager, CarList carList, SalesTransactionList salesTransactionList, ServiceManager serviceManager, PartManager partManager) throws IOException, ClassNotFoundException {
        int choice;
        Scanner s = new Scanner(System.in);
        do {
            manager.exportLogHistoryToTXT();

            System.out.println("You are logged in as a Manager!");
            System.out.println("1: ADD Cars");
            System.out.println("2: VIEW All Cars");
            System.out.println("3: UPDATE Car Price");
            System.out.println("4: UPDATE Car Status");
            System.out.println("5: DELETE Cars");
            System.out.println("6: VIEW Car by ID");
            System.out.println("7: VIEW Car Sold");
            System.out.println("8: ADD Transaction");
            System.out.println("9: VIEW Transaction");
            System.out.println("10: DELETE Transaction");
            System.out.println("11: VIEW Revenue by Day");
            System.out.println("12: VIEW Revenue by Week");
            System.out.println("13: VIEW Revenue by Month");
            System.out.println("14: VIEW revenue by SalesPerson");
            System.out.println("15: VIEW Car Sold By Day");
            System.out.println("16: VIEW Car Sold By Week");
            System.out.println("17: VIEW Car Sold By Month");
            System.out.println("18: ADD Services");
            System.out.println("19: VIEW Services");
            System.out.println("20: DELETE Service");
            System.out.println("21: VIEW revenue by mechanic");
            System.out.println("22: ADD Part");
            System.out.println("23: VIEW Part");
            System.out.println("24: DELETE Part");
            System.out.println("25: LOGOUT");
            choice = s.nextInt();
            switch (choice) {
                case 1 -> {Manager.addCar(carList);}
                case 2 -> {Manager.viewCar(carList);}
                case 3 -> {Manager.updateCarPriceByID(carList);}
                case 4 -> {Manager.updateStatus(carList);}
                case 5 -> {Manager.deletedCar(carList);}
                case 6 -> {Manager.getByID((carList));}
                case 7 -> {Manager.listCarSold(carList);}
                case 8 -> {Manager.addTransaction(salesTransactionList);}
                case 9 -> {Manager.viewTransaction(salesTransactionList);}
                case 10 -> {Manager.deleteTransactionByID(salesTransactionList);}
                case 11 -> {Manager.revenueByDay(salesTransactionList);}
                case 12 -> {Manager.revenuePerWeek(salesTransactionList);}
                case 13 -> {Manager.revenuePerMonth(salesTransactionList);}
                case 14 -> {Manager.revenueBySales(salesTransactionList);}
                case 15 -> {Manager.carSoldByDay(salesTransactionList);}
                case 16 -> {Manager.carSoldByWeek(salesTransactionList);}
                case 17 -> {Manager.carSoldByMonth(salesTransactionList);}
                case 18 -> {ServiceManager.createService();}
                case 19 -> {ServiceManager.viewServices();}
                case 20 -> {ServiceManager.deleteService();}
                case 21 -> {ServiceManager.revenueByMechanic();}
                case 22 -> {PartManager.createAutoPart();}
                case 23 -> {PartManager.viewAutoParts();}
                case 24 -> {PartManager.deleteAutoPart();}
                case 25 -> {manager.LOGOUT();}
            }
        } while (choice != 25);
        System.out.println("Manager " + manager.getUsername() + " logged out at " + new java.util.Date());
    }

    public static void employee(Employee employee) {
        int choice;
        Scanner s = new Scanner(System.in);
        List<SalesTransaction> transactionList = employee.readTransactionsFromTXT();
        do {
            employee.exportLogHistoryToTXT();

            System.out.println("You are logged in as: " + employee.getFullName());
            String position = employee.getPosition();

            // Debug: Print out the position to confirm
            System.out.println("Employee Position: " + position);

            if (position.equalsIgnoreCase("Sales")) {
                System.out.println("1: Calculate revenue in day");
                System.out.println("2: Calculate revenue in week");
                System.out.println("3: Calculate revenue in month");
            } else if (position.equalsIgnoreCase("Mechanic")) {
                System.out.println("1: List the number cars models released by year");
            }

            System.out.println("4: List the number of services in days");
            System.out.println("5: List the number of services in weeks");
            System.out.println("6: List the number of services in months");
            System.out.println("7: LOGOUT");

            choice = s.nextInt();

            if (position.equalsIgnoreCase("Sales")) {
                switch (choice) {
                    case 1 -> Employee.calculateRevenue(transactionList, "day");
                    case 2 -> Employee.calculateRevenue(transactionList, "week");
                    case 3 -> Employee.calculateRevenue(transactionList, "month");
                }
            } else if (position.equalsIgnoreCase("Mechanic")) {
                switch (choice) {
                    case 1 -> Employee.listCars();
                }
            }

            switch (choice) {
                case 4 -> Employee.listServices("day");
                case 5 -> Employee.listServices("week");
                case 6 -> Employee.listServices("month");
                case 7 -> employee.LOGOUT();
            }

        } while (choice != 7);
        System.out.println("Employee " + employee.getUsername() + " logged out at " + new java.util.Date());
    }

    public static void client(Client client) {
        int choice;
        Scanner s = new Scanner(System.in);
        do {
            client.exportLogHistoryToTXT();

            System.out.println("You are logged in as a Client");
            System.out.println("1: View client info");
            System.out.println("2: View Personal Log History");
            System.out.println("3: View my transactions");
            System.out.println("3: LOGOUT");
            choice = s.nextInt();
            switch (choice) {
                case 1 -> client.getClientInfo();
                case 2 -> client.reviewLogHistory();
                case 3 -> client.LOGOUT();
            }
        } while (choice != 3);
        System.out.println("Client " + client.getUsername() + " logged out at " + new java.util.Date());
    }
}
