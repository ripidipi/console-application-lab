package commands;

import inputOutput.StudyGroupInput;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

public class Add implements Helpable {

    public static void add() {
        StudyGroup studyGroup = StudyGroupInput.Input();
        Collection.getInstance().addElement(studyGroup);
    }

    public String getHelp() {
        return "add";
    }
}
