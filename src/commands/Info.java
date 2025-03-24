package commands;

import input_output.DistributionOfTheOutputStream;
import related_to_the_collection.Collection;

/**
 * Command that provides information about the collection.
 */
public class Info implements Helpable, Command {

    /**
     * Prints information about the collection (type, initialization date, number of elements).
     */
    public static void info() {
        DistributionOfTheOutputStream.println(Collection.getInstance().getInfo());
    }

    @Override
    public void execute(String arg, String inputMode) {
        info();
    }

    @Override
    public String getHelp() {
        return "Returns information about the collection (type, initialization date, number of elements).";
    }
}
