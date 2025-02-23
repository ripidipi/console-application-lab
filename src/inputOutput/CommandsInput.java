package inputOutput;

import commands.CommandsWithInput;
import commands.CommandsWithoutInput;
import exeptions.EmptyLine;
import exeptions.IncorrectCommand;
import exeptions.ZeroValue;

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

    private static Integer argumentValidator(String s) throws EmptyLine, ZeroValue{
        try {
            if (s.isEmpty()) throw new EmptyLine("ID argument for remove");
            int arg = Integer.parseInt(s);
            if (arg <= 0) throw new ZeroValue("ID argument for remove");
            return arg;
        } catch (EmptyLine | ZeroValue e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input for command. Try again");
        }
        return null;
    }

    public static void Input() {
        try {
            String input = scanner.nextLine();
            String[] inputSplit = input.split(" ");
            if (isWith(inputSplit[0])) {
                CommandsWithInput command = Enum.valueOf(CommandsWithInput.class, inputSplit[0].toUpperCase());
                Integer i = argumentValidator(inputSplit[1]);
                if (i == null) {return;}
                command.execute(i);
            } else if(isWithout(inputSplit[0])) {
                CommandsWithoutInput command = Enum.valueOf(CommandsWithoutInput.class, inputSplit[0].toUpperCase());
                command.execute();
            } else {
                throw new IncorrectCommand(inputSplit[0]);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(new IncorrectCommand("Command").getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
