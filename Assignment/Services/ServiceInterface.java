package Assignment.Services;

import java.util.List;

public interface ServiceInterface {
    void addService(Service service);
    void removeService(String serviceId);
    void updateService(String serviceId, Service updatedService);
    Service getService(String serviceId);
    List<Service> getAllServices();
}
