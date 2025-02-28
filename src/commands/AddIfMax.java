package commands;

import exeptions.InsufficientNumberOfArguments;
import inputOutput.PrimitiveDataTransform;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.TreeSet;

public class AddIfMax {

    public static void addIfMax() {
        Integer id = PrimitiveDataTransform.input("id", Integer.class);
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

    public static void addIfMaxFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("");
            }
            StudyGroup studyGroup = StudyGroup.InputFromFile(inputSplit, true);
            if (studyGroup != null  & ifMax(studyGroup)) {
                Collection.getInstance().addElement(studyGroup);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
