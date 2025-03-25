package commands;

/**
 * Enum representing available commands in the system.
 * Each command corresponds to a specific action that can be executed.
 * The commands are either parameterless (using Runnable) or require arguments (using Consumer&lt;String&gt;).
 */
public enum Commands {
    /**
     * Add a new study group to the collection.
     */
    ADD(new Add()),

    /**
     * Print information about the collection (type, initialization date, number of elements).
     */
    INFO(new Info()),

    /**
     * Print help information about available commands.
     */
    HELP(Help.getInstance()),

    /**
     * Show all the study groups in the collection.
     */
    SHOW(new Show()),

    /**
     * Clear the entire collection of study groups.
     */
    CLEAR(new Clear()),

    /**
     * Update an existing study group in the collection by ID.
     */
    UPDATE(new Update()),

    /**
     * Exit the program.
     */
    EXIT(new Exit()),

    /**
     * Save the current state of the collection to a file.
     */
    SAVE(new Save()),

    /**
     * Remove a study group from the collection by its ID.
     */
    REMOVE_BY_ID(new RemoveById()),

    /**
     * Execute a script.
     */
    EXECUTE_SCRIPT(new ExecuteScript()),

    /**
     * Add a study group if its ID is greater than the maximum ID in the collection.
     */
    ADD_IF_MAX(new AddIfMax()),

    /**
     * Remove study groups greater than a specified one.
     */
    REMOVE_GREATER(new RemoveGreater()),

    /**
     * Remove study groups lower than a specified one.
     */
    REMOVE_LOWER(new RemoveLower()),

    /**
     * Count the number of study groups managed by a specific group admin.
     */
    COUNT_BY_GROUP_ADMIN(new CountByGroupAdmin()),

    /**
     * Remove a study group managed by a specific group admin.
     */
    REMOVE_ANY_BY_GROUP_ADMIN(new RemoveAnyByGroupAdmin()),

    /**
     * Group the study groups by ID and display the result.
     */
    GROUP_COUNTING_BY_ID(new GroupCountingById());

    private final Command command;

    /**
     * Constructor for commands .
     *
     * @param command Command .
     */
    Commands(Command command) {
        this.command = command;
    }

    /**
     * Executes the command with the provided argument.
     * If the command does not accept arguments, it does nothing.
     *
     * @param arg The argument to pass to the command.
     */
    public void execute(String arg, String inputMode) {
        command.execute(arg, inputMode);
    }
}
