package input_output;

import commands.ExecuteScript;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public interface DistributionOfTheOutputStream {

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

    static void println(String message) {
        if (ExecuteScript.getExecuteScriptMode()) {
            printlnToFile(message);
        } else
            System.out.println(message);
    }

    static void print(String message) {
        if (ExecuteScript.getExecuteScriptMode()) {
            printToFile(message);
        } else
            System.out.print(message);
    }

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
