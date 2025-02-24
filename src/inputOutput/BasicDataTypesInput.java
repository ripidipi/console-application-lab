package inputOutput;

import exeptions.BirthdayInTheFuture;
import exeptions.EmptyLine;
import exeptions.ZeroValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * class with static methods for basic types readings
 */
public class BasicDataTypesInput implements Inputable{

    private static final Scanner scanner = new Scanner(System.in);

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
    public static <T> T Input(String name, Class<T> type, Boolean EmptyLineCheck, Boolean ZeroValueCheck, Boolean BirthdayInTheFutureCheck) throws EmptyLine, ZeroValue, BirthdayInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return TransformToBasicType(name, type, EmptyLineCheck, ZeroValueCheck, BirthdayInTheFutureCheck, input);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return Input(name, type, EmptyLineCheck, ZeroValueCheck, BirthdayInTheFutureCheck);
    }

    public static <T> T Input(String name, Class<T> type) throws EmptyLine, ZeroValue, BirthdayInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return TransformToBasicType(name, type, true, true, true, input);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return Input(name, type);
    }

    public static <T> T TransformToBasicType(String name, Class<T> type, Boolean EmptyLineCheck, Boolean ZeroValueCheck, Boolean BirthdayInTheFutureCheck, String input) throws EmptyLine, ZeroValue, BirthdayInTheFuture {
        try {
            if (input.isEmpty() & EmptyLineCheck)
                throw new EmptyLine(name);
            if (input.isEmpty())
                return type.cast(null);
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0 & ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0 & ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                float value = Float.parseFloat(input);
                if (value <= 0 & ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                long value = Long.parseLong(input);
                if (value <= 0 & ZeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == LocalDateTime.class) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
                LocalDateTime date = LocalDate.parse(input, formatter).atStartOfDay();
                if (date.isAfter(LocalDateTime.now()) & BirthdayInTheFutureCheck) {
                    throw new BirthdayInTheFuture(name);
                }
                return type.cast(date);
            }
        } catch (EmptyLine | ZeroValue | BirthdayInTheFuture e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return Input(name, type, EmptyLineCheck, ZeroValueCheck, BirthdayInTheFutureCheck);
    }

}
