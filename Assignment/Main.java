package Assignment;


import Assignment.Users.Manager;
import Assignment.Object.Car.CarList;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
        CarList carList = new CarList();
        start(carList);
    }
    public static void start(CarList carList) throws IOException, URISyntaxException, ParseException, ClassNotFoundException {
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
                case 1 -> manager(carList);
                case 2 -> employee();
                case 3 -> client();

            }
        }while (choice !=0);
    }
    public static void manager(CarList carList) throws IOException, ClassNotFoundException {
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
//            System.out.println("7: Calculate Mechanic Revenue");
//            System.out.println("8: List Transaction");
//            System.out.println("9: List Auto part");
//            System.out.println("10: Remove Car Parts");
//            System.out.println("11: Remove Employees");
//            System.out.println("12: Remove Client");
            System.out.println("13. Logout ");
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

                  ;
                }
                case 6 -> {Manager.getByID((carList));

                    ;
                }case 7 -> {Manager.listCarSold(carList);
                   ;
                }
                case 8 -> {
                    ;
                }
                case 9 -> {
                    ;
                }
                case 10 -> {
                    ;

                }case 11 -> {
                    ;
                }
                case 13 -> {
                   ;
                }
            }
        }while (choice !=0);
    }
    public static void employee() {
        int choice = -1;
        Scanner s = new Scanner(System.in);
        do {
            System.out.println("You are log in as an Employee");
            System.out.println("1: ");
            System.out.println("2: ");
            System.out.println("3: ");
            System.out.println("4: ");

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
