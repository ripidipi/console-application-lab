package relatedToTheCollection;

import java.security.SecureRandom;
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
    private final Semester semesterEnum;
    private final Person groupAdmin;

    public StudyGroup(String name, Coordinates coordinates,
                      Integer studentCount, FormOfEducation formOfEducation,
                      Semester semesterEnum, Person groupAdmin) {
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
        this.semesterEnum = semesterEnum;
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
                            formOfEducation, semesterEnum, groupAdmin);
    }

    @Override
    public String toString() {
        return "StudyGroup {" +
                "\nid: " + id +
                "\nname: " + name +
                "\ncoordinates: " + coordinates +
                "\ncreation date: " + creationDate +
                "\nstudent count: " + studentCount +
                "\nfrom of education: " + formOfEducation +
                "\nsemester enum: " + semesterEnum +
                "\ngroup admin" + groupAdmin + '}';
    }

    public Integer getId() {return id;}

    public String getName() {return name;}

    public Coordinates getCoordinates() {return coordinates;}

    public java.time.LocalDateTime getCreationDate() {return creationDate;}

    public String  getCreationDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return creationDate.format(formatter);
    }

    public Integer getStudentCount() {return studentCount;}

    public FormOfEducation getFormOfEducation() { return formOfEducation;}

    public Semester getSemesterEnum() { return semesterEnum;}

    public Person getGroupAdmin() { return groupAdmin;}

}











