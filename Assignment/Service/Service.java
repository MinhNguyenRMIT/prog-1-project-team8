package Assignment.Service;

import Assignment.AutoPart.AutoPart;

import java.util.ArrayList;
import java.util.List;

public class Service{
    private String serviceId;        // Unique ID for the service (formatted as s-number)
    private String serviceDate;      // Date of the service
    private String clientId;         // ID of the client who received the service
    private String mechanicId;       // ID of the mechanic who performed the service
    private String serviceType;      // Type of service performed (e.g., Oil Change, Tire Rotation)
    private List<AutoPart> replacedParts; // List of parts replaced during the service, if any
    private double serviceCost;      // Cost of the service
    private String notes;            // Additional notes or details about the service

    // Constructor
    public Service(String serviceId, String serviceDate, String clientId, String mechanicId,
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
    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(String serviceDate) {
        this.serviceDate = serviceDate;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getMechanicId() {
        return mechanicId;
    }

    public void setMechanicId(String mechanicId) {
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

        summary.append("Assignment.Assignment.Service.Service ID: ").append(serviceId).append("\n")
                .append("Assignment.Assignment.Service.Service Date: ").append(serviceDate).append("\n")
                .append("Client ID: ").append(clientId).append("\n")
                .append("Mechanic ID: ").append(mechanicId).append("\n")
                .append("Assignment.Assignment.Service.Service Type: ").append(serviceType).append("\n")
                .append("Assignment.Assignment.Service.Service Cost: ").append(serviceCost).append("\n");

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
