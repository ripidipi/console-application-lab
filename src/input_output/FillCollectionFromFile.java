package input_output;

import commands.Add;
import commands.AddFromFile;
import commands.ExecuteScript;
import exceptions.InsufficientNumberOfArguments;

/**
 * A utility class to populate a collection from a file.
 */
public class FillCollectionFromFile {

    /**
     * Reads a collection from a file and populates it.
     * The file name is predefined as "Collection.csv".
     * If an error occurs, the method prints an error message.
     */
    public static void fillCollectionFromFile() {
        try {
            // String fileName = System.getenv("CSV_FILE_NAME");
            String fileName = ("data/Collection.csv");
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
    public static Void adderForFill(String[] input) {
        try {
            if (PrimitiveDataTransform.inputFromFile("Id", input[0], Integer.class, true,
                    false, false, null, true) == null) {
                return null;
            }
            AddFromFile.addStudyGroupFromFile(String.join(",", input));
        } catch (InsufficientNumberOfArguments e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
        return null;
    }
}
