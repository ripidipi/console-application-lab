package exeptions;

public class IncorrectValue extends RuntimeException {
    public IncorrectValue(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " argument for command can't have this value. If you have a questions check help";
  }
}
