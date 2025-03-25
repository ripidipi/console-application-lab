package input_output;

import commands.ExecuteScript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

/**
 * This interface is responsible for managing the output stream for console and file outputs.
 * It allows printing messages either to the console or to a file, depending on the current execution mode.
 * If the script execution mode is active, messages are written to a file; otherwise, they are printed to the console.
 */
public interface DistributionOfTheOutputStream {

    /**
     * Clears the output file, if it exists.
     * The output file is located at "data/output.txt".
     */
    static void clear() {
        try {
            File file = new File("data/output.txt");
            if (file.exists())
                file.delete();
        } catch (Exception e) {
            System.out.println("Output to file error");
            ExecuteScript.setExecuteScriptMode(false);
        }
    }

    /**
     * Prints a message to the output. Depending on the execution mode, it either prints to the console
     * or writes to a file.
     *
     * @param message The message to be printed or written.
     */
    static void println(String message) {
        if (ExecuteScript.getExecuteScriptMode()) {
            printlnToFile(message);
        } else
            System.out.println(message);
    }

    /**
     * Prints a message to the output without a newline. Depending on the execution mode, it either prints to the console
     * or writes to a file.
     *
     * @param message The message to be printed or written.
     */
    static void print(String message) {
        if (ExecuteScript.getExecuteScriptMode()) {
            printToFile(message);
        } else
            System.out.print(message);
    }

    /**
     * Writes a message to the output file with a newline. The file is located at "data/output.txt".
     *
     * @param message The message to be written to the file.
     */
    static void printlnToFile(String message) {
        String fileName = "data/output.txt";
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(fileName, true), StandardCharsets.UTF_8)) {
            writer.write(message);
            writer.write(System.lineSeparator());
        } catch (Exception e) {
            System.out.println("Output to file error");
            ExecuteScript.setExecuteScriptMode(false);
        }
    }

    /**
     * Writes a message to the output file without a newline. The file is located at "data/output.txt".
     *
     * @param message The message to be written to the file.
     */
    static void printToFile(String message) {
        String fileName = "data/output.txt";
        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(fileName, true), StandardCharsets.UTF_8)) {
            writer.write(message);
        } catch (Exception e) {
            System.out.println("Output to file error");
            ExecuteScript.setExecuteScriptMode(false);
        }
    }
}
