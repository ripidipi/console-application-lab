package relatedToTheCollection;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import inputOutput.PrimitiveDataTransform;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public record Person(String name, LocalDateTime birthday, Double height, String passportID) {

    public String getBirthdayString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return birthday.format(formatter);
    }

    @Override
    public String toString() {
        return "Group admin {" +
                "\nname: " + name +
                "\nbirthday: " + getBirthdayString() +
                "\nheight: " + (height==null ? "" : height) +
                "\npassportID: " + passportID + '}';
    }

    public static boolean isRightFill(Person person) {
        if (person == null) {
            return false;
        }
        return person.name != null && person.birthday != null && person.passportID != null;
    }

    public String heightToString() {
        return (height==null ? " " : height.toString());
    }

    /**
     * Input manager to create object with class Person
     * @return object with class Person
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static Person Input() throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter information about group admin");
            String name = PrimitiveDataTransform.input("name", String.class);
            LocalDateTime birthday = PrimitiveDataTransform.input("birthday data in format DD.MM.YYYY",
                    LocalDateTime.class, false, false,
                    true, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            Double height = PrimitiveDataTransform.input("height", Double.class, false, true, false, null);
            String passportID = PrimitiveDataTransform.input("passportID", String.class);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return Input();

    }

    public static Person InputFromFile(String name, String birthday, String height, String passportID) {
        try {
            return new Person(PrimitiveDataTransform.inputFromFile("groupAdminName", name, String.class),
                    PrimitiveDataTransform.inputFromFile("adminBirthday", birthday, LocalDateTime.class, false, false,
                            true, DateTimeFormatter.ofPattern("dd/MM/yyyy"), false),
                    PrimitiveDataTransform.inputFromFile("adminHeight", height, Double.class, false, true,
                            false, null, false),
                    PrimitiveDataTransform.inputFromFile("adminPassportID", passportID, String.class));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
