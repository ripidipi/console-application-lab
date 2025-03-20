package commands;


import exceptions.RemoveOfTheNextSymbol;
import input_output.Logging;
import related_to_the_collection.Collection;
import related_to_the_collection.StudyGroup;

import java.util.Objects;

/**
 * Command for adding study groups to the collection from console.
 */
public class Add implements Helpable, Command {

    /**
     * Adds a new study group based on user input.
     */
    private static void addStudyGroup(StudyGroup inputStudyGroup) {
        try {
            StudyGroup studyGroup = Objects.requireNonNullElseGet(inputStudyGroup, StudyGroup::input);
            Collection.getInstance().addElement(studyGroup);
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    @Override
    public void execute(String arg) {
        addStudyGroup(null);
    }

    public void execute(StudyGroup studyGroup) {
        addStudyGroup(studyGroup);
    }

    @Override
    public String getHelp() {
        return "Adds a new element to the collection.";
    }
}
