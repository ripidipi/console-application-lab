package commands;

import relatedToTheCollection.Collection;

import java.util.ArrayList;
import java.util.Collections;

public class Help implements Helpable {

    private static ArrayList<Helpable> commands;
    private static Help instance;

    private Help() {
        commands = new ArrayList<>();
    }

    public static Help getInstance() {
        if (instance == null) {
            instance = new Help();
        }
        return instance;
    }

    /**
     * Return information about command that was requested
     */
    public static void help() {
        for(Helpable command: commands) {
            System.out.println(command.getHelp());
        }
    }

    /**
     * Add ability to request help from the command and
     * get information about it
     * @param commandArgs array of supporting commands
     */
    public void addCommand(Helpable... commandArgs) {
        Collections.addAll(commands, commandArgs);
    }

    /**
     * add an information about help
     * @return String with information about command
     */
    public String getHelp() {
        return "Return information about commands";
    }
}
