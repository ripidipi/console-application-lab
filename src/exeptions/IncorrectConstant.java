package exeptions;

public class IncorrectConstant extends RuntimeException {
    public IncorrectConstant(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " can't have that value. If you have a questions check help()";
    }
}
