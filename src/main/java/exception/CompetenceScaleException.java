package exception;

public class CompetenceScaleException extends Exception {
    public CompetenceScaleException() {
    }

    public CompetenceScaleException(String message) {
        super(message);
    }

    public CompetenceScaleException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompetenceScaleException(Throwable cause) {
        super(cause);
    }
}
