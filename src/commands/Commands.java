package commands;

import java.util.function.Consumer;

public enum Commands {
    ADD(c -> Add.add()),
    HELP(Help::help),
    INFO(c -> Info.info());

    private final Consumer<Helpable> command;

    Commands(Consumer<Helpable> command) {
        this.command = command;
    }

    public void execute(Helpable arg) {
        command.accept(arg);
    }
}
