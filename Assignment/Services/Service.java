package Assignment.Services;

import Assignment.Part.AutoPart;
import jdk.vm.ci.meta.Local;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Service implements Serializable {
    private int serviceId;        // Unique ID for the service (formatted as s-number)
    private LocalDate serviceDate;      // Date of the service
    private int clientId;         // ID of the client who received the service
    private int mechanicId;       // ID of the mechanic who performed the service
    private String serviceType;      // Type of service performed (e.g., Oil Change, Tire Rotation)
    private List<AutoPart> replacedParts; // List of parts replaced during the service, if any
    private double serviceCost;      // Cost of the service
    private String notes;            // Additional notes or details about the service



    public String toString(){
        return String.format("%-10s %-13s %-10s %-10s %-10s %-25s %-10s %-10s \n",serviceId, serviceDate, clientId, mechanicId, serviceType, replacedParts, serviceCost,notes);
    }
    // Constructor
    public Service(int serviceId, LocalDate serviceDate, int clientId, int mechanicId,
                   String serviceType, List<AutoPart> replacedParts, double serviceCost, String notes) {
        this.serviceId = serviceId;
        this.serviceDate = serviceDate;
        this.clientId = clientId;
        this.mechanicId = mechanicId;
        this.serviceType = serviceType;
        this.replacedParts = replacedParts != null ? replacedParts : new ArrayList<>();
        this.serviceCost = serviceCost;
        this.notes = notes;
    }
    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDate getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(LocalDate serviceDate) {
        this.serviceDate = serviceDate;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(int mechanicId) {
        this.mechanicId = mechanicId;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public List<AutoPart> getReplacedParts() {
        return replacedParts;
    }

    public void setReplacedParts(List<AutoPart> replacedParts) {
        this.replacedParts = replacedParts;
    }

    public double getServiceCost() {
        return serviceCost;
    }

    public void setServiceCost(double serviceCost) {
        this.serviceCost = serviceCost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double calculateTotalCost() {
        double totalCost = this.serviceCost; // Start with the base service cost

        // Add the cost of each replaced part to the total cost
        for (AutoPart part : replacedParts) {
            totalCost += part.getCost();
        }

        return totalCost; // Return the total cost of the service
    }
    public String getServiceSummary() {
        StringBuilder summary = new StringBuilder();

        summary.append("Assignment.Services.Service ID: ").append(serviceId).append("\n")
                .append("Assignment.Services.Service Date: ").append(serviceDate).append("\n")
                .append("Client ID: ").append(clientId).append("\n")
                .append("Mechanic ID: ").append(mechanicId).append("\n")
                .append("Assignment.Services.Service Type: ").append(serviceType).append("\n")
                .append("Assignment.Services.Service Cost: ").append(serviceCost).append("\n");

        // Append replaced parts details if any
        if (replacedParts != null && !replacedParts.isEmpty()) {
            summary.append("Replaced Parts:\n");
            for (AutoPart part : replacedParts) {
                summary.append("  - ").append(part.getPartName())
                        .append(" (ID: ").append(part.getPartId()).append("), ")
                        .append("Cost: ").append(part.getCost()).append("\n");
            }
        } else {
            summary.append("Replaced Parts: None\n");
        }

        // Append additional notes if any
        if (notes != null && !notes.isEmpty()) {
            summary.append("Notes: ").append(notes).append("\n");
        } else {
            summary.append("Notes: None\n");
        }

        return summary.toString();
    }
}
