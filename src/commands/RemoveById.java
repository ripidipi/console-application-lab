package commands;

import relatedToTheCollection.Collection;
import relatedToTheCollection.StudyGroup;

import java.util.Objects;
import java.util.TreeSet;

public class RemoveById implements Helpable{

    public static void removeById(Integer id) {
        TreeSet<StudyGroup> collection = Collection.getInstance().getCollection();
        for (StudyGroup sG : collection) {
            if(Objects.equals(sG.getId(), id)) {
                Collection.getInstance().removeElement(sG);
                break;
            }
        }
    }

    public String getHelp() {
        return "Remove a study group by id";
    }

}
