package commands;

import inputOutput.BasicDataTypesInput;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.TreeSet;

public class RemoveGreater {

    public static void removeGreater() {
        Integer id = BasicDataTypesInput.Input("id", Integer.class);
        StudyGroup studyGroup = StudyGroup.Input(id.toString());
        try {
            TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
            ArrayList<StudyGroup> toRemove = new ArrayList<>();
            for (StudyGroup sG : collection) {
                if(sG.compareTo(studyGroup) > 0) {
                    toRemove.add(sG);
                }
            }
            for (StudyGroup sG : toRemove) {
                collection.remove(sG);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
