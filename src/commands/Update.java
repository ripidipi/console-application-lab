package commands;

import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

public class Update implements Helpable{

    public static void update() {
        StudyGroup studyGroup = StudyGroup.Input();
        replacementInTheCollection(studyGroup);
    }

    public static void updateFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length < 12) {
                throw new InsufficientNumberOfArguments("Update");
            }
            StudyGroup studyGroup = StudyGroup.InputFromFile(inputSplit);
            replacementInTheCollection(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void replacementInTheCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup sG : collection) {
            if(Objects.equals(sG.getId(), studyGroup.getId())) {
                Collection.getInstance().removeElement(sG);
                Collection.getInstance().addElement(studyGroup);
                break;
            }
        }
    }

    public String getHelp() {
        return "update";
    }
}
