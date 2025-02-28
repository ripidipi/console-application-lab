package commands;

import inputOutput.BasicDataTypesInput;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.TreeSet;

public class AddIfMax {

    public static void addIdMax() {
        Integer id = BasicDataTypesInput.Input("id", Integer.class);
        StudyGroup studyGroup = StudyGroup.Input(id.toString());
        if (ifMax(studyGroup)) {
            Collection.getInstance().addElement(studyGroup);
        }
    }

    private static boolean ifMax(StudyGroup studyGroup) {
        try {
            TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
            StudyGroup maxStudyGroup = collection.last();
            return maxStudyGroup.compareTo(studyGroup) < 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
