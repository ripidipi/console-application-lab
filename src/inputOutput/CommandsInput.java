package inputOutput;

import commands.Commands;
import exeptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Class responsible for handling command inputs either from the console or a file.
 * Provides methods to process user commands and validate input.
 */
public class CommandsInput {

    /**
     * Tries to convert a string to a valid command enum from the Commands enum.
     *
     * @param s The string to check for validity.
     * @return True if the string corresponds to a valid command, false otherwise.
     */
    private static boolean convertToEnum(String s) {
        try {
            Enum.valueOf(Commands.class, s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Checks if the provided input corresponds to a valid command and executes it.
     * If the command is not valid, throws an IncorrectCommand exception.
     *
     * @param inputSplit Array of strings representing the command and its arguments.
     */
    public static Void isCommand(String[] inputSplit) {
        try {
            if (convertToEnum(inputSplit[0])) {
                Commands command = Enum.valueOf(Commands.class, inputSplit[0].toUpperCase());
                if (inputSplit.length >= 2) {
                    command.execute(String.join(",", Arrays.copyOfRange(inputSplit, 1, inputSplit.length)));
                } else {
                    command.execute("");
                }
            } else {
                throw new IncorrectCommand(inputSplit[0]);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Reads input from the console and processes the command.
     * Splits the input into arguments and checks if it matches a valid command.
     */
    public static void inputFromConsole() {
        try (Scanner scanner = new Scanner(System.in)) {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            isCommand(inputSplit);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input for command. Try again.");
        }
    }

    /**
     * Reads commands from a file and processes them.
     * Each line in the file should represent a command with arguments, if any.
     *
     * @param filePath Path to the file containing the commands.
     * @param handler  A handler function to process the command from the file.
     */
    public static void inputFromFile(String filePath, Function<String[], Void> handler) {
        try {
            if (filePath == null || filePath.isEmpty()) {
                throw new ConnectionToFileFailed("Connection to environment path failed " + filePath);
            }
            File file = new File(filePath);
            if (!file.exists() || !file.isFile()) {
                throw new ConnectionToFileFailed("File path doesn't found " + filePath);
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    try {
                        String line = scanner.nextLine();
                        String[] values = line.split(",");
                        if (convertToEnum(values[0] + "_F")) {
                            values[0] = values[0] + "_F";
                        }
                        handler.apply(values);
                    } catch (Exception e) {
                        System.out.println("Invalid input. Try again.");
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open the file " + filePath);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
