package exeptions;

public class IncorrectCommand extends RuntimeException {
    public IncorrectCommand(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " command doesn't exist. If you have a questions check help";
    }
}
