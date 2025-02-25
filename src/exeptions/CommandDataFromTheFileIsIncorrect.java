package exeptions;

public class CommandDataFromTheFileIsIncorrect extends RuntimeException {
    public CommandDataFromTheFileIsIncorrect(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " arguments are incorrect run command failed. If you have a questions check help";
    }
}
