package commands;

import inputOutput.CommandsInput;

public class ExecuteScript implements Helpable{

    public static void executeScript(String fileName) {
        CommandsInput.InputFromFile(fileName);
    }

    public String getHelp() {
        return "ExecuteScript";
    }

}
