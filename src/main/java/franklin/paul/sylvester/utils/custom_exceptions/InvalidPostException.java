package franklin.paul.sylvester.utils.custom_exceptions;

public class InvalidPostException extends RuntimeException {
    public InvalidPostException() {
    }

    public InvalidPostException(String message) {
        super(message);
    }

    public InvalidPostException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidPostException(Throwable cause) {
        super(cause);
    }

    public InvalidPostException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
