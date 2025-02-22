package commands;

import relatedToTheCollection.Collection;

public class Info implements Helpable {

    /**
     * @param collection shows what information to return about
     */
    public static void info(Collection collection) {
        System.out.println(collection.getInfo());
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
