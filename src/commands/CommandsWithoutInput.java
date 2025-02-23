package commands;

public enum CommandsWithoutInput {
    ADD(Add::add),
    INFO(Info::info),
    HELP(Help::help),
    SHOW(Show::show),
    CLEAR(Clear::clear),
    UPDATE(Update::update),
    EXIT(Exit::exit);

    private final Runnable command;

    CommandsWithoutInput(Runnable command) {
        this.command = command;
    }

    public void execute() {
        command.run();
    }

}
