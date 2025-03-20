package commands;

import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.TreeSet;

/**
 * Command that shows all study groups in the collection.
 */
public class Show implements Helpable, Command {

    /**
     * Displays all study groups in the collection.
     */
    public static void show() {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();

        if (collection.isEmpty()) {
            System.out.println("Collection is empty");
            return;
        }

        for (StudyGroup studyGroup : collection) {
            System.out.println(studyGroup);
        }
    }

    @Override
    public void execute(String arg) {
        show();
    }

    @Override
    public String getHelp() {
        return "Displays all study groups in the collection. If the collection is empty, shows a message indicating that.";
    }
}
