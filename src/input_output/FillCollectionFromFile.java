package input_output;

import commands.Add;
import exceptions.InsufficientNumberOfArguments;

/**
 * A utility class to populate a collection from a file.
 */
public class FillCollectionFromFile {

    /**
     * Reads a collection from a file and populates it.
     * The file name is predefined as "collection.csv".
     * If an error occurs, the method prints an error message.
     */
    public static void fillCollectionFromFile() {
        try {
            // String fileName = System.getenv("CSV_FILE_NAME");
            String fileName = ("data/collection.csv");
            CommandsInput.inputFromFile(fileName, FillCollectionFromFile::adderForFill);
            SavingAnEmergencyStop.clearFile();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Processes an input line from the file and adds a study group if the ID is valid.
     *
     * @param input An array of strings representing the fields of a study group.
     * @return Always returns null.
     */
    public static Void adderForFill(String[] input, String inputFile) {
        try {
            if (PrimitiveDataTransform.inputFromFile("Id", input[0], Integer.class, true,
                    false, false, null, true) == null) {
                return null;
            }
            new Add().execute((String.join(",", input)), inputFile);
        } catch (InsufficientNumberOfArguments e) {
            DistributionOfTheOutputStream.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
        return null;
    }
}
