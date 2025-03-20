package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Person;

import static commands.RemoveAnyByGroupAdmin.removeGroupByAdmin;

/**
 * Command that removes a study group by its group admin from file.
 */
public class RemoveAnyByGroupAdminFromFile implements Command {

    /**
     * Removes the first study group with the given group admin from the collection based on input from a file.
     *
     * @param input The input string containing information to identify the person.
     */
    public static void removeAnyByGroupAdminFromFile(String input) {
        String[] inputSplit = input.split(",");
        if (inputSplit.length != 4) {
            throw new InsufficientNumberOfArguments("");
        }
        Person person = Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
        removeGroupByAdmin(person);
    }

    @Override
    public void execute(String arg) {
        removeAnyByGroupAdminFromFile(arg);
    }

}
