package relatedToTheCollection;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
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

    public void clearCollection() {
        collection.clear();
    }

    public void removeElement(StudyGroup studyGroup) {
        collection.remove(studyGroup);
    }

    public TreeSet<StudyGroup> getCollection() {
        return collection;
    }

    public int getSize() {
        return collection.size();
    }

    public void addElement(StudyGroup studyGroup) {
        collection.add(studyGroup);
    }

    public static void output() {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        String csvFile = "collection.csv";
        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(csvFile), StandardCharsets.UTF_8)) {
            writer.write("ID,Name,CoordinateX,CoordinateY,CreationDate,StudentsCount," +
                    "FormOfEducation,Semester,AdminName,AdminBirthday,Height,PassportID\n");  // Заголовки
            for (StudyGroup studyGroup : collection) {
                String writeRequest = studyGroup.getId().toString() + "," + studyGroup.getCoordinates().xToString() + "," +
                        studyGroup.getCoordinates().yToString() + "," + studyGroup.getCreationDateString() + "," +
                        studyGroup.getStudentCount().toString() + "," + studyGroup.getFormOfEducation().toString() + "," +
                        studyGroup.getSemester().toString() + "," + studyGroup.getGroupAdmin().name() + "," +
                        studyGroup.getGroupAdmin().getBirthdayString() + "," + studyGroup.getGroupAdmin().height().toString() + "," +
                        studyGroup.getGroupAdmin().passportID() + '\n';
                writer.write(writeRequest);
            }
        } catch (Exception e) {
            System.out.println("Saving failed, try again later");
        }
    }
}
