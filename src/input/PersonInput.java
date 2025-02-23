package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import relatedToTheCollection.Coordinates;
import relatedToTheCollection.Person;

public class PersonInput {

    /**
     * Input manager to create object with class Person
     * @return object with class Person
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static Person Input() throws EmptyLine, ZeroValue {
        try {
            String name = BasicDataTypesInput.readInput("name", String.class);
            java.time.LocalDateTime birthday = BasicDataTypesInput.readInput("birthday data in format DD/MM/YYYY",
                                                                                java.time.LocalDateTime.class);
            double height = BasicDataTypesInput.readInput("height", Double.class);
            String passportID = BasicDataTypesInput.readInput("passportID", String.class, false, false);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
            return Input();
        }


    }

}
