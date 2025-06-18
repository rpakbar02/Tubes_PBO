package exception;

public class DeadlineIsBeforeCreation extends Exception{
    boolean error = false;
    public DeadlineIsBeforeCreation() {
        super("Error: Deadline should not be before creation date!");
        error = true;
    }
    @Override
    public String getMessage(){
        error = false;
        return super.getMessage();
    }

    public boolean isError() {
        return error;
    }
}
