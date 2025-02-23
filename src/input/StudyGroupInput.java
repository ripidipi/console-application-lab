package input;

import relatedToTheCollection.*;

/**
 * class with static method for StudyGroup object readings
 */
public class StudyGroupInput implements Inputable{

    /**
     * Input manager to create object with class StudyGroup
     * @return object with class StudyGroup
     */
    public static StudyGroup Input() {
        try {
            String name = BasicDataTypesInput.Input("name", String.class);
            Coordinates coordinates = CoordinatesInput.Input();
            Integer studentCount = BasicDataTypesInput.Input("students count", Integer.class);
            FormOfEducation formOfEducation = EnumInput.Input(FormOfEducation.class);
            Semester semester = EnumInput.Input(Semester.class);
            Person groupAdmin = PersonInput.Input();
            return new StudyGroup(name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        } catch (Exception e) {
            System.err.println("Invalid input. Try again.");
        }
        return Input();
    }

}