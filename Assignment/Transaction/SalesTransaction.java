package Assignment.Transaction;

import Assignment.Services.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SalesTransaction implements Serializable{
    private int transactionID;
    private LocalDate transactionDate;
    private int clientID;
    private int salespersonID;
    private ArrayList<String> purchasedItems;
    private double discount;
    private double totalAmount;
    private String additionalNotes;

    // Constructor
    public SalesTransaction(int transactionID, LocalDate transactionDate,int clientID, int salespersonID,ArrayList<String> purchasedItems, double discount, double totalAmount
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



    public ArrayList<String> getCarSold(){
        ArrayList<String> carSold = new ArrayList<>();
        for (String items : purchasedItems){
            carSold.add(items);
        }
        return carSold;
    }


}