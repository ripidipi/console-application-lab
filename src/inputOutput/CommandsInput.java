package inputOutput;

import com.sun.tools.javac.Main;
import commands.Commands;
import exeptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;
import java.util.function.Function;

public class CommandsInput {

    private static boolean convertToEnum(String s) {
        try {
            Enum.valueOf(Commands.class, s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static void argumentValidator(String s) throws EmptyLine, ZeroValue{
        try {
            int arg = Integer.parseInt(s);
            if (arg <= 0) throw new ZeroValue("ID argument for remove");
        } catch (ZeroValue e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for command. Try again");
        }
    }

    public static Void isCommand(String[] inputSplit) {
        if (convertToEnum(inputSplit[0] + "_F")) {
            inputSplit[0] = inputSplit[0] + "_F";
        }
        if (convertToEnum(inputSplit[0])) {
            Commands command = Enum.valueOf(Commands.class, inputSplit[0].toUpperCase());
            if (inputSplit.length >= 2) {
                if (command == Commands.REMOVE_BY_ID) {
                    argumentValidator(inputSplit[1]);
                }
                command.execute(String.join(",", Arrays.copyOfRange(inputSplit, 1, inputSplit.length)));
            } else {
                command.execute("");
            }
        } else {
            throw new IncorrectCommand(inputSplit[0]);
        }
        return null;
    }

    public static void inputFromConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            isCommand(inputSplit);
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectCommand("Command").getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for command. Try again");
        }

    }

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
                        handler.apply(values);
                    } catch (Exception e) {
                        System.out.println("Invalid input. Try again");
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
