package relatedToTheCollection;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;

public class Collection {

    private TreeSet<StudyGroup> collection = new TreeSet<>();
    private final LocalDateTime date;
    private static Collection instance;

    private Collection() {
        date = LocalDateTime.now();
    }

    public static Collection getInstance() {
        if (instance == null) {
            instance = new Collection();
        }
        return instance;
    }

    public String getInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return "TreeSet " + date.format(formatter) + " " + collection.size();
    }

    public TreeSet<StudyGroup> getContent() {
        return collection;
    }

    public int getSize() {
        return collection.size();
    }

    public void addElement(StudyGroup studyGroup) {
        collection.add(studyGroup);
    }

}
