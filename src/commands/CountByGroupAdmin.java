package commands;

import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.Person;
import related_to_the_collection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

/**
 * Command that counts the number of study groups where a specified person is the admin from console.
 */
public class CountByGroupAdmin implements Helpable, Command{

    /**
     * Counts the number of study groups where the user-specified person is the admin.
     */
    public static void countByGroupAdmin() {
        try {
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            Person person = Person.input();
            int adminCounter = 0;
            for (StudyGroup studyGroup : studyGroups) {
                if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                    adminCounter++;
                }
            }
            DistributionOfTheOutputStream.println("The person is an admin in " + adminCounter + " groups.");
        } catch (RemoveOfTheNextSymbol e) {
            DistributionOfTheOutputStream.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg, String inputMode) {
        countByGroupAdmin();
    }

    @Override
    public String getHelp() {
        return "Counts the number of study groups where the specified person is the admin.";
    }
}
