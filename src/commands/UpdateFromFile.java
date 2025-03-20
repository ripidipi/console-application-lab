package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import static commands.Update.replacementInTheCollection;

/**
 * Command that updates a study group in the collection by its ID from file.
 */
public class UpdateFromFile implements Command {

    /**
     * Updates a study group based on data from a file.
     *
     * @param input The input string containing information to create the study group.
     */
    public static void updateFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length != inputSplit.length) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            replacementInTheCollection(studyGroup);
        } catch (InsufficientNumberOfArguments e) {
            // file log
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg) {
        updateFromFile(arg);
    }

}
