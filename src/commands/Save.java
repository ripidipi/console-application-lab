package commands;

import relatedToTheCollection.Collection;

public class Save implements Helpable{

    public  static void save() {
        Collection.getInstance();
        Collection.output();
    }

    public String getHelp() {
        return "Save a file";
    }

}
