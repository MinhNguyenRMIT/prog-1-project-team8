package Assignment.Object.Car;

import java.io.*;
import java.util.*;

public class CarList implements Serializable{
    public static ArrayList<Car> carList = new ArrayList<Car>();
    public static ArrayList<Car> carSold = new ArrayList<Car>();
    private static Scanner s;
    ListIterator li = null;

    //Create Car
    public static void create() throws IOException {
        String file = "Assignment/Data/Car/cars.txt";
        File newFile = new File(file);
        Scanner s = new Scanner(System.in);
        int cNum;

        //Validation if CNUM already exist, to prevent duplicate IDs
        while (true){
            System.out.println("Enter the carID: ");
            cNum = s.nextInt();
            boolean exists = false;
            for (Car car : carList){
                if (car.getCNumber() == cNum){
                    exists = true;
                    break;
                }
            }
            if (exists){
                System.out.println("This CNUM already exist, try again. ");
            }else {
                break;
            }

        }
        //Take input from users
        System.out.println("Enter the Model");
        String model = s.next();
        System.out.println("Enter the Year");
        int year = s.nextInt();
        System.out.println("Enter the Mileage");
        double milage = s.nextDouble();
        System.out.println("Enter the Color");
        String color = s.next();
        System.out.println("Car status: Sold or Available");
        String status = s.next();
        System.out.println("Enter the Price");
        double price = s.nextDouble();

        carList.add(new Car(cNum,model,year,milage,color,status,price));
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(carList);
        save();
        oos.close();
    }
    //View Car details
    public static void view() throws IOException, ClassNotFoundException {
        String file = "Assignment/Data/Car/cars.txt";
        File newFile = new File(file);
        ObjectInputStream ois = null;
        if (newFile.isFile()){
            ois = new ObjectInputStream(new FileInputStream(file));
            carList = (ArrayList<Car>) ois.readObject();
            ois.close();

        }
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Milage" , "Color" , "Status", "Price");
        ListIterator<Car> li = carList.listIterator();
        while (li.hasNext())
            System.out.println(li.next());

    }
    //Delete Car by ID
    public static void delete(int carID) throws IOException {
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\Data\\Car\\cars.txt";
        File newFile = new File(file);
        boolean found = false;
        ListIterator<Car> li= carList.listIterator();
        while (li.hasNext()) {
            Car car = (Car) li.next();
            if (car.getCNumber() == carID){
                li.remove();
                System.out.println("Car has been found and remove");
                found = true;
            }
        }
        if (!found){
            System.out.println("ID not found! ");
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(carList);
        save();
    }
    //Search Car by ID
    public static void searchCar(int carID){
        boolean found = false;
        ListIterator<Car> li= carList.listIterator();
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Milage" , "Color" , "Price");
        while (li.hasNext()) {
            Car car = (Car) li.next();
            if (car.getCNumber() == carID){
                System.out.println(car);
                found = true;
            }
        }
        if(!found){
            System.out.println("Car not found! ");
        }
    }
    //Update Car By ID
    public static void updateCarByID(int carID, double newPrice) throws IOException {
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\cars.txt";
        File newFile = new File(file);
        boolean found = false;
        ListIterator<Car> li= carList.listIterator();
        while (li.hasNext()) {
            Car car = (Car) li.next();
            if (car.getCNumber() == carID){
                li.set(new Car(car.getCNumber(), car.getModel(),car.getYear(), car.getMileage(),car.getColor(),car.getStatus(), newPrice));
                System.out.println("Car has been found and is updated");
                found = true;
            }
        }
        if (!found){
            System.out.println("ID not found! ");
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(carList);
        save();
    }
    public static void updateStatusByID(int carID, String newStatus)throws IOException{
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\cars.txt";
        File newFile = new File(file);
        boolean found = false;
        ListIterator<Car> li= carList.listIterator();
        while (li.hasNext()) {
            Car car = (Car) li.next();
            if (car.getCNumber() == carID){
                li.set(new Car(car.getCNumber(), car.getModel(),car.getYear(), car.getMileage(),car.getColor(),newStatus, car.getPrice()));
                System.out.println("Car has been found and status is updated");
                found = true;
            }
        }
        if (!found){
            System.out.println("ID not found! ");
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile));
        oos.writeObject(carList);
        save();
    }
    //List Car sold
    public static void listSold(){
        boolean found = false;
        ListIterator<Car> li= carList.listIterator();
        System.out.printf("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n", "CNUM" , "Model" , "Year" , "Mileage" , "Color", "Status" , "Price");
        while (li.hasNext()) {
            Car car = (Car) li.next();
            if ("sold".equalsIgnoreCase(car.getStatus())){
                System.out.println(car);
                found = true;
            }
        }
        if(!found){
            System.out.println("Car not found! ");
        }
    }




    //Save Cars
    public static void save() throws IOException {
        String file = "Assignment/Data/Car/cars.txt";
        File newFile = new File(file);
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(newFile, true));
        oos.writeObject(carList);
        oos.close();
    }
    public static void headers() throws IOException {
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\cars.txt";
        File newFile = new File(file);
        if(!newFile.exists()){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFile ))){
                writer.write("CNum, Make, Model, Year, Milage, Color, Status, Price, Notes");
                writer.newLine();
            }
        }

    }



    public static void readCSVAsArray(){
        String file = "C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\cars.csv";
        String line;
        String delimiter = ",";

        List<String[]> carList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((line = br.readLine()) != null) {
                String[] values = line.split(delimiter);
                carList.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert List to 2D array
        String[][] array = new String[carList.size()][];
        for (int i = 0; i < carList.size(); i++) {
            array[i] = carList.get(i);
        }

        // Print the array to verify
        for (String[] row : array) {
            for (String value : row) {
                System.out.printf("%-10s",value);
            }
            System.out.println();
        }

    }

    //Its jobs is to update the CSV files after change is made
    ///CSV File purpose is to view

    public void saveToCSV() throws IOException {
        File fileSrc = new File("C:\\Users\\ankha\\OneDrive\\Desktop\\University\\Programming 1\\ASM-Group\\prog-1-project-team8\\Assignment\\cars.csv");
        FileWriter fileWriterSrc = new FileWriter(fileSrc, false);
        String columns= "CNUM,MODEL,YEAR,MILAGE,COLOR,STATUS,PRICE";
        fileWriterSrc.write(columns + "\n");
        for (Car car : carList)
        {
            fileWriterSrc.write(car.convertToStringForCSV() + "\n");
        }
        fileWriterSrc.close();
    }


}
