package input_output;

import commands.Commands;
import commands.ExecuteScript;
import commands.Exit;
import exceptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

/**
 * Handles command input from the console or a file.
 */
public class CommandsInput {

    /**
     * Checks if a given string can be converted to an enum value of {@link Commands}.
     *
     * @param s the string to check
     * @return true if the string corresponds to a valid command, false otherwise
     */
    private static boolean convertToEnum(String s) {
        try {
            if (s == null || s.trim().isEmpty()) {
                throw new IllegalArgumentException();
            }
            Enum.valueOf(Commands.class, s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Processes and executes a command if it is valid.
     *
     * @param inputSplit an array containing the command and its arguments
     * @return null
     */
    public static Void isCommand(String[] inputSplit) {
        try {
            if (inputSplit.length!=0 && convertToEnum(inputSplit[0])) {
                Commands command = Enum.valueOf(Commands.class, inputSplit[0].toUpperCase());
                SavingAnEmergencyStop.addStringToFile(command.name());
                if (inputSplit.length >= 2) {
                    command.execute(String.join(",", Arrays.copyOfRange(inputSplit, 1, inputSplit.length)));
                } else {
                    command.execute("");
                }
                SavingAnEmergencyStop.clearFile();
            } else {
                throw new IncorrectCommand(inputSplit[0]);
            }
        } catch (IncorrectCommand e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
        return null;
    }

    /**
     * Reads and processes command input from the console.
     */
    public static void inputFromConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextLine()) {
                new Exit().execute("");
                throw new RemoveOfTheNextSymbol();
            }
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");

            isCommand(inputSplit);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input for command. Try again");
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Reads and processes command input from a file.
     *
     * @param filePath the path to the input file
     * @param handler  a function to process each line of input
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
                        Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
                    }
                }
            } catch (FileNotFoundException e) {
                Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
            }
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }
}
