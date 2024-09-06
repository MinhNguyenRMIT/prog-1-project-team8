package Assignment.AutoPart;

import java.util.List;

public interface AutoPartInterface {
    void addPart(AutoPart part);
    void removePart(String partId);
    void updatePart(String partId, AutoPart updatedPart);
    AutoPart getPart(String partId);
    List<AutoPart> getAllParts();
}

