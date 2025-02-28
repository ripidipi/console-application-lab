package inputOutput;

import exeptions.DataInTheFuture;
import exeptions.EmptyLine;
import exeptions.ZeroValue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;

/**
 * class with static methods for basic types readings
 */
public class PrimitiveDataTransform {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Input manager for some basic data types, which should be taken in my program with option of setting exceptions checking
     * @param name names the type of information that is expected from the user
     * @param type take expected type of information
     * @param emptyLineCheck responsible for using or not checking for emptyLineCheck
     * @param zeroValueCheck responsible for using or not checking for ZeroValueCheck
     * @return input data in right format
     * @param <T> show type of data expected to work with
     * @throws EmptyLine for empty gaps, if it incorrect format for it
     * @throws ZeroValue for numeric gaps <= 0, if it incorrect format for it
     */
    public static <T> T input(String name, Class<T> type, Boolean emptyLineCheck, Boolean zeroValueCheck,
                              Boolean dateInTheFutureCheck, DateTimeFormatter formatter)
                                throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return transformToBasicType(name, type, emptyLineCheck,
                    zeroValueCheck, dateInTheFutureCheck, input,
                    false, formatter, false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }

    public static <T> T input(String name, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            System.out.print("Enter " + name + ": ");
            String input = scanner.nextLine();
            return transformToBasicType(name, type, true, true, true, input,
                                false, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"), false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return input(name, type);
    }

    public static <T> T inputFromFile(String name, String input, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return transformToBasicType(name, type, true, true, true,
                    (Objects.equals(input, " ") ? "" : input), true, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"), false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    public static <T> T inputFromFile(String name, String input, Class<T> type, Boolean emptyLineCheck,
                                      Boolean zeroValueCheck, Boolean dateInTheFutureCheck,
                                      DateTimeFormatter formatter, Boolean muteMode) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return transformToBasicType(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, (Objects.equals(input,
                                    " ") ? "" : input), true, formatter, muteMode);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    private static LocalDateTime applyFormater(String input, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(input, formatter);
        }catch (Exception e) {
            return LocalDate.parse(input, formatter).atStartOfDay();
        }
    }

    public static <T> T transformToBasicType(String name, Class<T> type, Boolean emptyLineCheck, Boolean zeroValueCheck,
                                             Boolean dateInTheFutureCheck, String input, Boolean fileMode,
                                             DateTimeFormatter formatter, Boolean muteMode) throws EmptyLine, ZeroValue, DataInTheFuture {
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
                LocalDateTime date = applyFormater(input, formatter);
                if (date.isAfter(LocalDateTime.now()) & dateInTheFutureCheck) {
                    throw new DataInTheFuture(name);
                }
                return type.cast(date);
            }
        } catch (EmptyLine | ZeroValue | DataInTheFuture e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            if(!muteMode)
                System.out.println("Invalid input. Try again");
        }
        if (fileMode) {
            return null;
        }
        return input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }

}
