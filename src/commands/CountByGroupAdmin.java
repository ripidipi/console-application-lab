package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.Person;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

public class CountByGroupAdmin {

    public static void countByGroupAdmin() {
        try {
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            Person person = Person.Input();
            int adminCounter = 0;
            for (StudyGroup studyGroup : studyGroups) {
                if (Objects.equals(studyGroup.getGroupAdmin(), person)) {
                    adminCounter++;
                }
            }
            System.out.println("Person is admin in " + adminCounter + " groups");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
