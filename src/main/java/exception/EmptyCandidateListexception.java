package exception;

public class EmptyCandidateListexception extends Exception{
    public EmptyCandidateListexception() {
    }

    public EmptyCandidateListexception(String message) {
        super(message);
    }

    public EmptyCandidateListexception(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyCandidateListexception(Throwable cause) {
        super(cause);
    }
}
