package commands;

import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.TreeSet;

public interface IsElementWithId {

    /**
     * Validates and parses the ID input.
     *
     * @param id The ID as a string.
     * @return The parsed ID, or null if invalid.
     */
    static Integer validateId(String id) throws RuntimeException {
        Integer transformedId =  PrimitiveDataTransform.transformToRequiredType("id", Integer.class, true,
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
        return transformedId;
    }

}
