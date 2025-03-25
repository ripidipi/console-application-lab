package commands;

import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;
import related_to_the_collection.StudyGroupFabric;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Command that removes study groups greater than a given one from the collection.
 * This command removes all study groups in the collection that have greater values than
 * the specified study group based on comparison logic defined in the class.
 */
public class RemoveGreater implements Helpable, Command {

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
                    throw new InsufficientNumberOfArguments("RemoveGreater");
                }
                studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, true);
            }
            RemoveFromCollectionIs.remove(studyGroup, true);
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
    public String getHelp() {
        return "Removes all study groups from the collection that are greater than the specified study group.";
    }
}
