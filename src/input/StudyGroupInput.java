package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import modules.*;

import java.util.Scanner;

public class StudyGroupInput {


    private Integer id;
    private String name;
    private Coordinates coordinates;
    private java.time.LocalDateTime creationDate;
    private Integer studentCount;
    private FormOfEducation formOfEducation;
    private Semester semesterEnum;
    private Person groupAdmin;

    public static StudyGroup Input() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter study group name:");
            String name = scanner.nextLine();
            if(name.isEmpty()) {throw new EmptyLine("name");}
            Coordinates coordinates = CoordinatesInput.Input();
            System.out.println("Enter students count:");
            int studentCount = scanner.nextInt();
            if (studentCount <= 0) {throw new ZeroValue("students count");}
            System.out.println("Enter form of education:");
            String form = scanner.nextLine().toUpperCase();
            FormOfEducation formOfEducationEnum = FormOfEducation.valueOf(form);
            System.out.println("Enter semester:");
            String sem = scanner.nextLine().toUpperCase();
            Semester semester = Semester.valueOf(sem);

        } catch (EmptyLine e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }

}