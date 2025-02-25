package inputOutput;

import exeptions.IncorrectConstant;
import java.util.Scanner;

/**
 * class with static methods for enum value readings
 */
public class EnumInput {


    /**
     * Input manager for enums
     * @param enumType show type of enum the object that is expected to be received from console
     * @return one of the constant enum values
     * @param <T>  show type of enum expected to work with
     * @throws IncorrectConstant if input isn't a valid value from the enum
     */
    public static <T extends Enum<T>> T InputFromConsole(Class<T> enumType) throws IncorrectConstant {
        try {
            T[] enumConstants = enumType.getEnumConstants();
            StringBuilder enumValues = new StringBuilder();
            for (T constant : enumConstants) {
                enumValues.append(constant.name()).append(" ");
            }
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter " + enumType.getSimpleName() + " (" + enumValues.toString().trim().toLowerCase() + "): ");
            String input = scanner.nextLine().toUpperCase();
            return TransformToEnum(enumType, input);
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }

        return InputFromConsole(enumType);
    }

    public static <T extends Enum<T>> T TransformToEnum(Class<T> enumType, String input) throws IncorrectConstant {
        try {
            return Enum.valueOf(enumType, input.toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }
        return InputFromConsole(enumType);
    }


}
