package commands;

import relatedToTheCollection.Collection;

public class Clear implements Helpable{

    public static void clear() {
        Collection.getInstance().clearCollection();
    }

    public String getHelp() {
        return "Clear";
    }
}
