import commands.Add;
import commands.Help;
import commands.Info;
import input.CommandsInput;
import relatedToTheCollection.Collection;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        Collection.getInstance();
        Help.getInstance().addCommand(new Add(), Help.getInstance(), new Info());
        while(true) {
            System.err.flush();
            System.out.print("Enter the command: ");
            CommandsInput.Input();
        }
    }
}