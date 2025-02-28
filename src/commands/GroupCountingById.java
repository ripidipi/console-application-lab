package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;
import java.util.*;

public class GroupCountingById {

    public static void groupCountingById() {
        try {
            TreeSet<StudyGroup> studyGroups = Collection.getInstance().getCollection();
            int setSize = studyGroups.size();
            if (setSize == 0) {
                System.out.println("The collection is empty.");
                return;
            }

            int groupCount = (int) Math.ceil(Math.sqrt(setSize));
            List<List<StudyGroup>> groups = new ArrayList<>(groupCount);
            for (int i = 0; i < groupCount; i++) {
                groups.add(new ArrayList<>());
            }

            int index = 0;
            for (StudyGroup obj : studyGroups) {
                groups.get(index / groupCount).add(obj);
                index++;
            }
            groups.removeIf(List::isEmpty);
            System.out.println("There are " + groups.size() + " groups");
            int lastID = 1;
            for (List<StudyGroup> group : groups) {
                if (!group.isEmpty()) {
                    int groupSize = group.size();
                    int endID = group.getLast().getId();
                    System.out.print("In id range " + lastID + "-" + (endID + 1) + " - " + groupSize + " elements, ");
                    lastID = endID + 1;
                }
            }
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
