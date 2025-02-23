package input;

import exeptions.EmptyLine;
import exeptions.ZeroValue;

import java.util.Scanner;

public class BasicDataTypesInput {

    private static final Scanner scanner = new Scanner(System.in);

    public static <T> T readInput(String name, Class<T> type) throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter " + name + ": ");
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
            return readInput(name, type);
        } catch (Exception e) {
            System.err.println("Invalid input. Try again");
            return readInput(name, type);
        }
        System.err.println("Unsupported type " + type);
        return type.cast("");
    }

    public static <T> T readInput(String name, Class<T> type, Boolean... checkers) throws EmptyLine, ZeroValue {
        try {
            System.out.println("Enter " + name + ": ");
            String input = scanner.nextLine();
            if (input.isEmpty() && checkers[0])
                throw new EmptyLine(name);
            if (input.isEmpty())
                return type.cast("");
            if (type == String.class) {
                return type.cast(input);
            } else if (type == Integer.class) {
                int value = Integer.parseInt(input);
                if (value <= 0 && checkers[1])
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Double.class) {
                double value = Double.parseDouble(input);
                if (value <= 0 && checkers[1])
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Float.class) {
                double value = Float.parseFloat(input);
                if (value <= 0 && checkers[1])
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == Long.class) {
                double value = Long.parseLong(input);
                if (value <= 0 && checkers[1])
                    throw new ZeroValue(name);
                return type.cast(value);
            } else if (type == java.time.LocalDateTime.class) {
                return type.cast(java.time.LocalDateTime.parse(input));
            }
        } catch (EmptyLine | ZeroValue e) {
            System.err.println(e.getMessage());
            return readInput(name, type);
        } catch (Exception e) {
            System.err.println("Invalid input. Try again");
            return readInput(name, type);
        }
        System.err.println("Unsupported type " + type);
        return type.cast("");
    }

}
