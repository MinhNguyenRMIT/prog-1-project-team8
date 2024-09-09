package Assignment;

import Assignment.Part.PartManager;
import Assignment.Services.ServiceManager;
import Assignment.Users.Manager.Manager;
import Assignment.Object.Car.CarList;
import Assignment.Transaction.SalesTransactionList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        CarList carList = new CarList();
        SalesTransactionList salesTransactionList = new SalesTransactionList();
        ServiceManager serviceManager = new ServiceManager();
        PartManager partManager = new PartManager();
        start(carList, salesTransactionList, serviceManager, partManager);
    }
    public static void start(CarList carList, SalesTransactionList salesTransactionList, ServiceManager serviceManager, PartManager partManager) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        System.out.println("WELCOME TO THE HOMEPAGE");
        System.out.println("------------------------------");
        System.out.println("|Who do you want to login as?|");
        System.out.println("------------------------------");
        do{
            System.out.println("1. MANAGER");
            System.out.println("2. EMPLOYEE");
            System.out.println("3. CLIENT");
            choice = s.nextInt();
            switch (choice){
                case 1 -> manager(carList, salesTransactionList, serviceManager, partManager);
                case 2 -> employee();
                case 3 -> client();
            }
        }while (choice !=0);
    }
    public static void manager(CarList carList, SalesTransactionList salesTransactionList, ServiceManager serviceManager, PartManager partManager) throws IOException, ClassNotFoundException {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("You are log in Manager!");
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
//            System.out.println("15: ADD Services");
//            System.out.println("16: VIEW Services");
//            System.out.println("17: DELETE Service");
//            System.out.println("18: VIEW revenue by mechanic");
//            System.out.println("19: ADD Part");
//            System.out.println("20: VIEW Part");
//            System.out.println("21: DELETE Part");
            choice = s.nextInt();
            switch (choice){
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
//                case 15 -> {ServiceManager.createService();}
//                case 16 -> {ServiceManager.viewServices();}
//                case 17 -> {ServiceManager.deleteService();}
//                case 18 -> {ServiceManager.revenueByMechanic();}
//                case 19 -> {PartManager.createAutoPart();}
//                case 20-> {PartManager.viewAutoParts();}
//                case 21 -> {PartManager.deleteAutoPart();}
            }
        }while (choice !=0);
    }
    public static void employee() {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("You are log in as an Employee");
            System.out.println("1: Calculate revenue in day ");
            System.out.println("2: Calculate revenue in week ");
            System.out.println("3: Calculate revenue in month ");
            System.out.println("4: List the number of services in days");
            System.out.println("5: List the number of services in weeks ");
            System.out.println("6: List the number of services in month ");
            System.out.println("7: List the number of cars in days ");
            System.out.println("8: List the number of cars in weeks ");
            System.out.println("9: List the number of cars in month ");
            System.out.println("10: LOGOUT");

            choice = s.nextInt();
            switch (choice) {

            }

        } while (choice != 0);
    }
    public static void client(){
            int choice = -1;
            Scanner s = new Scanner(System.in);
            do{
                System.out.println("You are log in as a Client");
                System.out.println("1: ");
                System.out.println("2: ");
                System.out.println("3: ");
                System.out.println("4: ");

                choice = s.nextInt();
                switch (choice){
                }

            }while (choice !=0);
        }


}
