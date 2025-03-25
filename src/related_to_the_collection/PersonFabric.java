package related_to_the_collection;

import exceptions.RemoveOfTheNextSymbol;

/**
 * Interface for creating {@link Person} objects based on different input modes.
 * The input modes determine how a {@link Person} is constructed (from mixed input, file input, or interactive input).
 */
public interface PersonFabric {

    /**
     * Factory method to create a {@link Person} object based on the provided input string and mode.
     * The method processes the input differently based on the mode specified:
     * <ul>
     * <li>"M" mode processes the input as mixed user input.</li>
     * <li>"F" mode processes the input as file-based input.</li>
     * <li>Default mode processes the input interactively.</li>
     * </ul>
     *
     * @param input the input string to create a {@link Person} object.
     * @param inputMode the mode that determines how the input is processed:
     *                  "M" for mixed input, "F" for file input, or default for interactive input.
     * @return a {@link Person} object created based on the input and mode.
     * @throws RemoveOfTheNextSymbol if an unexpected or invalid symbol is found in the input.
     */
    static Person getPerson(String input, String inputMode) throws RemoveOfTheNextSymbol {
        String[] inputSplit = input.split(",");
        if (inputMode.equalsIgnoreCase("M")) {
            return Person.inputMixed(inputSplit);
        } else if (inputMode.equalsIgnoreCase("F")) {
            return Person.inputFromFile(inputSplit[0], inputSplit[1], inputSplit[2], inputSplit[3]);
        }
        return Person.input();
    }
}
