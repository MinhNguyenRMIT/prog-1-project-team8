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
        this.replacedParts = replacedParts;
        this.serviceCost = serviceCost;
        this.notes = notes;
    }
}