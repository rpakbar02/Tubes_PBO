package exception;

public class MemberNotFound extends Exception {
    public MemberNotFound() {
        super("Error: Member not found in this project!");
    }
    
}
