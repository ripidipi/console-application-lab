package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import relatedToTheCollection.*;

import java.util.Scanner;

public class StudyGroupInput {

    public static StudyGroup Input(int id) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter study group name:");
            String name = scanner.nextLine();
            if(name.isEmpty()) {throw new EmptyLine("name");}
            Coordinates coordinates = CoordinatesInput.Input();
            System.out.println("Enter students count:");
            int studentCount = scanner.nextInt();
            if (studentCount <= 0) {throw new ZeroValue("students count");}
            FormOfEducation formOfEducation = EnumInput.Input(FormOfEducation.class);
            Semester semester = EnumInput.Input(Semester.class);
            Person person = PersonInput.Input();
            return new StudyGroup(id, name, coordinates, studentCount, formOfEducation, semester, person);
        } catch (EmptyLine e) {
            System.out.println(e.getMessage());
        }
        return Input(id);
    }

}