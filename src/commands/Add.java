package commands;

public class Add implements Helpable {

    public static void add() {
        System.out.println("Add command");
    }

    public String getHelp() {
        return "";
    }
}
