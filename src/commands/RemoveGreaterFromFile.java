package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import static commands.RemoveGreater.removeGreaterFromCollection;

/**
 * Command that removes study groups greater than a given one from the collection from file.
 */
public class RemoveGreaterFromFile implements Command {

    /**
     * Removes study groups greater than the specified one, based on input from a file.
     *
     * @param input The input string containing the data to create the study group.
     */
    public static void removeGreaterFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length != inputSplit.length) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            if (studyGroup == null) return;
            removeGreaterFromCollection(studyGroup);
        } catch (InsufficientNumberOfArguments e) {
            // file out
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg) {
        removeGreaterFromFile(arg);
    }

}
