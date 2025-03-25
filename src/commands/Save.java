package commands;

import input_output.DistributionOfTheOutputStream;
import related_to_the_collection.Collection;

/**
 * Command that saves the collection data to a file.
 */
public class Save implements Helpable, Command {

    /**
     * Saves the collection data to a file.
     */
    public static void save() {
        DistributionOfTheOutputStream.println("Saving...");
        Collection.output();
    }

    @Override
    public void execute(String arg, String inputMode) {
        save();
    }

    @Override
    public String getHelp() {
        return "Save the collection data to a file";
    }

}
