package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import relatedToTheCollection.*;

import java.util.Scanner;

public class StudyGroupInput {

    public static StudyGroup Input(int id) {
        try {
            String name = BasicDataTypesInput.readInput("name", String.class);
            Coordinates coordinates = CoordinatesInput.Input();
            Integer studentCount = BasicDataTypesInput.readInput("students count", Integer.class);
            FormOfEducation formOfEducation = EnumInput.Input(FormOfEducation.class);
            Semester semester = EnumInput.Input(Semester.class);
            Person groupAdmin = PersonInput.Input();
            return new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, groupAdmin);
        } catch (Exception e) {
            System.err.println("Invalid input. Try again.");
        }
        return Input(id);
    }

}