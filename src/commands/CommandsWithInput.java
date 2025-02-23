package commands;

import java.util.function.Consumer;

public enum CommandsWithInput {

    REMOVE_BY_ID(RemoveById::removeById);

    private final Consumer<String> command;

    CommandsWithInput(Consumer<String> command) {
        this.command = command;
    }

    public void execute(String arg) {
        command.accept(arg);
    }

}
