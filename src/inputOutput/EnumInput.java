package inputOutput;

import exeptions.IncorrectConstant;
import java.util.Scanner;

/**
 * Utility class with static methods for reading enum values from the console.
 */
public class EnumInput {

    /**
     * Manager for reading enums from the console.
     * Prompts the user to enter a value that corresponds to a valid enum constant.
     * If the input is invalid, throws an IncorrectConstant exception and prompts the user again.
     *
     * @param enumType The enum class type that defines the valid values to be entered.
     * @param <T> The type of the enum to be used.
     * @return The enum constant corresponding to the user's input.
     * @throws IncorrectConstant If the input is not a valid value from the enum.
     */
    public static <T extends Enum<T>> T InputFromConsole(Class<T> enumType) throws IncorrectConstant {
        try {
            T[] enumConstants = enumType.getEnumConstants();
            StringBuilder enumValues = new StringBuilder();
            for (T constant : enumConstants) {
                enumValues.append(constant.name()).append(" ");
            }
            try (Scanner scanner = new Scanner(System.in)) {
                System.out.print("Enter " + enumType.getSimpleName() + " (" +
                        enumValues.toString().trim().toLowerCase() + "): ");
                String input = scanner.nextLine().toUpperCase();
                return TransformToEnum(enumType, input);
            }
        } catch (Exception e) {
            System.out.println("Invalid data. Try again.");
        }

        return InputFromConsole(enumType);
    }

    /**
     * Converts the input string to the corresponding enum constant.
     * If the input is invalid, throws an IncorrectConstant exception.
     *
     * @param enumType The enum class type to transform the input string into.
     * @param input The string input to be transformed into an enum constant.
     * @param <T> The type of the enum to be used.
     * @return The enum constant corresponding to the input string.
     * @throws IncorrectConstant If the input is not a valid enum constant.
     */
    public static <T extends Enum<T>> T TransformToEnum(Class<T> enumType, String input) throws IncorrectConstant {
        try {
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        } catch (Exception e) {
            System.out.println("Invalid data. Try again.");
        }
        return InputFromConsole(enumType);
    }
}
