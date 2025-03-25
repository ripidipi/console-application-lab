import commands.*;
import exceptions.RemoveOfTheNextSymbol;
import input_output.*;
import related_to_the_collection.Collection;

import java.util.Scanner;

/**
 * The entry point of the application.
 */
public class Main {

    public static void main(String[] args) {
        initializeApplication();
        runCommandLoop();
    }

    /**
     * Initializes the application by loading necessary components and registering commands.
     */
    private static void initializeApplication() {
        Collection.getInstance();
        RunningFiles.getInstance();
        Help help = Help.getInstance();
        Logging.initialize();
        DistributionOfTheOutputStream.clear();

        help.addCommand(
                help, new Add(), new Info(), new Show(),
                new Update(), new Exit(), new Save(),
                new AddIfMax(), new Clear(), new CountByGroupAdmin(),
                new ExecuteScript(), new GroupCountingById(),
                new RemoveAnyByGroupAdmin(), new RemoveById(),
                new RemoveGreater(), new RemoveLower()
        );

        if (SavingAnEmergencyStop.checkIfFile()) {
            runPreviousSession();
        }
        try {
            FillCollectionFromFile.fillCollectionFromFile();
        } catch (Exception e) {
            System.out.println("Error loading collection from file: " + e.getMessage());
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }

    /**
     * Runs the command input loop, continuously waiting for user input.
     */
    private static void runCommandLoop() {

        while (Exit.running) {
            System.out.print("Enter the command: ");
            CommandsInput.inputFromConsole();
        }

    }

    private static void runPreviousSession() {
        try {
            System.out.println("Previous session was urgently completed.\nPrint Yes if you want to continue it.");
            Scanner scanner = new Scanner(System.in);
            if (!scanner.hasNextLine()) {
                new Exit().execute("", "");
                throw new RemoveOfTheNextSymbol();
            }
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("yes")) {
                SavingAnEmergencyStop.recapCommandFromFile();
            }
        } catch (RemoveOfTheNextSymbol e) {
            System.out.println(e.getMessage());
            Exit.exit();
        } catch (Exception e) {
            Logging.log(Logging.makeMessage(e.getMessage(), e.getStackTrace()));
        }
    }
}