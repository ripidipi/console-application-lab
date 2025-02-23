package input;

import exeptions.IncorrectConstant;

import java.util.Scanner;

public class EnumInput {

    /**
     * Input manager for enums
     * @param enumType show type of enum the object that is expected to be received from console
     * @return one of the constant enum values
     * @param <T>  show type of enum expected to work with
     * @throws IncorrectConstant if input isn't a valid value from the enum
     */
    public static <T extends Enum<T>> T Input(Class<T> enumType) throws IncorrectConstant {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter " + enumType.getSimpleName() + ":");
            String e = scanner.nextLine().toUpperCase();
            return Enum.valueOf(enumType, e);
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        }
        return Input(enumType);
    }
}
