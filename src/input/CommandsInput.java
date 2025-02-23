package input;

import commands.CommandsWithInput;
import commands.CommandsWithoutInput;
import exeptions.IncorrectCommand;
import exeptions.IncorrectConstant;

import java.util.Arrays;
import java.util.Scanner;

public class CommandsInput implements Inputable{

    private static final Scanner scanner = new Scanner(System.in);

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

    public static void Input() {
        try {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            if (isWith(inputSplit[0])) {
                CommandsWithInput command = Enum.valueOf(CommandsWithInput.class, inputSplit[0].toUpperCase());
                command.execute(String.join(" ", Arrays.copyOfRange(inputSplit, 1, inputSplit.length)));
            } else if(isWithout(inputSplit[0])) {
                CommandsWithoutInput command = Enum.valueOf(CommandsWithoutInput.class, inputSplit[0].toUpperCase());
                command.execute();
            } else {
                throw new IncorrectCommand(inputSplit[0]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectConstant("Command").getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
