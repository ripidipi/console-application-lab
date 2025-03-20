package commands;

import exceptions.InsufficientNumberOfArguments;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import static commands.AddIfMax.isMax;

/**
 * Command that adds a study group to the collection only if it is the largest from file.
 */
public class AddIfMaxFromFile implements Command {

    /**
     * Adds a study group from file input if it is the maximum in the collection.
     *
     * @param input A string containing study group parameters separated by commas.
     * @throws InsufficientNumberOfArguments if the provided input has insufficient arguments.
     */
    public static void addStudyGroupIfMaxFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length != inputSplit.length) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.inputFromFile(inputSplit, true);
            if (studyGroup != null && isMax(studyGroup)) {
                Collection.getInstance().addElement(studyGroup);
                System.out.println("Study group added successfully from file.");
            } else {
                System.out.println("The study group is not the maximum and was not added.");
            }
        } catch (InsufficientNumberOfArguments e) {
            // file log
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg) {
        addStudyGroupIfMaxFromFile(arg);
    }

}
