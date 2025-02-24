package exeptions;

public class BirthdayInTheFuture extends RuntimeException {
    public BirthdayInTheFuture(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return super.getMessage() + " date for this filed can't be in the future. If you have a questions check help()";
    }
}
