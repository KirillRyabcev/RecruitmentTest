package exception;

public class ObligatoryCompetenceException extends Exception {
    public ObligatoryCompetenceException() {
    }

    public ObligatoryCompetenceException(String message) {
        super(message);
    }

    public ObligatoryCompetenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ObligatoryCompetenceException(Throwable cause) {
        super(cause);
    }
}
