package input_output;

import commands.Add;
import exceptions.InsufficientNumberOfArguments;

/**
 * A utility class to populate a collection from a file.
 * This class reads a CSV file, processes the data, and adds study groups to the collection.
 */
public class FillCollectionFromFile {

    /**
     * Reads a collection from a file and populates it.
     * The file name is predefined as "data/collection.csv".
     * If an error occurs, the method logs the error message.
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
     * Processes an input line from the file and adds a study group to the collection.
     * If the ID is valid, it uses the {@link Add#execute(String, String)} method
     * to add the study group.
     *
     * @param input An array of strings representing the fields of a study group.
     * @param inputFile The path to the file being processed (not used in this method).
     * @return Always returns null.
     * @throws InsufficientNumberOfArguments If the input data is insufficient for adding a study group.
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
