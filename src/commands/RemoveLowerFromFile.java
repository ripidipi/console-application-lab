package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import static commands.RemoveLower.removeLowerFromCollection;

/**
 * Command that removes study groups lower than a given one from the collection from file.
 */
public class RemoveLowerFromFile implements Command {

    /**
     * Removes study groups lower than the specified one, based on input from a file.
     *
     * @param input The input string containing the data to create the study group.
     */
    public static void removeLowerFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length != inputSplit.length) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            if (studyGroup == null) return;
            removeLowerFromCollection(studyGroup);
        } catch (InsufficientNumberOfArguments e) {
            // file out
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }

    }

    @Override
    public void execute(String arg) {
        removeLowerFromFile(arg);
    }

}
