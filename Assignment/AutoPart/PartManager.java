package Assignment.AutoPart;

import java.util.HashMap;
import java.util.Map;

public class PartManager {
    private final Map<String, AutoPart> partMap;

    public PartManager() {
        this.partMap = new HashMap<>();
    }

    // 1. Create or Add an Assignment.Assignment.AutoPart.AutoPart
    public void addPart(AutoPart part) {
        if (!partMap.containsKey(part.getPartId())) {
            partMap.put(part.getPartId(), part);
            System.out.println("Part added successfully.");
        } else {
            System.out.println("Part with ID " + part.getPartId() + " already exists.");
        }
    }

    // 2. Retrieve or Get an Assignment.Assignment.AutoPart.AutoPart
    public AutoPart getPart(String partId) {
        AutoPart part = partMap.get(partId);
        if (part != null) {
            return part;
        } else {
            System.out.println("Part with ID " + partId + " not found.");
            return null;
        }
    }

    // 3. Update an Assignment.Assignment.AutoPart.AutoPart
    public void updatePart(String partId, AutoPart updatedPart) {
        if (partMap.containsKey(partId)) {
            partMap.put(partId, updatedPart);
            System.out.println("Part updated successfully.");
        } else {
            System.out.println("Part with ID " + partId + " not found.");
        }
    }

    // 4. Delete an Assignment.Assignment.AutoPart.AutoPart (soft delete by removing from the list)
    public void deletePart(String partId) {
        if (partMap.containsKey(partId)) {
            partMap.remove(partId);
            System.out.println("Part deleted successfully.");
        } else {
            System.out.println("Part with ID " + partId + " not found.");
        }
    }

    // Optionally, you can also add a method to list all parts
    public void listAllParts() {
        if (!partMap.isEmpty()) {
            for (AutoPart part : partMap.values()) {
                System.out.println(part.getPartId() + " - " + part.getPartName());
            }
        } else {
            System.out.println("No parts available.");
        }
    }
}
