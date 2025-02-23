package relatedToTheCollection;

import java.util.TreeSet;

public class Collection {

    private TreeSet<StudyGroup> collection = new TreeSet<>();
    private final java.time.LocalDateTime date;
    private static Collection instance;

    private Collection() {
        date = java.time.LocalDateTime.now();
    }

    public static Collection getInstance() {
        if (instance == null) {
            instance = new Collection();
        }
        return instance;
    }

    public String getInfo() {
        return "TreeSet " + date.toString() + " " + collection.size();
    }

    public TreeSet<StudyGroup> getContent() {
        return collection;
    }

    public int getSize() {
        return collection.size();
    }

}
