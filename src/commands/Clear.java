package commands;

import input_output.DistributionOfTheOutputStream;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

/**
 * Command that clears the entire collection.
 */
public class Clear implements Helpable, Command {

    /**
     * Clears all elements from the collection and resets study group IDs.
     */
    public static void clearCollection() {
        Collection.getInstance().clearCollection();
        StudyGroup.clearIds();
        DistributionOfTheOutputStream.println("The collection has been cleared.");
    }

    /**
     * Executes the Clear command, removing all elements from the collection.
     *
     * @param arg       unused argument
     * @param inputMode unused input mode
     */
    @Override
    public void execute(String arg, String inputMode) {
        clearCollection();
    }

    /**
     * Returns the help information for the command.
     *
     * @return a string describing the command usage
     */
    @Override
    public String getHelp() {
        return "Clears the collection.";
    }
}
