import commands.Add;
import commands.Help;
import commands.Info;
import commands.Show;
import input.CommandsInput;
import relatedToTheCollection.Collection;

public class Main {

    public static void main(String[] args) {
        Collection.getInstance();
        Help.getInstance().addCommand(Help.getInstance(),new Add(), new Info(), new Show());
        while(true) {
            System.err.flush();
            System.out.print("Enter the command: ");
            CommandsInput.Input();
        }
    }

}