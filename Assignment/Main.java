package Assignment;


import Assignment.Users.Manager;
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
        start(carList, salesTransactionList);
    }
    public static void start(CarList carList, SalesTransactionList salesTransactionList) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
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
                case 1 -> manager(carList, salesTransactionList);
                case 2 -> employee();
                case 3 -> client();

            }
        }while (choice !=0);
    }
    public static void manager(CarList carList, SalesTransactionList salesTransactionList) throws IOException, ClassNotFoundException {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        do{
            System.out.println("You are log in Manager!");
            System.out.println("1: Add Cars");
            System.out.println("2: View All Cars");
            System.out.println("3: Update Car Price");
            System.out.println("4: Update Car Status");
            System.out.println("5: Delete Cars");
            System.out.println("6: Get By ID");
            System.out.println("7: List Car Sold");
            System.out.println("8: Add transaction");
            System.out.println("9: List Transaction");
            System.out.println("10: Delete Transaction");
            System.out.println("11: Revenue by Week");
            System.out.println("12: Revenue by Week");
            System.out.println("13: Revenue by Month");
//            System.out.println("9: List Auto part");
//            System.out.println("10: Remove Car Parts");
//            System.out.println("11: Remove Employees");
//            System.out.println("12: Remove Client");
//            System.out.println("13. Logout ");
            choice = s.nextInt();
            switch (choice){
                case 1 -> {
                    Manager.addCar(carList);
                    carList.saveToCSV();
                }
                case 2 -> {
                    Manager.viewCar(carList);
                }case 3 -> {
                    Manager.updateCarPriceByID(carList);
                    carList.saveToCSV();
                }
                case 4 -> {
                    Manager.updateStatus(carList);
                    carList.saveToCSV();

                }
                case 5 -> {Manager.deletedCar(carList);
                    carList.saveToCSV();

                }
                case 6 -> {Manager.getByID((carList));

                }case 7 -> {Manager.listCarSold(carList);
                   ;
                }
                case 8 -> {Manager.addTransaction(salesTransactionList);

                }
                case 9 -> {Manager.viewTransaction(salesTransactionList);
//                    salesTransactionList.saveTransactionCSV();

                }
                case 10 -> {
                    Manager.deleteTransactionByID(salesTransactionList);


                }case 11 -> {
                    Manager.revenueByDay(salesTransactionList);

                }case 12 -> {
                    Manager.revenuePerWeek(salesTransactionList);

                }
                case 13 -> {
                    Manager.revenuePerMonth(salesTransactionList);

                }
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
