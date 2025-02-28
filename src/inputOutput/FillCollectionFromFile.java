package inputOutput;

import commands.Add;

/**
 * Class responsible for filling the collection from a file.
 */
public class FillCollectionFromFile {

    /**
     * Fills the collection by reading data from a specified file.
     * The file should contain the necessary information to add elements to the collection.
     */
    public static void fillCollectionFromFile() {
        try {
//            String fileName = System.getenv("CSV_FILE_NAME");
            String fileName = ("collection.csv");
            CommandsInput.inputFromFile(fileName, FillCollectionFromFile::adderForFill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Adds a study group to the collection based on the data provided in the input array.
     * This method is used as a callback to process each line of the input file.
     *
     * @param input The input data parsed from the file.
     * @return Returns null to indicate successful processing of the input line.
     */
    public static Void adderForFill(String[] input) {
        try {
            if (PrimitiveDataTransform.inputFromFile("Id", input[0], Integer.class, false,
                    false, false, null, true) == null) {
                return null;
            }
            Add.addStudyGroupFromFile(String.join(",",input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
