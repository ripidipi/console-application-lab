package commands;

import inputOutput.CommandsInput;

public class ExecuteScript implements Helpable{

    public static void executeScript(String fileName) {
        CommandsInput.inputFromFile(fileName);
    }

    public String getHelp() {
        return "ExecuteScript";
    }

}
