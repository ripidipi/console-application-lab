package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import relatedToTheCollection.Person;

import java.time.LocalDateTime;

/**
 * class with static method for Person object readings
 */
public class PersonInput implements Inputable {

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
            Double height = BasicDataTypesInput.Input("height", Double.class, false, true);
            String passportID = BasicDataTypesInput.Input("passportID", String.class);
            return new Person(name, birthday, height, passportID);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return Input();

    }

}
