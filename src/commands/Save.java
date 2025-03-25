package commands;

import input_output.DistributionOfTheOutputStream;
import related_to_the_collection.Collection;

/**
 * Command that saves the collection data to a file.
 */
public class Save implements Helpable, Command {

    @Override
    public void execute(String arg, String inputMode) {
        DistributionOfTheOutputStream.println("Saving...");
        Collection.output();
        DistributionOfTheOutputStream.println("Save finished");
    }

    @Override
    public String getHelp() {
        return "Save the collection data to a file";
    }

}
