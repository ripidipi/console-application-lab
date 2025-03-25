package input_output;

import commands.Commands;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class SavingAnEmergencyStop {

    /**
     * Name of the primary emergency stop file.
     */
    static final String emergencyFile = "data/emergency_stop.csv";

    public static void addStringToFile(String message) {

        File file = new File(emergencyFile);

        try (OutputStreamWriter writer = new OutputStreamWriter(
                new FileOutputStream(file, true), StandardCharsets.UTF_8)) {
            writer.write(message + ',');
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    public static void clearFile() {

        File file = new File(emergencyFile);

        if (file.exists()) {
            file.delete();
        }
    }

    public static void recapCommandFromFile() {

        File file = new File(emergencyFile);

        try (Scanner scanner = new Scanner(file)) {
                    String line = scanner.nextLine();
                    if(line.endsWith(",")) {
                        line = line.substring(0, line.length() - 1);
                    }
                    String[] values = line.split(",");
                    Commands command = Enum.valueOf(Commands.class, values[0].toUpperCase());
                    DistributionOfTheOutputStream.println("Continue of work " + command.name());
                    command.execute(line, "M");
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    public static boolean checkIfFile() {
        File file = new File(emergencyFile);

        return file.exists();
    }

}
