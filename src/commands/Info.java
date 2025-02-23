package commands;

import relatedToTheCollection.Collection;

public class Info implements Helpable {

    /**
     * Print info about collection (type, initialization date, number of elements)
     */
    public static void info() {
        System.out.println(Collection.getInstance().getInfo());
    }

    /**
     * add an information about info
     * @return String with information about command
     */
    public String getHelp() {
        return "Return information about the collection\n" +
                "(type, initialization date, number of elements)";
    }
}
