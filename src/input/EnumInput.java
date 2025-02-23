package input;

import exeptions.IncorrectConstant;
import java.util.Scanner;

/**
 * class with static methods for enum value readings
 */
public class EnumInput implements Inputable{

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
            System.out.print("Enter " + enumType.getSimpleName() + ": ");
            String s = scanner.nextLine().toUpperCase();
            return Enum.valueOf(enumType, s);
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant(enumType.getSimpleName()).getMessage());
        } catch (Exception e) {
            System.out.println("Invalid data. Try again");
        }

        return Input(enumType);
    }
}
