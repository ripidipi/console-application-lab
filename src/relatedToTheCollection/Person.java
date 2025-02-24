package relatedToTheCollection;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import inputOutput.BasicDataTypesInput;

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

    /**
     * Input manager to create object with class Person
     * @return object with class Person
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static Person Input() throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter information about group admin");
            String name = BasicDataTypesInput.Input("name", String.class);
            LocalDateTime birthday = BasicDataTypesInput.Input("birthday data in format DD.MM.YYYY",
                    LocalDateTime.class);
            Double height = BasicDataTypesInput.Input("height", Double.class, false, true, false);
            String passportID = BasicDataTypesInput.Input("passportID", String.class);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return Input();

    }
}
