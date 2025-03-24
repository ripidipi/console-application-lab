package commands;

import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that removes a study group by its ID.
 */
public class RemoveById implements Helpable, Command {

    /**
     * Removes a study group with the given ID from the collection.
     *
     * @param idInput The ID of the study group to remove.
     */
    public static void removeById(String idInput) {
        try {
            int id = IsElementWithId.validateId(idInput);
            TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
            Iterator<StudyGroup> iterator = collection.iterator();

            while (iterator.hasNext()) {
                StudyGroup studyGroup = iterator.next();
                if (Objects.equals(studyGroup.getId(), id)) {
                    iterator.remove();
                    break;
                }
            }
            DistributionOfTheOutputStream.println("Object has been removed.");
        } catch (RuntimeException e) {
            DistributionOfTheOutputStream.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg, String inputMode) {
        removeById(arg);
    }

    @Override
    public String getHelp() {
        return "Removes a study group by its ID.";
    }
}
