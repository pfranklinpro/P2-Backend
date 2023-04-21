package franklin.paul.sylvester.utils.custom_exceptions;

public class InvalidProfileException extends RuntimeException {
    public InvalidProfileException() {
    }

    public InvalidProfileException(String message) {
        super(message);
    }

    public InvalidProfileException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidProfileException(Throwable cause) {
        super(cause);
    }

    public InvalidProfileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
