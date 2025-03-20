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
     * Add a new study group from a file to the collection.
     */
    ADD_F(new AddFromFile()),

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
     * Update an existing study group from a file.
     */
    UPDATE_F(new UpdateFromFile()),

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
     * Add a study group from a file if its ID is greater than the maximum ID in the collection.
     */
    ADD_IF_MAX_F(new AddIfMaxFromFile()),

    /**
     * Remove study groups greater than a specified one.
     */
    REMOVE_GREATER(new RemoveGreater()),

    /**
     * Remove study groups greater than a specified one from a file.
     */
    REMOVE_GREATER_F(new RemoveGreaterFromFile()),

    /**
     * Remove study groups lower than a specified one.
     */
    REMOVE_LOWER(new RemoveLower()),

    /**
     * Remove study groups lower than a specified one from a file.
     */
    REMOVE_LOWER_F(new RemoveLowerFromFile()),

    /**
     * Count the number of study groups managed by a specific group admin.
     */
    COUNT_BY_GROUP_ADMIN(new CountByGroupAdmin()),

    /**
     * Count the number of study groups managed by a specific group admin from a file.
     */
    COUNT_BY_GROUP_ADMIN_F(new CountByGroupAdminFromFile()),

    /**
     * Remove a study group managed by a specific group admin.
     */
    REMOVE_ANY_BY_GROUP_ADMIN(new RemoveAnyByGroupAdmin()),

    /**
     * Remove a study group managed by a specific group admin from a file.
     */
    REMOVE_ANY_BY_GROUP_ADMIN_F(new RemoveAnyByGroupAdminFromFile()),

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
    public void execute(String arg) {
        command.execute(arg);
    }
}
