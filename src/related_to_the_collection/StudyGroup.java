package related_to_the_collection;

import commands.Exit;
import exceptions.CommandDataFromTheFileIsIncorrect;
import exceptions.RemoveOfTheNextSymbol;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import input_output.EnumInput;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Class representing a study group. This class implements the Comparable interface to allow comparison
 * of study groups based on their unique ID.
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

    /**
     * Constructs a StudyGroup with a generated unique ID.
     *
     * @param name The name of the study group.
     * @param coordinates The coordinates of the study group.
     * @param studentCount The number of students in the group.
     * @param formOfEducation The form of education of the group.
     * @param semester The semester of the group.
     * @param groupAdmin The admin of the group.
     */
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
        this.creationDate = LocalDateTime.now();
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Constructs a StudyGroup with a provided unique ID.
     *
     * @param id The unique ID of the study group.
     * @param name The name of the study group.
     * @param coordinates The coordinates of the study group.
     * @param studentCount The number of students in the group.
     * @param formOfEducation The form of education of the group.
     * @param semester The semester of the group.
     * @param groupAdmin The admin of the group.
     */
    public StudyGroup(Integer id, String name, Coordinates coordinates, Integer studentCount,
                      FormOfEducation formOfEducation,
                      Semester semester, Person groupAdmin) {
        this.id = id;
        if (id != null)
            IDs.put(id, true);
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = LocalDateTime.now();
        this.studentCount = studentCount;
        this.formOfEducation = formOfEducation;
        this.semester = semester;
        this.groupAdmin = groupAdmin;
    }

    /**
     * Compares this StudyGroup to another StudyGroup based on the ID.
     *
     * @param other The StudyGroup to compare this one to.
     * @return A negative integer, zero, or a positive integer as this ID is less than, equal to, or greater than the ID of the other StudyGroup.
     */
    @Override
    public int compareTo(StudyGroup other) {
        return this.id.compareTo(other.id);
    }

    /**
     * Compares this StudyGroup to another object for equality. Two StudyGroups are equal if their IDs are the same.
     *
     * @param o The object to compare to.
     * @return true if the IDs are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudyGroup that = (StudyGroup) o;
        return Objects.equals(id, that.id);
    }

    /**
     * Returns a hash code value for this StudyGroup. The hash code is based on the ID, name, coordinates,
     * creation date, student count, form of education, semester, and group admin.
     *
     * @return The hash code value for this StudyGroup.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, studentCount,
                formOfEducation, semester, groupAdmin);
    }

    /**
     * Returns a string representation of this StudyGroup. The string includes the ID, name, coordinates,
     * creation date, student count, form of education, semester, and group admin.
     *
     * @return The string representation of this StudyGroup.
     */
    @Override
    public String toString() {
        return "StudyGroup {" +
                "id: " + id +
                "\tname: " + name +
                "\n" + coordinates +
                "\tcreation date: " + getCreationDateString() +
                "\tstudent count: " + studentCount +
                "\tform of education: " + formOfEducation +
                "\tsemester: " + semester +
                "\n" + groupAdmin + "}\n";
    }

    /**
     * Gets the ID of the StudyGroup.
     *
     * @return The ID of the StudyGroup.
     */
    public Integer getId() { return id; }

    /**
     * Gets the name of the StudyGroup.
     *
     * @return The name of the StudyGroup.
     */
    public String getName() { return name; }

    /**
     * Gets the coordinates of the StudyGroup.
     *
     * @return The coordinates of the StudyGroup.
     */
    public Coordinates getCoordinates() { return coordinates; }

    /**
     * Gets the creation date of the StudyGroup.
     *
     * @return The creation date of the StudyGroup.
     */
    public java.time.LocalDateTime getCreationDate() { return creationDate; }

    /**
     * Gets the creation date of the StudyGroup as a formatted string.
     *
     * @return The creation date as a string in the format dd/MM/yyyy HH:mm:ss.
     */
    public String getCreationDateString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return creationDate.format(formatter);
    }

    /**
     * Gets the student count of the StudyGroup.
     *
     * @return The number of students in the StudyGroup.
     */
    public Integer getStudentCount() { return studentCount; }

    /**
     * Remove all ids from Id list
     */
    public static void clearIds() { IDs.clear(); }

    /**
     * Gets the form of education of the StudyGroup.
     *
     * @return The form of education of the StudyGroup.
     */
    public FormOfEducation getFormOfEducation() { return formOfEducation; }

    /**
     * Gets the semester of the StudyGroup.
     *
     * @return The semester of the StudyGroup.
     */
    public Semester getSemester() { return semester; }

    /**
     * Gets the group admin of the StudyGroup.
     *
     * @return The group admin of the StudyGroup.
     */
    public Person getGroupAdmin() { return groupAdmin; }

    public static StudyGroup getEmptyStudyGroup() {
        return new StudyGroup(-1, " ", new Coordinates(-1L, -1F), -1,
                FormOfEducation.FULL_TIME_EDUCATION, Semester.EIGHTH,
                new Person(" ", PrimitiveDataTransform.inputFromFile("birthday",
                        "12/12/1212 00:00:00", LocalDateTime.class), -1D, " "));
    }

    /**
     * Checks if the StudyGroup object has all required fields filled.
     *
     * @param studyGroup The StudyGroup object to check.
     * @return true if the StudyGroup is valid, false otherwise.
     */
    private static boolean isRightFill(StudyGroup studyGroup) {
        if (studyGroup == null) { return false; }
        return Person.isRightFill(studyGroup.groupAdmin) && Coordinates.isRightFill(studyGroup.coordinates) &&
                studyGroup.name != null && studyGroup.creationDate != null && studyGroup.studentCount != null &&
                studyGroup.formOfEducation != null && studyGroup.semester != null && studyGroup.id != null;
    }

    /**
     * Input manager for creating a StudyGroup object from user input.
     *
     * @param Arg Optional argument to specify the ID.
     * @return A new StudyGroup object created from the user input.
     */
    public static StudyGroup input(String... Arg) throws RemoveOfTheNextSymbol {
        Integer id = null;
        if (Arg.length > 0) {
            id = PrimitiveDataTransform.inputFromFile("id", Arg[0], Integer.class);
        }
        System.out.print("Enter information about study group");
        String name = PrimitiveDataTransform.input("name", String.class);
        Coordinates coordinates = Coordinates.input();
        Integer studentCount = PrimitiveDataTransform.input("students count", Integer.class);
        FormOfEducation formOfEducation = EnumInput.inputFromConsole(FormOfEducation.class);
        Semester semester = EnumInput.inputFromConsole(Semester.class);
        Person groupAdmin = Person.input();
        if (id != null) {
            return new StudyGroup(id, name, coordinates, studentCount,
                    formOfEducation, semester, groupAdmin);
        }
        return new StudyGroup(name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
    }

    /**
     * Input manager for creating a StudyGroup object from data read from a file.
     *
     * @param inputSplit The input data split into parts.
     * @param notAdded Flag to indicate if the ID should not be added to the list of IDs.
     * @return A new StudyGroup object created from the file data.
     */
    public static StudyGroup inputFromFile(String[] inputSplit, boolean notAdded) {
        try {
            Integer id = PrimitiveDataTransform.inputFromFile("id", inputSplit[0], Integer.class);
            String name = PrimitiveDataTransform.inputFromFile("name", inputSplit[1], String.class);
            Coordinates coordinates = Coordinates.inputFromFile(inputSplit[2], inputSplit[3]);
            Integer studentCount = PrimitiveDataTransform.inputFromFile("students count", inputSplit[4],
                    Integer.class);
            FormOfEducation formOfEducation = EnumInput.TransformToEnum(FormOfEducation.class, inputSplit[5]);
            Semester semester = EnumInput.TransformToEnum(Semester.class, inputSplit[6]);
            Person groupAdmin = Person.inputFromFile(inputSplit[7], inputSplit[8], inputSplit[9], inputSplit[10]);
            return rightInisilizeStudyGroup(inputSplit, notAdded, id, name, coordinates, studentCount,
                    formOfEducation, semester, groupAdmin);
        } catch (CommandDataFromTheFileIsIncorrect e) {
            // file out
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
        return null;
    }

    public static StudyGroup inputMixed(String[] inputSplit, boolean notAdded, boolean isId) {
        try {
            int index = 0;

            Integer id = null;
            if (isId) {
                id = (index < inputSplit.length) ?
                        PrimitiveDataTransform.inputFromFile("id", inputSplit[index++], Integer.class) :
                        PrimitiveDataTransform.input("id", Integer.class);
            }

            String name = (index < inputSplit.length) ?
                    PrimitiveDataTransform.inputFromFile("name", inputSplit[index++], String.class) :
                    PrimitiveDataTransform.input("name", String.class);

            Long coordX = (index < inputSplit.length) ?
                    ((Objects.equals(inputSplit[index++], " ") ?
                            null :
                            PrimitiveDataTransform.inputFromFile("x", inputSplit[index++], Long.class))) :
                    PrimitiveDataTransform.input("x coordinate", Long.class, false,
                    false, false, null);
            Float coordY = (index < inputSplit.length) ?
                    ((Objects.equals(inputSplit[index++], " ") ?
                            null :
                            PrimitiveDataTransform.inputFromFile("y", inputSplit[index++], Float.class))) :
                    PrimitiveDataTransform.input("y coordinate", Float.class, false,
                            false, false, null);
            Coordinates coordinates = new Coordinates(coordX, coordY);

            Integer studentCount = (index < inputSplit.length) ?
                    PrimitiveDataTransform.inputFromFile("students count", inputSplit[index++], Integer.class) :
                    PrimitiveDataTransform.input("students count", Integer.class);

            FormOfEducation formOfEducation = (index < inputSplit.length) ?
                    EnumInput.TransformToEnum(FormOfEducation.class, inputSplit[index++]) :
                    EnumInput.inputFromConsole(FormOfEducation.class);

            Semester semester = (index < inputSplit.length) ?
                    EnumInput.TransformToEnum(Semester.class, inputSplit[index++]) :
                    EnumInput.inputFromConsole(Semester.class);

            String adminName = (index < inputSplit.length) ?
                    PrimitiveDataTransform.inputFromFile("groupAdminName", inputSplit[index++], String.class) :
                    PrimitiveDataTransform.input("name", String.class);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDateTime dateTime = (index < inputSplit.length) ?
                    PrimitiveDataTransform.inputFromFile("adminBirthday", inputSplit[index++],
                            LocalDateTime.class, false, false,
                            true, formatter, false) :
                    PrimitiveDataTransform.input("birthday data in format DD.MM.YYYY",
                            LocalDateTime.class, false, false,
                            true, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Double height = (index < inputSplit.length) ?
                    ((Objects.equals(inputSplit[index++], " ") ?
                            null :
                            PrimitiveDataTransform.inputFromFile("adminHeight", inputSplit[index++],
                                    Double.class, false, true,
                                    false, null, false))) :
                    PrimitiveDataTransform.input("height", Double.class, false,
                            true, false, null);
            String adminPassport = (index < inputSplit.length) ?
                    PrimitiveDataTransform.inputFromFile("adminPassportID", inputSplit[index++], String.class) :
                    PrimitiveDataTransform.input("passportID", String.class);
            Person groupAdmin = new Person(adminName, dateTime, height, adminPassport);

            return rightInisilizeStudyGroup(inputSplit, notAdded, id, name, coordinates, studentCount,
                    formOfEducation, semester, groupAdmin);
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
        return null;
    }

    private static StudyGroup rightInisilizeStudyGroup(String[] inputSplit, boolean notAdded, Integer id, String name,
                                                       Coordinates coordinates, Integer studentCount,
                                                       FormOfEducation formOfEducation, Semester semester,
                                                       Person groupAdmin) {
        if (id != null && IDs.containsKey(id) && !notAdded) {
            id = null;
        }

        StudyGroup studyGroup = new StudyGroup(id, name, coordinates, studentCount,
                formOfEducation, semester, groupAdmin);

        if (!isRightFill(studyGroup)) {
            throw new CommandDataFromTheFileIsIncorrect(String.join(",", inputSplit));
        }

        return studyGroup;
    }

}