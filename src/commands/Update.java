package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

public class Update implements Helpable{

    public static void update() {
        StudyGroup studyGroup = StudyGroup.Input();
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup sG : collection) {
            if(Objects.equals(sG.getId(), studyGroup.getId())) {
                Collection.getInstance().removeElement(sG);
                Collection.getInstance().addElement(studyGroup);
                break;
            }
        }
    }

    public static void updateFromFile(String input) {

    }

    public String getHelp() {
        return "update";
    }
}
