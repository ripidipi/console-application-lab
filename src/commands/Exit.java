package commands;

import input_output.DistributionOfTheOutputStream;

/**
 * Command that exits the program.
 */
public class Exit implements Helpable, Command {

    public static boolean running = true;

    /**
     * Exits the program with a status code of 0 (successful termination).
     */
    public static void exit() {
        if (running)
            DistributionOfTheOutputStream.println("Exiting the program.");
        running = false;
    }

    @Override
    public void execute(String arg, String inputMode) {
        exit();
    }

    @Override
    public String getHelp() {
        return "Exits the program.";
    }
}
