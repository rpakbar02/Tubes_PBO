package exception;

public class MemberIsAlreadyInProject extends Exception {
    public MemberIsAlreadyInProject() {
        super("Error: Member is already in project!");
    }
}