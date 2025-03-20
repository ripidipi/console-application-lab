package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.Person;
import related_to_the_collection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that counts the number of study groups where a specified person is the admin from file.
 */
public class CountByGroupAdminFromFile implements Command {

    /**
     * Counts the number of study groups where the person from file input is the admin.
     *
     * @param input A string containing person attributes separated by commas.
     * @throws InsufficientNumberOfArguments if the provided input has insufficient arguments.
     */
    public static void countByGroupAdminFromFile(String input) {
        String[] inputSplit = input.split(",");
        if (inputSplit.length != 4) {
            throw new InsufficientNumberOfArguments("CountByGroupAdmin");
        }

        Person person = Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
        TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
        int adminCounter = 0;
        for (StudyGroup studyGroup : studyGroups) {
            if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                adminCounter++;
            }
        }
        System.out.println("The person is an admin in " + adminCounter + " groups.");
    }

    @Override
    public void execute(String arg) {
        countByGroupAdminFromFile(arg);
    }

}
