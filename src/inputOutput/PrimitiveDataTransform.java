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
 * Class with static methods for reading basic data types from the user input or file.
 */
public class PrimitiveDataTransform {

    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Manages input for various basic data types with options for validation checks.
     * @param name The name of the expected input type.
     * @param type The class type of the expected input.
     * @param emptyLineCheck Whether to check for empty input.
     * @param zeroValueCheck Whether to check for numeric inputs less than or equal to zero.
     * @param dateInTheFutureCheck Whether to check if the date is in the future.
     * @param formatter The date-time formatter to use for parsing dates.
     * @param <T> The type of data expected.
     * @return The input data in the correct format.
     * @throws EmptyLine If the input is empty, and empty line check is enabled.
     * @throws ZeroValue If the input is numeric and less than or equal to zero, and zero value check is enabled.
     * @throws DataInTheFuture If the date is in the future, and date in the future check is enabled.
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

    /**
     * Overloaded method for basic input without advanced checks.
     * @param name The name of the expected input type.
     * @param type The class type of the expected input.
     * @param <T> The type of data expected.
     * @return The input data in the correct format.
     * @throws EmptyLine If the input is empty.
     * @throws ZeroValue If the input is numeric and less than or equal to zero.
     * @throws DataInTheFuture If the date is in the future.
     */
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

    /**
     * Reads input data from a file and converts it to the specified data type.
     * @param name The name of the expected input type.
     * @param input The string data from the file.
     * @param type The class type of the expected input.
     * @param <T> The type of data expected.
     * @return The converted data or null if the input is invalid.
     * @throws EmptyLine If the input is empty.
     * @throws ZeroValue If the input is numeric and less than or equal to zero.
     * @throws DataInTheFuture If the date is in the future.
     */
    public static <T> T inputFromFile(String name, String input, Class<T> type) throws EmptyLine, ZeroValue, DataInTheFuture {
        try {
            return transformToBasicType(name, type, true, true, true,
                    (Objects.equals(input, " ") ? "" : input), true, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"), false);
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
        }
        return null;
    }

    /**
     * Overloaded method for file-based input with additional validation options.
     * @param name The name of the expected input type.
     * @param input The string data from the file.
     * @param type The class type of the expected input.
     * @param emptyLineCheck Whether to check for empty input.
     * @param zeroValueCheck Whether to check for numeric inputs less than or equal to zero.
     * @param dateInTheFutureCheck Whether to check if the date is in the future.
     * @param formatter The date-time formatter to use for parsing dates.
     * @param muteMode Whether to suppress error messages.
     * @param <T> The type of data expected.
     * @return The converted data or null if the input is invalid.
     * @throws EmptyLine If the input is empty.
     * @throws ZeroValue If the input is numeric and less than or equal to zero.
     * @throws DataInTheFuture If the date is in the future.
     */
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

    /**
     * Helper method for applying a date-time formatter to the input string.
     * @param input The date-time input string.
     * @param formatter The formatter to apply.
     * @return The formatted LocalDateTime or LocalDate.
     */
    private static LocalDateTime applyFormater(String input, DateTimeFormatter formatter) {
        try {
            return LocalDateTime.parse(input, formatter);
        } catch (Exception e) {
            return LocalDate.parse(input, formatter).atStartOfDay();
        }
    }

    /**
     * Converts the input string to the specified data type with validation checks.
     * @param name The name of the expected input type.
     * @param type The class type of the expected input.
     * @param emptyLineCheck Whether to check for empty input.
     * @param zeroValueCheck Whether to check for numeric inputs less than or equal to zero.
     * @param dateInTheFutureCheck Whether to check if the date is in the future.
     * @param input The input string.
     * @param fileMode Whether the input is coming from a file.
     * @param formatter The date-time formatter to use.
     * @param muteMode Whether to suppress error messages.
     * @param <T> The type of data expected.
     * @return The converted data or null if invalid.
     * @throws EmptyLine If the input is empty.
     * @throws ZeroValue If the input is numeric and less than or equal to zero.
     * @throws DataInTheFuture If the date is in the future.
     */
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
            if (!muteMode)
                System.out.println("Invalid input. Try again");
        }
        if (fileMode) {
            return null;
        }
        return input(name, type, emptyLineCheck, zeroValueCheck, dateInTheFutureCheck, formatter);
    }

}
