package Assignment.Transaction;
import Assignment.Object.Car.Car;

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

import static Assignment.Object.Car.CarList.carList;

public class SalesTransactionList implements Serializable {
    public static ArrayList<SalesTransaction> transactionList = new ArrayList<SalesTransaction>();
    ListIterator li = null;

    //CRUD for Manager
    //Create
    public static void addTransaction() throws IOException {
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\Data\\Transaction\\transaction.txt";
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
        System.out.println("Enter the Item label Cars/Part. ");
        String items = s.nextLine();
        s.nextLine();
        ArrayList<String> purchasedItems = new ArrayList<>();
        for (int i = 0; i < items.length(); i++){
            String item = s.nextLine();
            purchasedItems.add(item);
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
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\Data\\Transaction\\transaction.txt";
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
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\Data\\Transaction\\transaction.txt";
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
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\Data\\Transaction\\transaction.txt";
        File newFile = new File(file);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile, true));
        oos.writeObject(transactionList);
        oos.close();
    }



    // Method to calculate the final total amount after applying the discount
    public static double totalAmountPerClient() {
        double finalAmount = 0;
        for (SalesTransaction salesTransaction : transactionList) {
            finalAmount = salesTransaction.getTotalAmount() - (salesTransaction.getTotalAmount() / 100);
        }
        return finalAmount;
    }


    // Method to add an item to the list of purchased items and save it to a text file
    public static void addPurchasedItem(String itemID) {
        String filename = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\_purchasedItems.txt"; // Each transaction has its own file
        try {
            // Append the purchased item to the text file
            Files.write(Paths.get(filename), (itemID + System.lineSeparator()).getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            System.out.println("Item " + itemID + " added to " + filename);
        } catch (IOException e) {
            System.err.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }

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
            if (salesTransaction.getTransactionDate().get(ChronoField.ALIGNED_DAY_OF_WEEK_IN_YEAR) == week && (salesTransaction.getTransactionDate().getYear() == year)){
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
    public static void totalAmountPerYear(int year){
        boolean found = false;
        ListIterator<SalesTransaction> li= transactionList.listIterator();
        double total = 0;
        while (li.hasNext()) {
            SalesTransaction salesTransaction = (SalesTransaction) li.next();
            if (salesTransaction.getTransactionDate().getYear() == year){
                total += salesTransaction.getTotalAmount();
                found = true;
            }
        }
        if(!found){
            System.out.println(" Not found! ");
        }
        System.out.println(total);

    }

//    // Corrected method to filter transactions by week
//    public static List<SalesTransaction> filterTransactionsByWeek(List<SalesTransaction> transactions, LocalDate date) {
//        LocalDate startOfWeek = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY)); // Start of the week (Monday)
//        LocalDate endOfWeek = startOfWeek.plusDays(6); // End of the week (Sunday)
//
//        return transactions.stream()
//                .filter(transaction -> !transaction.getTransactionDate().isBefore(startOfWeek) &&
//                        !transaction.getTransactionDate().isAfter(endOfWeek))
//                .collect(Collectors.toList());
//    }

//    // Method to filter transactions by month
//    public static List<SalesTransaction> filterTransactionsByMonth(List<SalesTransaction> transactions, LocalDate date) {
//        int month = date.getMonthValue();
//        int year = date.getYear();
//
//        return transactions.stream()
//                .filter(transaction -> transaction.getTransactionDate().getMonthValue() == month &&
//                        transaction.getTransactionDate().getYear() == year)
//                .collect(Collectors.toList());
//    }


//    public void saveTransactionCSV() throws IOException {
//        File fileSrc = new File("C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\transaction.csv");
//        FileWriter fileWriterSrc = new FileWriter(fileSrc, true);
//        String columns= "TID,DATE,CID,SID,DISCOUNT";
//        fileWriterSrc.write(columns + "\n");
//        for (SalesTransaction salesTransaction : transactionList)
//        {
//            fileWriterSrc.write(salesTransaction.convertTransactionToCSV() + "\n");
//        }
//        fileWriterSrc.close();
//    }

}
