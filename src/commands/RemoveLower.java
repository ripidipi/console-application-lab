package commands;

import exeptions.InsufficientNumberOfArguments;
import inputOutput.PrimitiveDataTransform;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.ArrayList;
import java.util.TreeSet;

public class RemoveLower {

    public static void removeLower() {
        Integer id = PrimitiveDataTransform.input("id", Integer.class);
        StudyGroup studyGroup = StudyGroup.Input(id.toString());
        try {
            removeLoverFromCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeLowerFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("");
            }
            StudyGroup studyGroup = StudyGroup.InputFromFile(inputSplit, true);
            if (studyGroup == null) return;
            removeLoverFromCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void removeLoverFromCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        ArrayList<StudyGroup> toRemove = new ArrayList<>();
        for (StudyGroup sG : collection) {
            if(sG.compareTo(studyGroup) < 0) {
                toRemove.add(sG);
            }
        }
        for (StudyGroup sG : toRemove) {
            collection.remove(sG);
        }
    }

}
