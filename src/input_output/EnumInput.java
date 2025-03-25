package input_output;

import commands.ExecuteScript;
import commands.Exit;
import exceptions.IncorrectConstant;
import exceptions.RemoveOfTheNextSymbol;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A utility class for reading enum values from user input.
 */
public class EnumInput {

    final private static Scanner scanner = new Scanner(System.in);

    /**
     * Reads an enum value from the console.
     *
     * @param enumType The enum class type that is expected to be received from the console.
     * @param <T> The type of the enum.
     * @return One of the constant enum values.
     * @throws IncorrectConstant if the input isn't a valid value from the enum.
     */
    public static <T extends Enum<T>> T inputFromConsole(Class<T> enumType) {
        T[] enumConstants = enumType.getEnumConstants();
        ArrayList<String> enumValues = new ArrayList<>();
        for (T constant : enumConstants) {
            enumValues.add(constant.name());
        }
        DistributionOfTheOutputStream.print("Enter " + enumType.getSimpleName() + " " + enumValues.toString().trim().toLowerCase() + ": ");
        if (!scanner.hasNextLine()) {
            new Exit().execute("", "");
            throw new RemoveOfTheNextSymbol();
        }
        String input = scanner.nextLine().toUpperCase();
        T result = TransformToEnum(enumType, input);
        SavingAnEmergencyStop.addStringToFile(result.name());
        return result;
    }



    /**
     * Converts a string input to an enum value.
     *
     * @param enumType The enum class type to convert to.
     * @param input The string input to convert.
     * @param <T> The type of the enum.
     * @return The corresponding enum value if valid.
     * @throws IncorrectConstant if the input isn't a valid value from the enum.
     */
    public static <T extends Enum<T>> T TransformToEnum(Class<T> enumType, String input) {
        try {
            if (input == null || input.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            DistributionOfTheOutputStream.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        }
        return inputFromConsole(enumType);
    }
}