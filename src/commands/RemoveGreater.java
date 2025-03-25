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
 * Command that removes study groups greater than a given one from the collection from console.
 */
public class RemoveGreater implements Helpable, Command {

    @Override
    public void execute(String arg, String inputMode) {
        try{
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
                    throw new InsufficientNumberOfArguments("Add");
                }
                studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, true);
            }
            RemoveFromCollectionIs.remove(studyGroup, true);
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        }
    }

    @Override
    public String getHelp() {
        return "Removes all study groups greater than the specified one.";
    }
}
