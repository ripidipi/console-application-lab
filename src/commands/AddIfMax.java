package commands;

import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;
import related_to_the_collection.StudyGroupFabric;

import java.util.TreeSet;

/**
 * Command that adds a study group to the collection only if it is the largest from the console.
 */
public class AddIfMax implements Helpable, Command {

    /**
     * Adds a new study group if it is the maximum in the collection.
     *
     * @param studyGroup the study group to be checked and potentially added
     */
    private static void addStudyGroupIfMax(StudyGroup studyGroup) {
        if (studyGroup != null && isMax(studyGroup)) {
            Collection.getInstance().addElement(studyGroup);
            DistributionOfTheOutputStream.println("Study group added successfully.");
        } else {
            DistributionOfTheOutputStream.println("The study group is not the maximum and was not added.");
        }
    }

    /**
     * Checks if the given study group is the maximum in the collection.
     *
     * @param studyGroup the study group to compare
     * @return {@code true} if the study group is the largest; {@code false} otherwise
     */
    private static boolean isMax(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        if (collection.isEmpty()) {
            return true;
        }
        StudyGroup maxStudyGroup = collection.last();
        return maxStudyGroup.compareTo(studyGroup) < 0;
    }

    /**
     * Executes the AddIfMax command.
     *
     * @param arg       the input arguments for creating a study group
     * @param inputMode the input mode (e.g., console or file)
     */
    @Override
    public void execute(String arg, String inputMode) {
        try {
            StudyGroup studyGroup;
            if (inputMode.equalsIgnoreCase("C")) {
                Integer id = PrimitiveDataTransform.input("id", Integer.class);
                studyGroup = StudyGroupFabric.getStudyGroup("C",
                        new String[]{id.toString()}, false, true);
            } else {
                String[] inputSplit = arg.split(",");
                if (inputMode.equalsIgnoreCase("F") &&
                        Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length
                                != inputSplit.length) {
                    throw new InsufficientNumberOfArguments("AddIfMax");
                }
                studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, true);
            }
            addStudyGroupIfMax(studyGroup);
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
        return "Adds a new element to the collection if the element is larger than the maximum in the collection.";
    }
}
