package commands;

import input_output.PrimitiveDataTransform;
import input_output.SavingAnEmergencyStop;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.TreeSet;

/**
 * Interface that defines the method for validating and parsing the ID input for study groups.
 * It ensures the provided ID exists in the collection and can be used for operations such as updating or removing elements.
 */
public interface IsElementWithId {

    /**
     * Validates and parses the ID input.
     * This method checks if the provided ID exists in the collection of study groups. If the ID is valid and exists,
     * it returns the parsed ID. Otherwise, it throws a runtime exception.
     *
     * @param id The ID as a string to validate and parse.
     * @return The parsed ID as an Integer, if it exists in the collection.
     * @throws RuntimeException If the ID is invalid or does not exist in the collection.
     */
    static Integer validateId(String id) throws RuntimeException {
        Integer transformedId = PrimitiveDataTransform.transformToRequiredType("id", Integer.class, true,
                true, false, id, true, null, true);
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        boolean found = false;
        for (StudyGroup studyGroup: collection) {
            if (studyGroup.getId().equals(transformedId)) {
                found = true;
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("No element to update with this id in collection");
        }
        SavingAnEmergencyStop.addStringToFile(transformedId.toString());
        return transformedId;
    }

}
