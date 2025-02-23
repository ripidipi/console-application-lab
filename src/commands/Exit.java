package commands;

public class Exit implements Helpable{

    public static void exit() {
        System.exit(0);
    }

    public String getHelp() {
        return "Exit the program";
    }

}
