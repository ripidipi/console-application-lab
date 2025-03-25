package commands;


import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;
import related_to_the_collection.StudyGroupFabric;

import java.util.Objects;

/**
 * Command for adding study groups to the collection from console.
 */
public class Add implements Helpable, Command {

    /**
     * Adds a new study group based on user input.
     */
    public static void addStudyGroup(StudyGroup studyGroup) {
        try {
            Collection.getInstance().addElement(studyGroup);
            if (studyGroup != null) {
                Collection.getInstance().addElement(studyGroup);
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

    @Override
    public void execute(String arg, String inputMode) {
        String[] inputSplit = arg.split(",");
        if (inputMode.equalsIgnoreCase("F") &&
                Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length
                        != inputSplit.length) {
            throw new InsufficientNumberOfArguments("Add");
        }
        StudyGroup studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, false);
        addStudyGroup(studyGroup);
    }



    @Override
    public String getHelp() {
        return "Adds a new element to the collection.";
    }
}
