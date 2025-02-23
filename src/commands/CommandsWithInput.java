package commands;

import java.util.function.Consumer;

public enum CommandsWithInput {

    REMOVE_BY_ID(RemoveById::removeById);

    private final Consumer<Integer> command;

    CommandsWithInput(Consumer<Integer> command) {
        this.command = command;
    }

    public void execute(Integer arg) {
        command.accept(arg);
    }

}
