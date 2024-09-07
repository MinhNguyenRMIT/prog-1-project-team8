import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.*;

public class Car {
    private String cNumber;
    private String make;
    private String model;
    private LocalDate year;
    private double mileage;
    private String color;
    private String status;  // Available/Sold
    private double price;
    private String additionalNotes;
    private List<String> serviceHistory;
}
