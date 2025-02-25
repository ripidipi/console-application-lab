package exeptions;

public class InsufficientNumberOfArguments extends RuntimeException {
    public InsufficientNumberOfArguments(String message) {
        super(message);
    }

  @Override
  public String getMessage() {
    return super.getMessage() + " command must have different quantity of arguments. If you have a questions check help";
  }
}
