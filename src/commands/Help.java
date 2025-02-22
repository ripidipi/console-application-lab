package commands;

import java.util.ArrayList;

public class Help {

    ArrayList<Commands> commands;

    public Help() {
        commands = new ArrayList<>();
    }

    public void help() {
        for (Commands command : commands) {
            System.out.println(command.getHelp());
        }
    }

    public void addCommand(Commands command) {
        commands.add(command);
    }
}
