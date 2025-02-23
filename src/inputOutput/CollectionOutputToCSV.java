package inputOutput;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.TreeSet;

public class CollectionOutputToCSV {

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
