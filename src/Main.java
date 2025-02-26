import commands.*;
import inputOutput.CommandsInput;
import inputOutput.FillCollectionFromFile;
import inputOutput.RunningFiles;
import relatedToTheCollection.Collection;

import java.util.HashSet;
import java.util.Set;

public class Main {

    public static Set<String> runningFiles = new HashSet<String>();

    public static void main(String[] args) {
        Collection.getInstance();
        RunningFiles.getInstance();
        Help.getInstance().addCommand(Help.getInstance(),new Add(), new Info(), new Show(),
                                        new Update(), new Exit(), new Save());
        while(true) {
            System.out.print("Enter the command: ");
            CommandsInput.inputFromConsole();
        }
    }

}