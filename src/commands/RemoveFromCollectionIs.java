package commands;

import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * Interface that defines the logic for removing study groups from a collection.
 * This interface provides a method to remove study groups based on a comparison of their values
 * with a given study group, either greater or lower than the specified group.
 */
public interface RemoveFromCollectionIs {

    /**
     * Removes study groups from the collection that are greater or lower than the given one, based on the comparison logic.
     *
     * @param studyGroup The study group to compare with.
     * @param compareAsGreater If true, removes all study groups greater than the specified one;
     *                         if false, removes all study groups lower than the specified one.
     */
    static void remove(StudyGroup studyGroup, boolean compareAsGreater) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        ArrayList<StudyGroup> toRemove = new ArrayList<>();
        for (StudyGroup sG : collection) {
            if (compareAsGreater ? sG.compareTo(studyGroup) > 0 : sG.compareTo(studyGroup) < 0) {
                toRemove.add(sG);
            }
        }
        for (StudyGroup sG : toRemove) {
            collection.remove(sG);
        }
    }

}
