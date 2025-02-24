package inputOutput;

import commands.Commands;
import exeptions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CommandsInput implements Inputable{

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

    private static void isCommand(String[] inputSplit) {

        if (convertToEnum(inputSplit[0])) {
            Commands command = Enum.valueOf(Commands.class, inputSplit[0].toUpperCase());
            if (inputSplit.length > 2) {
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

    public static void inputFromFile(String filePath) {
        try {
            String fileName;
            if (filePath.isEmpty()) {
                fileName = System.getenv("CSV_FILE_NAME");
            } else {
                fileName = filePath;
            }
            if (fileName == null || fileName.isEmpty()) {
                throw new ConnectionToFileFailed("Connection to environment path failed " + fileName);
            }
            File file = new File(fileName);
            if (!file.exists() || !file.isFile()) {
                throw new ConnectionToFileFailed("File path doesn't found " + fileName);
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] values = line.split(",");
                    values[0] = values[0] + "_F";
                    isCommand(values);
                }
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open the file " + fileName);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
