package commands;

import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;
import related_to_the_collection.StudyGroupFabric;

import java.util.Objects;
import java.util.TreeSet;



/**
 * Command that updates a study group in the collection by its ID from console.
 */
public class Update implements Helpable, Command {

    public static void update(StudyGroup studyGroup) {
        try {
            replacementInTheCollection(studyGroup);
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        } catch (RuntimeException e) {
            DistributionOfTheOutputStream.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Replaces an existing study group in the collection with a new one.
     *
     * @param studyGroup The updated study group.
     */
    static void replacementInTheCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup sG : collection) {
            if (Objects.equals(sG.getId(), studyGroup.getId())) {
                Collection.getInstance().removeElement(sG);
                Collection.getInstance().addElement(studyGroup);
                break;
            }
        }
        DistributionOfTheOutputStream.println("Study group " + studyGroup.getId() + " is successfully updated");
    }


    @Override
    public void execute(String arg, String inputMode) {
        try{
            StudyGroup studyGroup;
            if (inputMode.equalsIgnoreCase("C")) {
                Integer id = IsElementWithId.validateId(arg);
                studyGroup = StudyGroupFabric.getStudyGroup("C",
                        new String[]{id.toString()}, false, true);
            } else {
                String[] inputSplit = arg.split(",");
                if (inputMode.equalsIgnoreCase("F") &&
                        Collection.formatStudyGroupToCSV(StudyGroup.getEmptyStudyGroup()).split(",").length
                                != inputSplit.length) {
                    throw new InsufficientNumberOfArguments("Update");
                }
                studyGroup = StudyGroupFabric.getStudyGroup(inputMode, inputSplit, false, true);
            }
            update(studyGroup);
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        }
    }

    @Override
    public String getHelp() {
        return "Updates an existing study group by its ID. You can update study " +
                "groups either through user input or by loading data from a file.";
    }
}
