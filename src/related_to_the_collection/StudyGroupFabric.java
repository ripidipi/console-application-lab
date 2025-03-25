package related_to_the_collection;

import exceptions.RemoveOfTheNextSymbol;

/**
 * Interface providing a factory method to create instances of {@link StudyGroup}.
 * The method determines the mode of input (mixed, file, or default) and creates the appropriate StudyGroup object.
 */
public interface StudyGroupFabric {

    /**
     * Factory method to create a StudyGroup based on the input mode.
     *
     * @param inputMode The input mode, which can be "M" for mixed input, "F" for file input, or default for standard input.
     * @param input The input data, which can vary based on the input mode.
     * @param notAdd Flag to indicate whether the ID should not be added to the list of IDs.
     * @param isId Flag to specify if the ID should be considered during the input.
     * @return A StudyGroup object created based on the provided input and mode.
     * @throws RemoveOfTheNextSymbol If an error occurs while processing the input.
     */
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
