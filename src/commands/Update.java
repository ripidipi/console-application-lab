package commands;

import exceptions.RemoveOfTheNextSymbol;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;



/**
 * Command that updates a study group in the collection by its ID from console.
 */
public class Update implements Helpable, Command {

    /**
     * Updates a study group based on user input.
     *
     * @param id The ID of the study group to be updated.
     */
    public static void update(String id) {
        try {
            Integer parsedId = IsElementWithId.validateId(id);
            if (parsedId == null) {
                System.out.println("Invalid input. Please provide a valid ID.");
                return;
            }
            StudyGroup studyGroup = StudyGroup.input(id);
            replacementInTheCollection(studyGroup);
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
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
        System.out.println("Study group " + studyGroup.getId() + " is successfully updated");
    }


    @Override
    public void execute(String arg) {
        update(arg);
    }

    @Override
    public String getHelp() {
        return "Updates an existing study group by its ID. You can update study " +
                "groups either through user input or by loading data from a file.";
    }
}
