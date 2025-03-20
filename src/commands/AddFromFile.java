package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

/**
 * Command for adding study groups to the collection from file.
 */
public class AddFromFile implements Command {

    /**
     * Adds a study group from a file input.
     *
     * @param input A string containing study group parameters separated by commas.
     */
    public static void addStudyGroupFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, false);
            if (studyGroup != null) {
                Collection.getInstance().addElement(studyGroup);
            }
        } catch (InsufficientNumberOfArguments e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg) {
        addStudyGroupFromFile(arg);
    }

}
