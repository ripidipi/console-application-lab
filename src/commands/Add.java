package commands;


import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

public class Add implements Helpable {

    public static void add() {
        StudyGroup studyGroup = StudyGroup.Input();
        Collection.getInstance().addElement(studyGroup);
    }

    public static void addFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 11) {
                throw new InsufficientNumberOfArguments("Add");
            }
            StudyGroup studyGroup = StudyGroup.InputFromFile(inputSplit, false);
            if (studyGroup != null)
                Collection.getInstance().addElement(studyGroup);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getHelp() {
        return "add";
    }
}
