package inputOutput;

import commands.CommandsWithInput;
import commands.CommandsWithoutInput;
import exeptions.ConnectionToFileFailed;
import exeptions.EmptyLine;
import exeptions.IncorrectCommand;
import exeptions.ZeroValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CommandsInput implements Inputable{

    private static boolean isWith(String s) {
        try {
            Enum.valueOf(CommandsWithInput.class, s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static boolean isWithout(String s) {
        try {
            Enum.valueOf(CommandsWithoutInput.class, s.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    private static Integer argumentValidator(String s) throws EmptyLine, ZeroValue{
        try {
            int arg = Integer.parseInt(s);
            if (arg <= 0) throw new ZeroValue("ID argument for remove");
            return arg;
        } catch (ZeroValue e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for command. Try again");
        }
        return null;
    }

    public static void InputFromConsole() {
        try {
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            if (isWith(inputSplit[0])) {
                CommandsWithInput command = Enum.valueOf(CommandsWithInput.class, inputSplit[0].toUpperCase());
                Integer i = argumentValidator(inputSplit[1]);
                if (i == null & command == CommandsWithInput.REMOVE_BY_ID) {return;}
                command.execute(inputSplit[1]);
            } else if(isWithout(inputSplit[0])) {
                CommandsWithoutInput command = Enum.valueOf(CommandsWithoutInput.class, inputSplit[0].toUpperCase());
                command.execute();
            } else {
                throw new IncorrectCommand(inputSplit[0]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectCommand("Command").getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for command. Try again");
        }

    }

    public static void InputFromFile(String filePath) {
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
                }
            } catch (FileNotFoundException e) {
                System.out.println("Couldn't open the file " + fileName);
                return;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
