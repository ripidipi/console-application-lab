package modules;

import java.util.ArrayList;
import java.util.TreeSet;

public class Collection {

    TreeSet<StudyGroup> collection = new TreeSet<>();
    java.time.LocalDateTime date;

    Collection() {
        date = java.time.LocalDateTime.now();
    }

    public String getInfo() {
        return "TreeSet " + date.toString() + " " + collection.size();
    }

    public TreeSet<StudyGroup> getContent() {
        return collection;
    }
}
