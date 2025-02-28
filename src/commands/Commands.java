package commands;

import java.util.function.Consumer;

public enum Commands {
    ADD(Add::add),
    ADD_F(Add::addFromFile),
    INFO(Info::info),
    HELP(Help::help),
    SHOW(Show::show),
    CLEAR(Clear::clear),
    UPDATE(Update::update),
    UPDATE_F(Update::updateFromFile),
    EXIT(Exit::exit),
    SAVE(Save::save),
    REMOVE_BY_ID(RemoveById::removeById),
    EXECUTE_SCRIPT(ExecuteScript::executeScript),
    ADD_IF_MAX(AddIfMax::addIdMax),
    REMOVE_GREATER(RemoveGreater::removeGreater),
    REMOVE_LOWER(RemoveLower::removeLower),
    COUNT_BY_GROUP_ADMIN(CountByGroupAdmin::countByGroupAdmin),
    REMOVE_ANY_BY_GROUP_ADMIN(RemoveAnyByGroupAdmin::removeAnyByGroupAdmin),
    GROUP_COUNTING_BY_ID(GroupCountingById::groupCountingById);


    private final Runnable command;
    private final Consumer<String> commandWithArg;

    Commands(Runnable command) {
        this.command = command;
        this.commandWithArg = null;
    }

    Commands(Consumer<String> commandWithArg) {
        this.command = null;
        this.commandWithArg = commandWithArg;
    }

    public void execute(String arg) {
        if (commandWithArg != null) {
            commandWithArg.accept(arg);
        }else if (command != null) {
            command.run();
        }else {
            System.out.println("Problems with set of argument");
        }
    }
}
