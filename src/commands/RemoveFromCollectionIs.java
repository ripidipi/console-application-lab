package commands;

import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

public interface RemoveFromCollectionIs {

    /**
     * Removes study groups greater than the given one from the collection.
     *
     * @param studyGroup The study group to compare with.
     */
    static void remove(StudyGroup studyGroup, boolean compareAsGreater) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        ArrayList<StudyGroup> toRemove = new ArrayList<>();
        for (StudyGroup sG : collection) {
            if(compareAsGreater ? sG.compareTo(studyGroup) > 0 : sG.compareTo(studyGroup) < 0) {
                toRemove.add(sG);
            }
        }
        for (StudyGroup sG : toRemove) {
            collection.remove(sG);
        }
    }

}
