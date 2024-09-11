package Assignment.Transaction;
import Assignment.Object.Car.Car;
import Assignment.Part.AutoPart;
import Assignment.Services.Service;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.StreamSupport;

import static Assignment.Object.Car.CarList.carList;
import static Assignment.Object.Car.CarList.carSold;

public class SalesTransactionList implements Serializable {
    public static ArrayList<SalesTransaction> transactionList = new ArrayList<SalesTransaction>();
    public static ArrayList<Car> purchasedItems = new ArrayList<>();
    ListIterator li = null;

    //CRUD for Manager
    //Create
    public static void addTransaction() throws IOException {
        String file = "Assignment/Data/SalesTransaction/transaction.txt";
        File newFile = new File(file);
        Scanner s = new Scanner(System.in);
        int tID;
        while (true){
            System.out.println("Enter the tID: ");
            tID = s.nextInt();
            boolean exists = false;
            for (Car car : carList){
                if (car.getCNumber() == tID){
                    exists = true;
                    break;
                }
            }
            if (exists){
                System.out.println("This Transfer ID already exist, try again. ");
            }else {
                break;
            }
        }
        System.out.println("Enter transaction date. ");
        String date = s.next();
        LocalDate transactionDate = LocalDate.parse(date);
        System.out.println("Enter Client ID. ");
        int clientID = s.nextInt();
        System.out.println("Enter SalesPerson ID. ");
        int salesID = s.nextInt();


        System.out.println("Do you want to add Car? (yes/no): ");
        String addCar = s.next();
        while (addCar.equalsIgnoreCase("yes")) {
            Car foundCar = null;
            // Collect all required details for AutoPart
            System.out.println("Enter Car ID: ");
            int cID = s.nextInt();
            for (Car car : carList){
                if (car.getCNumber() == cID){
                    foundCar = car;
                    break;
                }
            }
            // Add part to list using the full constructor
            if (foundCar != null){
                purchasedItems.add(foundCar);
            }else {
                System.out.println("Car ID not found!");
            }
            System.out.println("Do you want to add another Car? (yes/no): "); //if no break out loops
            addCar = s.next();
        }
        System.out.println("Enter discount. ");
        int discount = s.nextInt();
        System.out.println("Enter total amount spend. ");
        double amount = s.nextDouble();
        transactionList.add(new SalesTransaction(tID, transactionDate,clientID,salesID, purchasedItems, discount, amount));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(transactionList);
        saveTransaction();
        oos.close();

    }
    //View Transaction
    public static void viewTransaction() throws IOException, ClassNotFoundException {
        String file = "Assignment/Data/SalesTransaction/transaction.txt";
        File newFile = new File(file);
        ObjectInputStream ois = null;
        if (newFile.isFile()){
            ois = new ObjectInputStream(new FileInputStream(file));
            transactionList = (ArrayList<SalesTransaction>) ois.readObject();
            ois.close();

        }
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s \n", "TID" , "Date" , "CID" , "SID" , "Discount", "Total Amount" );
        ListIterator<SalesTransaction> li = transactionList.listIterator();
        while (li.hasNext())
            System.out.println(li.next());

    }
    //Delete Transaction
    public static void deleteTransaction(int transacID) throws IOException {
        String file = "Assignment/Data/SalesTransaction/transaction.txt";
        File newFile = new File(file);
        boolean found = false;

        ListIterator<SalesTransaction> li= transactionList.listIterator();
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionID() == transacID){
                li.remove();
                System.out.println("Transaction ID has been found and remove");
                found = true;
            }
        }
        if (!found){
            System.out.println("ID not found! ");
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(transactionList);
        saveTransaction();
    }
    //Save Transaction
    public static void saveTransaction() throws IOException {
        String file = "Assignment/Data/SalesTransaction/transaction.txt";
        File newFile = new File(file);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile, true));
        oos.writeObject(transactionList);
        oos.close();
    }


    //Calculate amount made by Day/Week/Month
    public static void totalAmountPerDay(LocalDate day) throws IOException {
        boolean found = false;
        ListIterator<SalesTransaction> li= transactionList.listIterator();
        double total = 0;
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionDate().equals(day)){
                total += salesTransaction.getTotalAmount();
                found = true;
            }
        }
        if(!found){
            System.out.println(" Not found! ");
        }
        System.out.println(total);
    }
    public static void totalAmountPerWeek(int year, int week){
        boolean found = false;
        ListIterator<SalesTransaction> li= transactionList.listIterator();
        double total = 0;
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == week && salesTransaction.getTransactionDate().getYear() == year){;
                total += salesTransaction.getTotalAmount();
                found = true;
            }
        }
        if(!found){
            System.out.println(" Not found! ");
        }
        System.out.println(total);
    }
    public static void totalAmountPerMonth(int year, Month month){
        boolean found = false;
        ListIterator<SalesTransaction> li= transactionList.listIterator();
        double total = 0;
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionDate().getMonth() == month && (salesTransaction.getTransactionDate().getYear() == year)){
                total += salesTransaction.getTotalAmount();
                found = true;
            }
        }
        if(!found){
            System.out.println(" Not found! ");
        }
        System.out.println(total);
    }

    //List CarSold by Day/Week/Month
    public static void listCarSoldByDay(LocalDate day){
        boolean found = false;
        ListIterator<SalesTransaction> li = transactionList.listIterator();
        for (SalesTransaction salesTransaction : transactionList){
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Milage" , "Color" , "Status", "Price");
            String soldCar = salesTransaction.getPurchasedItems().toString();
            soldCar = soldCar.substring(1, soldCar.length() - 1); // Remove the square brackets
            System.out.println(soldCar);
        }
    }
    public static void listCarSoldByWeek(int year, int week){
        ListIterator<SalesTransaction> li= transactionList.listIterator();
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Milage" , "Color" , "Status", "Price");
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionDate().get(ChronoField.ALIGNED_WEEK_OF_YEAR) == week && salesTransaction.getTransactionDate().getYear() == year){;
                String soldCar = salesTransaction.getPurchasedItems().toString();
                soldCar = soldCar.substring(1, soldCar.length() - 1); // Remove the square brackets
                System.out.println(soldCar);
            }
        }
    }
    public static void listCarSoldPerMonth(int year, Month month){
        for (SalesTransaction salesTransaction : transactionList){
            System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Milage" , "Color" , "Status", "Price");
            if (salesTransaction.getTransactionDate().getMonth() == month && (salesTransaction.getTransactionDate().getYear() == year)){
                String soldCar = salesTransaction.getPurchasedItems().toString();
                soldCar = soldCar.substring(1, soldCar.length() - 1); // Remove the square brackets
                System.out.println(soldCar);
            }
        }
    }


    //Return sales person by ID and calculate the amount of money they made
    public static void revenueBySalesPerson() { //This will calculate the total number of Revenue done by 1 mechanic
        Scanner scanner = new Scanner(System.in);
        boolean found = false;
        ListIterator<SalesTransaction> li = transactionList.listIterator();
        System.out.println("Search Sales ID ID for to see revenue made by them: ");
        int sID = scanner.nextInt();
        double total = 0;
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getSalespersonID() == sID) {
                total += salesTransaction.getTotalAmount();
                found = true;
            }
        }
        if (!found) {
            System.out.println(" Sales person doesn't exist ! ");
        }
        System.out.println("This Sales person has made " + total);
    }


}
