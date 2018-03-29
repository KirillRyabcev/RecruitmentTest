package exception;

public class ListIndexException extends Exception{
    public ListIndexException() {
    }

    public ListIndexException(String message) {
        super(message);
    }

    public ListIndexException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListIndexException(Throwable cause) {
        super(cause);
    }


}
