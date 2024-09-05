package Assignment.Object.Car;

import java.io.Serializable;

public class Car implements Serializable {
    private int cNumber;
    private String model;
    private int year;
    private double milage;
    private String color;
    private String status;
    private double price;

    public Car(int cNumber, String model, int year, double milage, String color, String status, double price){
        this.cNumber = cNumber;
        this.model = model;
        this.year = year;
        this.milage = milage;
        this.color = color;
        this.status = status ;
        this.price = price;
    }

    public String convertToString(){
        return getCNumber()+getModel()+getYear()+getMileage()+getColor()+getStatus()+getPrice();
    }
    public String convertToStringForCSV(){
        return getCNumber()+","+getModel()+","+getYear()+","+getMileage()+","+getColor()+","+getStatus()+","+getPrice();
    }


    @Override
    public String toString(){
//        return  cNumber + " " + model + " " + year + " "+ milage + " "+ color + " " + price + " " + notes;
        return String.format("%-10s %-10s %-10s %-10s %-10s %-10s %-10s \n ",cNumber, model, year, milage, color,status, price) ;
    }
    // Getter for cNumber
    public int getCNumber() {
        return cNumber;
    }

    // Setter for cNumber
    public void setCNumber(int cNumber) {
        this.cNumber = cNumber;
    }

    // Getter for model
    public String getModel() {
        return model;
    }

    // Setter for model
    public void setModel(String model) {
        this.model = model;
    }

    // Getter for year
    public int getYear() {
        return year;
    }

    // Setter for year
    public void setYear(int year) {
        this.year = year;
    }

    // Getter for mileage
    public double getMileage() {
        return milage;
    }

    // Setter for mileage
    public void setMileage(double mileage) {
        this.milage = mileage;
    }

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Getter for Status
    public String getStatus() {
        return status;
    }

    // Setter for Status
    public void setStatus(String status) {
        this.status = status;
    }


    // Getter for price
    public double getPrice() {
        return price;
    }

    // Setter for price
    public void setPrice(double price) {
        this.price = price;
    }

}
