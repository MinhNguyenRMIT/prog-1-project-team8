package Assignment.Transaction;

import Assignment.Object.Car.Car;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SalesTransaction implements Serializable{
    private int transactionID;
    private LocalDate transactionDate;
    private int clientID;
    private int salespersonID;
    private ArrayList<Car> purchasedItems;
    private double discount;
    private double totalAmount;
    private String additionalNotes;

    // Constructor
    public SalesTransaction(int transactionID, LocalDate transactionDate, int clientID, int salespersonID, ArrayList<Car> purchasedItems, double discount, double totalAmount
                            ) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.clientID = clientID;
        this.salespersonID = salespersonID;
        this.purchasedItems = purchasedItems;
        this.discount = discount;
        this.totalAmount = totalAmount;
//        this.additionalNotes = additionalNotes;
    }


    public String toString(){
        return String.format("%-10s %-10s %-10s %-10s %-10s %-10s \n",transactionID, transactionDate, clientID, salespersonID, discount, totalAmount);
    }


    // Getter and Setter Methods
    public int getTransactionID() {
        return transactionID;
    }
    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }


    public LocalDate getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }


    public int getClientID() {
        return clientID;
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    public int getSalespersonID() {
        return salespersonID;
    }
    public void setSalespersonID(int salespersonID) {
        this.salespersonID = salespersonID;
    }


    public double getTotalAmount() {
        return totalAmount;
    }


    public ArrayList<Car> getPurchasedItems(){
        return purchasedItems;

    }
}