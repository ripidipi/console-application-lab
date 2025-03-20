package commands;


import exceptions.RemoveOfTheNextSymbol;
import input_output.Logging;
import input_output.PrimitiveDataTransform;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.TreeSet;

/**
 * Command that adds a study group to the collection only if it is the largest from console.
 */
public class AddIfMax implements Helpable, Command {

    /**
     * Adds a new study group if it is the maximum in the collection.
     */
    public static void addStudyGroupIfMax() {
        try {
            Integer id = PrimitiveDataTransform.input("id", Integer.class);
            StudyGroup studyGroup = StudyGroup.input(id.toString());
            if (isMax(studyGroup)) {
                Collection.getInstance().addElement(studyGroup);
                System.out.println("Study group added successfully.");
            } else {
                System.out.println("The study group is not the maximum and was not added.");
            }
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Checks if the given study group is the maximum in the collection.
     *
     * @param studyGroup The study group to compare.
     * @return {@code true} if the study group is the largest; {@code false} otherwise.
     */
    static boolean isMax(StudyGroup studyGroup) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        if (collection.isEmpty()) {
            return true; // If collection is empty, any element is the max.
        }
        StudyGroup maxStudyGroup = collection.last();
        return maxStudyGroup.compareTo(studyGroup) < 0;
    }

    @Override
    public void execute(String arg) {
        addStudyGroupIfMax();
    }

    @Override
    public String getHelp() {
        return "Adds a new element to the collection if the element larger than the maximum in collection.";
    }
}


