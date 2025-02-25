package inputOutput;

import exeptions.DataInTheFuture;
import exeptions.EmptyLine;
import exeptions.ZeroValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * class with static methods for basic types readings
 */
public class BasicDataTypesInput {

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
    public static <T> T Input(String name, Class<T> type, Boolean EmptyLineCheck, Boolean ZeroValueCheck,
                              Boolean BirthdayInTheFutureCheck, DateTimeFormatter formatter)
                                throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return TransformToBasicType(name, type, EmptyLineCheck, ZeroValueCheck, BirthdayInTheFutureCheck, input,
                    false, formatter);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return Input(name, type, EmptyLineCheck, ZeroValueCheck, BirthdayInTheFutureCheck, formatter);
    }

    public static <T> T Input(String name, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return TransformToBasicType(name, type, true, true, true, input,
                                false, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return Input(name, type);
    }

    public static <T> T InputFromFile(String name, String input, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return TransformToBasicType(name, type, true, true, true, input,
                                true, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    public static <T> T InputFromFile(String name, String input, Class<T> type, Boolean EmptyLineCheck,
                                      Boolean ZeroValueCheck, Boolean DateInTheFutureCheck,
                                      DateTimeFormatter formatter) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return TransformToBasicType(name, type, EmptyLineCheck, ZeroValueCheck, DateInTheFutureCheck, input, true, formatter);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    public static <T> T TransformToBasicType(String name, Class<T> type, Boolean emptyLineCheck, Boolean zeroValueCheck,
                                             Boolean dateInTheFutureCheck, String input, Boolean fileMode,
                                             DateTimeFormatter formatter) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            if (input.isEmpty() & emptyLineCheck)
                throw new EmptyLine(name);
            if (input.isEmpty())
                return type.cast(null);
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                float value = Float.parseFloat(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                long value = Long.parseLong(input);
                if (value <= 0 & zeroValueCheck)
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == LocalDateTime.class) {
                LocalDateTime date = LocalDate.parse(input, formatter).atStartOfDay();
                if (date.isAfter(LocalDateTime.now()) & dateInTheFutureCheck) {
                    throw new DataInTheFuture(name);
                }
                return type.cast(date);
            }
        } catch (EmptyLine | ZeroValue | DataInTheFuture e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        if (fileMode) {
            return null;
        }
        return Input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }

}
