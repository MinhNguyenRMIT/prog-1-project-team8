package Assignment.Users.Manager;
import Assignment.Object.Car.CarList;
import Assignment.Transaction.SalesTransaction;
import Assignment.Transaction.SalesTransactionList;
import Assignment.Users.User;


import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;


public class Manager extends User {
    private final int managerID;
    public Manager(int managerID,String username, String password, String fullName, Date dob, String address, int phoneNumber, String email, String userType, String status){
        super(username, password, fullName, dob, address, phoneNumber, email, userType, status);
        this.managerID = managerID;
    }

    //Car OBJECT
    //CRUD operations
    //Create
    public static void addCar(CarList carList) throws IOException {
        carList.create();

    }
    //Read
    public static void viewCar(CarList carList) throws IOException, ClassNotFoundException {
//        carList.displayListCar();
//        carList.headers();
        carList.view();

    }
    //Update car by ID
    public static void updateCarPriceByID(CarList carList) throws IOException{
        Scanner s = new Scanner(System.in);
        System.out.println("Enter CAR ID: ");
        int ID = s.nextInt();
        System.out.println("Enter CAR PRICE: ");
        double price = s.nextInt();
        carList.updateCarByID(ID, price);

    }
    //Update Car Status
    public static void updateStatus(CarList carList) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter CAR ID: ");
        int ID = s.nextInt();
        System.out.println("Change the Car Status: ");
        String status = s.next();
        carList.updateStatusByID(ID, status);
    }
    //Delete
    public static void deletedCar(CarList carList) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter CAR ID: ");
        int ID = s.nextInt();
        carList.delete(ID);


    } //This will need to first list the car. After words chose the Car ID to delete
    //Return Car by its ID
    public static void getByID(CarList carList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter CAR ID: ");
        int ID = s.nextInt();
        carList.searchCar(ID);
    }
    //List CarSold
    public static void listCarSold(CarList carList){
        carList.listSold();
    }


    //Sales Transaction
    //CRUD OPERATIONS
    //Create
    public static void addTransaction(SalesTransactionList salesTransactionList) throws IOException {
        salesTransactionList.addTransaction();}
    //View Transaction
    public static void viewTransaction(SalesTransactionList salesTransactionList) throws IOException, ClassNotFoundException {
        salesTransactionList.viewTransaction();}
    //Delete byID
    public static void deleteTransactionByID(SalesTransactionList salesTransactionList) throws IOException, ClassNotFoundException {
        System.out.println("Enter the Transaction ID you want to delete: ");
        Scanner s = new Scanner(System.in);
        int transacID = s.nextInt();
        salesTransactionList.deleteTransaction(transacID);
    }
    //Return revenue by Day
    public static void revenueByDay(SalesTransactionList salesTransactionList) throws IOException {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter transaction date. ");
        String date = s.next();
        LocalDate transactionDate = LocalDate.parse(date);
        salesTransactionList.totalAmountPerDay(transactionDate);
    }
    //Return revenue by Week
    public static void revenuePerWeek(SalesTransactionList salesTransactionList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter transaction Year. ");
        int year = s.nextInt();
        System.out.println("Enter the number of week in the Year (1-52). ");
        int week = s.nextInt();
        if (week < 1 && week > 52){
            System.out.println("Number of week is invalid");
        }

        salesTransactionList.totalAmountPerWeek(year, week);
    }
    //Return revenue per Month
    public static void revenuePerMonth(SalesTransactionList salesTransactionList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter transaction Year. ");
        int year = s.nextInt();
        System.out.println("Enter transaction Month. ");
        int monthV = s.nextInt();

        Month month =  null;
        try {
            month = Month.of(monthV);
        } catch (Exception e) {
            System.out.println("Invalid month entered.");
            return;
        }

        salesTransactionList.totalAmountPerMonth(year, month);
    }


    //Lists car sold by Day/Week/Month
    public static void carSoldByDay(SalesTransactionList salesTransactionList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter date. ");
        String date = s.next();
        LocalDate transactionDate = LocalDate.parse(date);
        salesTransactionList.listCarSoldByDay(transactionDate);
    }
    public static void carSoldByWeek(SalesTransactionList salesTransactionList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Year. ");
        int year = s.nextInt();
        System.out.println("Enter the number of week in the Year (1-52). ");
        int week = s.nextInt();
        if (week < 1 && week > 52){
            System.out.println("Number of week is invalid");
        }

        salesTransactionList.listCarSoldByWeek(year, week);
    }
    public static void carSoldByMonth(SalesTransactionList salesTransactionList){
        Scanner s = new Scanner(System.in);
        System.out.println("Enter Year. ");
        int year = s.nextInt();
        System.out.println("Enter Month. ");
        int monthV = s.nextInt();

        Month month =  null;
        try {
            month = Month.of(monthV);
        } catch (Exception e) {
            System.out.println("Invalid month entered.");
            return;
        }
        salesTransactionList.listCarSoldPerMonth(year, month);

    }


    //Return the Revenue made by the Sales Person
    public static void revenueBySales(SalesTransactionList salesTransactionList){
        salesTransactionList.revenueBySalesPerson();
    }

}
