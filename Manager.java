import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

class Manager extends User {
    private final int managerID;
//    private SalesTransaction transaction;
    public Manager(int managerID,String fullName, Date date, String address, int phoneNo, String email, String userType, String status){
      super(fullName, date, address, phoneNo, email, userType, status);
        this.managerID = managerID;
    }

    public int getManagerID(){
        return managerID;
    }
    //CRUD operations
    public static void addCar(CarList carList){
        carList.addedCar();
    }
    public static void viewCar(CarList carList) throws IOException {
        carList.displayListCar();
    }

    public static void getByID(CarList carList){
        Scanner s = new Scanner(System.in);
        System.out.println("ID: ");
        String cnum = s.next();
        carList.returnByID(cnum);
    }

    public static void deletedCar(CarList carList){
    } //This will need to first list the car. After words chose the Car ID to delete
    //Statistic operations

    public static void updateCarPrice(CarList carList) throws IOException{
    }
//    public void calculateRevenue(){}
    public void listCarSold(){
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
