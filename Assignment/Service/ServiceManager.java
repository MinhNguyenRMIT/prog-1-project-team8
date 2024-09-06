package Assignment.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceManager implements ServiceInterface {
    // In-memory storage for Assignment.Assignment.Service.Service objects
    private final Map<String, Service> serviceMap;

    // Constructor
    public ServiceManager() {
        this.serviceMap = new HashMap<>();
    }

    /**
     * Adds a new Assignment.Assignment.Service.Service to the serviceMap.
     * If a Assignment.Assignment.Service.Service with the same ID already exists, it will not be added.
     *
     * @param service The Assignment.Assignment.Service.Service object to add.
     */
    @Override
    public void addService(Service service) {
        if (serviceMap.containsKey(service.getServiceId())) {
            System.out.println("Assignment.Assignment.Service.Service with ID " + service.getServiceId() + " already exists.");
        } else {
            serviceMap.put(service.getServiceId(), service);
            System.out.println("Assignment.Assignment.Service.Service added successfully.");
        }
    }

    /**
     * Removes a Assignment.Assignment.Service.Service from the serviceMap based on serviceId.
     *
     * @param serviceId The ID of the Assignment.Assignment.Service.Service to remove.
     */
    @Override
    public void removeService(String serviceId) {
        if (serviceMap.containsKey(serviceId)) {
            serviceMap.remove(serviceId);
            System.out.println("Assignment.Assignment.Service.Service removed successfully.");
        } else {
            System.out.println("Assignment.Assignment.Service.Service with ID " + serviceId + " does not exist.");
        }
    }

    /**
     * Updates an existing Assignment.Assignment.Service.Service in the serviceMap.
     *
     * @param serviceId     The ID of the Assignment.Assignment.Service.Service to update.
     * @param updatedService The Assignment.Assignment.Service.Service object containing updated information.
     */
    @Override
    public void updateService(String serviceId, Service updatedService) {
        if (serviceMap.containsKey(serviceId)) {
            serviceMap.put(serviceId, updatedService);
            System.out.println("Assignment.Assignment.Service.Service updated successfully.");
        } else {
            System.out.println("Assignment.Assignment.Service.Service with ID " + serviceId + " does not exist.");
        }
    }

    /**
     * Retrieves a Assignment.Assignment.Service.Service from the serviceMap based on serviceId.
     *
     * @param serviceId The ID of the Assignment.Assignment.Service.Service to retrieve.
     * @return The Assignment.Assignment.Service.Service object if found, else null.
     */
    @Override
    public Service getService(String serviceId) {
        return serviceMap.get(serviceId);
    }

    /**
     * Retrieves all Services from the serviceMap.
     *
     * @return A list of all Assignment.Assignment.Service.Service objects.
     */
    @Override
    public List<Service> getAllServices() {
        return new ArrayList<>(serviceMap.values());
    }
}

