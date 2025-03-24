package commands;

import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.Person;
import related_to_the_collection.StudyGroup;

import java.util.Iterator;
import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that removes a study group by its group admin from console.
 */
public class RemoveAnyByGroupAdmin implements Helpable, Command{

    /**
     * Removes the first study group with the given group admin from the collection.
     *
     * @param person The group admin whose group needs to be removed.
     */
    static void removeGroupByAdmin(Person person) {
        TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
        Iterator<StudyGroup> iterator = studyGroups.iterator();
        while (iterator.hasNext()) {
            StudyGroup studyGroup = iterator.next();
            if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                iterator.remove();
                break;
            }
        }
    }

    /**
     * Removes the first study group with the given group admin from the collection based on user input.
     */
    public static void removeAnyByGroupAdmin() {
        try {
            Person person = Person.input();
            removeGroupByAdmin(person);
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg, String inputMode) {
        removeAnyByGroupAdmin();
    }

    @Override
    public String getHelp() {
        return "Removes the first study group with the specified group admin.";
    }
}
