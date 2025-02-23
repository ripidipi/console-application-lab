package commands;

public enum CommandsWithoutInput {
    ADD(Add::add),
    INFO(Info::info),
    HELP(Help::help),
    SHOW(Show::show);

    private final Runnable command;

    CommandsWithoutInput(Runnable command) {
        this.command = command;
    }

    public void execute() {
        command.run();
    }

}
