import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceManager implements ServiceInterface {
    // In-memory storage for Service objects
    private final Map<String, Service> serviceMap;

    // Constructor
    public ServiceManager() {
        this.serviceMap = new HashMap<>();
    }

    /**
     * Adds a new Service to the serviceMap.
     * If a Service with the same ID already exists, it will not be added.
     *
     * @param service The Service object to add.
     */
    @Override
    public void addService(Service service) {
        if (serviceMap.containsKey(service.getServiceId())) {
            System.out.println("Service with ID " + service.getServiceId() + " already exists.");
        } else {
            serviceMap.put(service.getServiceId(), service);
            System.out.println("Service added successfully.");
        }
    }

    /**
     * Removes a Service from the serviceMap based on serviceId.
     *
     * @param serviceId The ID of the Service to remove.
     */
    @Override
    public void removeService(String serviceId) {
        if (serviceMap.containsKey(serviceId)) {
            serviceMap.remove(serviceId);
            System.out.println("Service removed successfully.");
        } else {
            System.out.println("Service with ID " + serviceId + " does not exist.");
        }
    }

    /**
     * Updates an existing Service in the serviceMap.
     *
     * @param serviceId     The ID of the Service to update.
     * @param updatedService The Service object containing updated information.
     */
    @Override
    public void updateService(String serviceId, Service updatedService) {
        if (serviceMap.containsKey(serviceId)) {
            serviceMap.put(serviceId, updatedService);
            System.out.println("Service updated successfully.");
        } else {
            System.out.println("Service with ID " + serviceId + " does not exist.");
        }
    }

    /**
     * Retrieves a Service from the serviceMap based on serviceId.
     *
     * @param serviceId The ID of the Service to retrieve.
     * @return The Service object if found, else null.
     */
    @Override
    public Service getService(String serviceId) {
        return serviceMap.get(serviceId);
    }

    /**
     * Retrieves all Services from the serviceMap.
     *
     * @return A list of all Service objects.
     */
    @Override
    public List<Service> getAllServices() {
        return new ArrayList<>(serviceMap.values());
    }
}

