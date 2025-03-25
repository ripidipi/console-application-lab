package commands;

import exceptions.InsufficientNumberOfArguments;
import exceptions.RemoveOfTheNextSymbol;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.Person;
import related_to_the_collection.PersonFabric;
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
    public static void countByGroupAdmin(Person person) {
        TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
        int adminCounter = 0;
        for (StudyGroup studyGroup : studyGroups) {
            if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                adminCounter++;
            }
        }
        DistributionOfTheOutputStream.println("The person is an admin in " + adminCounter + " groups.");
    }

    @Override
    public void execute(String arg, String inputMode) {
        try {
            if (inputMode.equalsIgnoreCase("F")) {
                String[] inputSplit = arg.split(",");
                if (inputSplit.length != 4) {
                    throw new InsufficientNumberOfArguments("CountByGroupAdmin");
                }
            }
            Person person = PersonFabric.getPerson(arg, inputMode);
            countByGroupAdmin(person);
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
        return "Counts the number of study groups where the specified person is the admin.";
    }
}
