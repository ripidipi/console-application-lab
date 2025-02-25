package relatedToTheCollection;

import exeptions.CommandDataFromTheFileIsIncorrect;
import inputOutput.BasicDataTypesInput;
import inputOutput.EnumInput;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Class of objects in collection
 */
public class StudyGroup implements Comparable<StudyGroup> {

    private static Map<Integer, Boolean> IDs = new ConcurrentHashMap<>();
    private final Integer id;
    private final String name;
    private final Coordinates coordinates;
    private final java.time.LocalDateTime creationDate;
    private final Integer studentCount;
    private final FormOfEducation formOfEducation;
    private final Semester semester;
    private final Person groupAdmin;

    public StudyGroup(String name, Coordinates coordinates,
                      Integer studentCount, FormOfEducation formOfEducation,
                      Semester semester, Person groupAdmin) {
        int randomID;
        SecureRandom random = new SecureRandom();
        do {
            randomID = 100000 + random.nextInt(900000); // 6 digits
        } while (IDs.containsKey(randomID));
        this.id = randomID;
        IDs.put(randomID, true);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = java.time.LocalDateTime.now();
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    public StudyGroup(Integer id, String name, Coordinates coordinates,
                      LocalDateTime creationDate, Integer studentCount,
                      FormOfEducation formOfEducation,
                      Semester semester, Person groupAdmin) {
        this.id = id;
        IDs.put(id, true);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * @param other the object to be compared.
     * @return comparison of objects
     */
    @Override
    public int compareTo(StudyGroup other) {
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentCount,
                            formOfEducation, semester, groupAdmin);
    }

    @Override
    public String toString() {
        return "StudyGroup {" +
                "\nid: " + id +
                "\nname: " + name +
                "\n" + coordinates +
                "\ncreation date: " + getCreationDateString() +
                "\nstudent count: " + studentCount +
                "\nfrom of education: " + formOfEducation +
                "\nsemester: " + semester +
                "\n" + groupAdmin + '}';
    }

    public Integer getId() {return id;}

    public String getName() {return name;}

    public Coordinates getCoordinates() {return coordinates;}

    public java.time.LocalDateTime getCreationDate() {return creationDate;}

    public String  getCreationDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return creationDate.format(formatter);
    }

    public Integer getStudentCount() {return studentCount;}

    public FormOfEducation getFormOfEducation() { return formOfEducation;}

    public Semester getSemester() { return semester;}

    public Person getGroupAdmin() { return groupAdmin;}

    private static boolean isRightFill(StudyGroup studyGroup) {
        if (studyGroup == null) {return false;}
        return Person.isRightFill(studyGroup.groupAdmin) && Coordinates.isRightFill(studyGroup.coordinates) &&
                studyGroup.name != null && studyGroup.creationDate != null && studyGroup.studentCount != null &&
                studyGroup.formOfEducation != null && studyGroup.semester != null;
    }

    /**
     * Input manager to create object with class StudyGroup
     * @return object with class StudyGroup
     */
    public static StudyGroup Input() {
        try {
            System.out.print("Enter information about study group");
            String name = BasicDataTypesInput.Input("name", String.class);
            Coordinates coordinates = Coordinates.Input();
            Integer studentCount = BasicDataTypesInput.Input("students count", Integer.class);
            FormOfEducation formOfEducation = EnumInput.InputFromConsole(FormOfEducation.class);
            Semester semester = EnumInput.InputFromConsole(Semester.class);
            Person groupAdmin = Person.Input();
            return new StudyGroup(name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again.");
        }
        return Input();
    }

    public static StudyGroup InputFromFile(String[] inputSplit) {
        try {
            Integer id = Integer.parseInt(inputSplit[0]);
            String name = BasicDataTypesInput.InputFromFile("name", inputSplit[1], String.class);
            Coordinates coordinates = Coordinates.InputFromFile(inputSplit[2], inputSplit[3]);
            LocalDateTime creationDate = BasicDataTypesInput.InputFromFile("creationDate", inputSplit[4],
                    LocalDateTime.class, false, false, false,
                    DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
            Integer studentCount = BasicDataTypesInput.InputFromFile("students count", inputSplit[5], Integer.class);
            FormOfEducation formOfEducation = EnumInput.TransformToEnum(FormOfEducation.class, inputSplit[6]);
            Semester semester = EnumInput.TransformToEnum(Semester.class, inputSplit[7]);
            Person groupAdmin = Person.InputFromFile(inputSplit[8], inputSplit[9], inputSplit[10], inputSplit[11]);
            if (IDs.containsKey(id)) {
                id = null;
            }
            StudyGroup studyGroup = new StudyGroup(id, name, coordinates, creationDate, studentCount, formOfEducation, semester, groupAdmin);
            if (!isRightFill(studyGroup)) {
                throw new CommandDataFromTheFileIsIncorrect(String.join(",", inputSplit));
            }
            return studyGroup;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}











