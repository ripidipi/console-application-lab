package commands;

import exceptions.IncorrectValue;
import exceptions.InfiniteRecursion;
import input_output.CommandsInput;
import input_output.DistributionOfTheOutputStream;
import input_output.Logging;
import input_output.RunningFiles;

/**
 * Command that executes a script from a specified file.
 */
public class ExecuteScript implements Helpable, Command {

    private static boolean executeScriptMode = false;

    /**
     * Executes a script from the given file.
     *
     * @param fileName The name of the script file to execute.
     * @throws IncorrectValue If the file name is empty.
     * @throws InfiniteRecursion If the script is already being executed (to prevent recursion).
     */
    public static void executeScript(String fileName) {
        executeScriptMode = true;
        DistributionOfTheOutputStream.printlnToFile("Work of the script " + fileName);
        try {
            if (fileName == null || fileName.isEmpty()) {
                throw new IncorrectValue("File name cannot be empty.");
            }

            if (RunningFiles.getInstance().isThere(fileName.toUpperCase())) {
                throw new InfiniteRecursion("Infinite recursion detected with file: " + fileName);
            }

            RunningFiles.getInstance().addFileName(fileName.toUpperCase());
            CommandsInput.inputFromFile(fileName, CommandsInput::isCommand);
            RunningFiles.getInstance().removeFileName(fileName.toUpperCase());
        } catch (IncorrectValue | InfiniteRecursion e) {
            DistributionOfTheOutputStream.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        } finally {
            DistributionOfTheOutputStream.printlnToFile("");
            executeScriptMode = false;
            DistributionOfTheOutputStream.println("ExecuteScript from " + fileName + " finished");
        }
    }

    /**
     * @return value of executeScriptMode
     */
    public static boolean getExecuteScriptMode() {
        return executeScriptMode;
    }

    @Override
    public void execute(String arg, String inputMode) {
        executeScript(arg);
    }

    @Override
    public String getHelp() {
        return "Executes a script from the specified file.";
    }
}
