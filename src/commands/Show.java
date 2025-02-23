package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.TreeSet;

public class Show implements Helpable {

    public static void show() {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup studyGroup : collection) {
            System.out.println(studyGroup);
        }
    }

    public String getHelp() {
        return "show";
    }

}
