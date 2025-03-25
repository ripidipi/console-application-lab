package related_to_the_collection;

import exceptions.RemoveOfTheNextSymbol;

import java.util.Objects;

public interface StudyGroupFabric {

    static StudyGroup getStudyGroup(String inputMode, String[] input, boolean notAdd, boolean isId)
            throws RemoveOfTheNextSymbol {
        if (inputMode.equalsIgnoreCase("M")) {
            return StudyGroup.inputMixed(input, notAdd, isId);
        } else if (inputMode.equalsIgnoreCase("F")) {
            return StudyGroup.inputFromFile(input, notAdd);
        }
        if (input.length >= 1 && !input[0].isEmpty()) {
            return StudyGroup.input(input[0]);
        }
        return StudyGroup.input();
    }

}
