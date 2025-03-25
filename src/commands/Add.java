package commands;

import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;
import related_to_the_collection.StudyGroupFabric;

/**
 * Command for adding study groups to the collection from the console.
 */
public class Add implements Helpable, Command {

    /**
     * Adds a new study group to the collection.
     *
     * @param studyGroup the study group to be added
     */
    private static void addStudyGroup(StudyGroup studyGroup) {
        if (studyGroup != null) {
            Collection.getInstance().addElement(studyGroup);
        }
    }

    /**
     * Executes the add command.
     *
     * @param arg       the input arguments for creating a study group
     * @param inputMode the input mode (e.g., console or file)
     */
    @Override
    public void execute(String arg, String inputMode) {
        try {
            String[] inputSplit = arg.split(",");
            if (inputMode.equalsIgnoreCase("F") &&
                    Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length
                            != inputSplit.length) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, false);
            addStudyGroup(studyGroup);
            if (!inputMode.equalsIgnoreCase("F") || ExecuteScript.getExecuteScriptMode()) {
                DistributionOfTheOutputStream.println("Added successfully");
            }
        } catch (InsufficientNumberOfArguments e) {
            DistributionOfTheOutputStream.println(e.getMessage());
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Returns the help information for the command.
     *
     * @return a string describing the command usage
     */
    @Override
    public String getHelp() {
        return "Adds a new element to the collection.";
    }
}
