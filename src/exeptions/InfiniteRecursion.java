package exeptions;

public class InfiniteRecursion extends RuntimeException {
    public InfiniteRecursion(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return "Running " + super.getMessage() + " got into an infinite recursion, fix it and try again. If you have a questions check help()";
    }
}
