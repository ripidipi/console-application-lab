package exeptions;

public class ConnectionToFileFailed extends RuntimeException {
  public ConnectionToFileFailed(String message) {
    super(message);
  }

  @Override
  public String getMessage() {
    return super.getMessage() + ". Please try again. If you have a questions check help()";
  }
}
