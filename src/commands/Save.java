package commands;

import inputOutput.CollectionOutputToCSV;

public class Save implements Helpable{

    public  static void save() {
        CollectionOutputToCSV.output();
    }

    public String getHelp() {
        return "Save a file";
    }

}
