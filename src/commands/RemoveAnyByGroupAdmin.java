package commands;

import exeptions.InsufficientNumberOfArguments;
import relatedToTheCollection.Collection;
import relatedToTheCollection.Person;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

public class RemoveAnyByGroupAdmin {

    public static void removeAnyByGroupAdmin() {
        try {
            Person person = Person.Input();
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            for (StudyGroup studyGroup : studyGroups) {
                if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                    studyGroups.remove(studyGroup);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void removeAnyByGroupAdminFromFile(String input) {
        try {
            String[] inputSplit = input.split(",");
            if (inputSplit.length != 4) {
                throw new InsufficientNumberOfArguments("");
            }
            Person person = Person.InputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            for (StudyGroup studyGroup : studyGroups) {
                if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                    studyGroups.remove(studyGroup);
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
