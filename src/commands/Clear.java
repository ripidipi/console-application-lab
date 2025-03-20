package commands;

import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

/**
 * Command that clears the entire collection.
 */
public class Clear implements Helpable, Command {

    /**
     * Clears all elements from the collection.
     */
    public static void clearCollection() {
        Collection.getInstance().clearCollection();
        StudyGroup.clearIds();
        System.out.println("The collection has been cleared.");
    }

    @Override
    public void execute(String arg) {
        clearCollection();
    }

    @Override
    public String getHelp() {
        return "Clears the collection.";
    }
}
