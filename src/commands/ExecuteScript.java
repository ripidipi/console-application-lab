package commands;

import exeptions.IncorrectValue;
import exeptions.InfiniteRecursion;
import inputOutput.CommandsInput;
import inputOutput.RunningFiles;

import java.util.Set;

public class ExecuteScript implements Helpable{

    public static void executeScript(String fileName) {
        try {
            if (fileName.isEmpty()) {
                throw new IncorrectValue("Execute_script");
            }
            if (RunningFiles.getInstance().isThere(fileName)) {
                throw new InfiniteRecursion(fileName);
            }
            RunningFiles.getInstance().addFilesNames(fileName);
            CommandsInput.inputFromFile(fileName, CommandsInput::isCommand);
            RunningFiles.getInstance().removeFilesNames(fileName);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }


public String getHelp() {
    return "ExecuteScript";
}

}
