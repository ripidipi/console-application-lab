import commands.*;
import inputOutput.CommandsInput;
import relatedToTheCollection.Collection;

public class Main {

    public static void main(String[] args) {
        Collection.getInstance();
        Help.getInstance().addCommand(Help.getInstance(),new Add(), new Info(), new Show(),
                                        new Update(), new Exit(), new Save());
        while(true) {
            System.err.flush();
            System.out.print("Enter the command: ");
            CommandsInput.inputFromConsole();
        }
    }

}