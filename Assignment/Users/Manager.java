package Assignment.Users;
import Assignment.Object.Car.CarList;
import Assignment.Transaction.SalesTransaction;
import Assignment.Transaction.SalesTransactionList;


import java.io.*;
import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.Scanner;


public class Manager extends User {
    private final int managerID;
//    private Assignment.Transaction.SalesTransaction transaction;
    public Manager(int managerID,String fullName, Date date, String address, int phoneNo, String email, String userType, String status){
      super(fullName, date, address, phoneNo, email, userType, status);
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
    public static void addTransaction(SalesTransactionList salesTransactionList) throws IOException {
        salesTransactionList.addTransaction();
    }
    //View Transaction
    public static void viewTransaction(SalesTransactionList salesTransactionList) throws IOException, ClassNotFoundException {
        salesTransactionList.viewTransaction();
    }


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

        salesTransactionList.totalAmountPerMonth(year, Month.of(week));
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


    public void listServices(){
        String file = ""; //Replace this wil the folder
        BufferedReader reader = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine())!= null){
                String[] row = line.split(",");
                for (String index: row){
                    System.out.printf("%-10s",index);
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void calculateCarSoldMonth(){}
    /*To find the amount of CarSold. Look at the Assignment.Transaction.SalesTransaction, if purchased items is Car. Then write the code where
    * it will return salesTransaction that listOfItem is car. Then add in parameter of Month and Year. Next just get the total sum
    * amount per Month. Which then will give me CarSoldPerMonth. */


    public void listAutoPart(){
        String file = ""; //Replace this wil the folder
        BufferedReader reader = null;
        String line = "";
        try{
            reader = new BufferedReader(new FileReader(file));
            while((line = reader.readLine())!= null){
                String[] row = line.split(",");
                for (String index: row){
                    System.out.printf("%-10s",index);
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
//    public void removeCarParts(){}
//    public void removeEmployees(){}
//    public void removeClients(){}


}
