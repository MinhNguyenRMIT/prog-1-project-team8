package Assignment.Users;
import Assignment.Object.Car.CarList;


import java.io.*;
import java.util.Date;
import java.util.Scanner;


public class Manager extends User {
    private final int managerID;
//    private SalesTransaction transaction;
    public Manager(int managerID,String fullName, Date date, String address, int phoneNo, String email, String userType, String status){
      super(fullName, date, address, phoneNo, email, userType, status);
        this.managerID = managerID;
    }


    //CRUD operations

    //Created
    public static void addCar(CarList carList) throws IOException {
//        carList.addedCar();
//        carList.saveToCSV();
        carList.create();

    }
    //CRead
    public static void viewCar(CarList carList) throws IOException, ClassNotFoundException {
//        carList.displayListCar();
//        carList.headers();
        carList.view();

    }
    //Update
    public static void updateCarPriceByID(CarList carList) throws IOException{
        Scanner s = new Scanner(System.in);
        System.out.println("Enter CAR ID: ");
        int ID = s.nextInt();
        System.out.println("Enter CAR PRICE: ");
        double price = s.nextInt();
        carList.updateCarByID(ID, price);

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




//    public void calculateRevenue(){}
    public static void listCarSold(CarList carList){
        carList.listSold();
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
//    public void calculateMechanicRevenue(){}
    public void listTransaction(){
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
