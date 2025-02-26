package inputOutput;

import commands.Add;
import commands.Commands;

import java.util.Arrays;

public class FillCollectionFromFile {

    public static void fillCollectionFromFile() {
        try {
//            String fileName = System.getenv("CSV_FILE_NAME");
            String fileName = ("collection.csv");
            CommandsInput.inputFromFile(fileName, FillCollectionFromFile::adderForFill);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static Void adderForFill(String[] input) {
        try {
            if (BasicDataTypesInput.InputFromFile("Id", input[0], Integer.class, false,
                    false, false, null, true) == null) {
                return null;
            }
            Add.addFromFile(String.join(",",input));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
