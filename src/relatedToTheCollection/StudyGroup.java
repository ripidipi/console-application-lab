package relatedToTheCollection;

import java.util.Objects;


public class StudyGroup implements Comparable<StudyGroup> {
    /**
     * Class of object in collection
     */
    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Integer studentCount;
    private FormOfEducation formOfEducation;
    private Semester semesterEnum;
    private Person groupAdmin;

    public StudyGroup(Integer id, String name, Coordinates coordinates,
                      Integer studentCount, FormOfEducation formOfEducation,
                      Semester semesterEnum, Person groupAdmin) {
        this.id = id;
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
                "\ngroup admin" + groupAdmin;
    }

    public Integer getId() {return id;}

    public String getName() {return name;}

    public Coordinates getCoordinates() {return coordinates;}

    public java.time.LocalDateTime getCreationDate() {return creationDate;}

    public Integer getStudentCount() {return studentCount;}

    public FormOfEducation getFormOfEducation() { return formOfEducation;}

    public Semester getSemesterEnum() { return semesterEnum;}

    public Person getGroupAdmin() { return groupAdmin;}

}











