package input;

import exeptions.IncorrectConstant;

import java.util.Scanner;

public class EnumInput {
    public static <T extends Enum<T>> T Input(Class<T> enumType) {
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
