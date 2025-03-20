package commands;

import exceptions.RemoveOfTheNextSymbol;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Command that removes study groups lower than a given one from the collection from console.
 */
public class RemoveLower implements Helpable, Command {

    /**
     * Removes study groups lower than the specified one, based on user input.
     */
    public static void removeLower() {
        try {
            Integer id = PrimitiveDataTransform.input("id", Integer.class);
            StudyGroup studyGroup = StudyGroup.input(id.toString());
            removeLowerFromCollection(studyGroup);
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Removes study groups lower than the given one from the collection.
     *
     * @param studyGroup The study group to compare with.
     */
    static void removeLowerFromCollection(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        ArrayList<StudyGroup> toRemove = new ArrayList<>();
        for (StudyGroup sG : collection) {
            if(sG.compareTo(studyGroup) < 0) {
                toRemove.add(sG);
            }
        }
        for (StudyGroup sG : toRemove) {
            collection.remove(sG);
        }
    }

    @Override
    public void execute(String arg) {
        removeLower();
    }

    @Override
    public String getHelp() {
        return "Removes all study groups lower than the specified one.";
    }
}
