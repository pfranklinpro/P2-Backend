package franklin.paul.sylvester.utils.custom_exceptions;

public class InvalidLikeException extends RuntimeException {
    public InvalidLikeException() {
    }

    public InvalidLikeException(String message) {
        super(message);
    }

    public InvalidLikeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidLikeException(Throwable cause) {
        super(cause);
    }

    public InvalidLikeException(String message, Throwable cause, boolean enableSuppression,
                                boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
