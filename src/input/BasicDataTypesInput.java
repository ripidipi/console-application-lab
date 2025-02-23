package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;
import java.util.Scanner;

/**
 * class with static methods for basic types readings
 */
public class BasicDataTypesInput implements Inputable{

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Input manager for some basic data types, which should be taken in my program
     * @param name names the type of information that is expected from the user
     * @param type take expected type of information
     * @return input data in right format
     * @param <T> show type of data expected to work with
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static <T> T Input(String name, Class<T> type) throws EmptyLine, ZeroValue {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            if (input.isEmpty())
                throw new EmptyLine(name);
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                double value = Float.parseFloat(input);
                if (value <= 0)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                double value = Long.parseLong(input);
                if (value <= 0)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == java.time.LocalDateTime.class) {
                return type.cast(java.time.LocalDateTime.parse(input));
            }
        } catch (EmptyLine | ZeroValue e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input. Try again");
        }
        System.err.println("Unsupported type " + type);
        return Input(name, type);
    }

    /**
     * Input manager for some basic data types, which should be taken in my program with option of setting exceptions checking
     * @param name names the type of information that is expected from the user
     * @param type take expected type of information
     * @param EmptyLineCheck responsible for using or not checking for EmptyLineCheck
     * @param ZeroValueCheck responsible for using or not checking for ZeroValueCheck
     * @return input data in right format
     * @param <T> show type of data expected to work with
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static <T> T Input(String name, Class<T> type, Boolean EmptyLineCheck, Boolean ZeroValueCheck) throws EmptyLine, ZeroValue {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            if (input.isEmpty() && EmptyLineCheck)
                throw new EmptyLine(name);
            if (input.isEmpty())
                return type.cast("");
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0 && ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0 && ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                double value = Float.parseFloat(input);
                if (value <= 0 && ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                double value = Long.parseLong(input);
                if (value <= 0 && ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == java.time.LocalDateTime.class) {
                return type.cast(java.time.LocalDateTime.parse(input));
            }
        } catch (EmptyLine | ZeroValue e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Invalid input. Try again");
        }
        System.err.println("Unsupported type " + type);
        return Input(name, type);
    }

}
