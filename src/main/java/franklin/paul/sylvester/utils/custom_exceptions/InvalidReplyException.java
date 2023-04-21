package franklin.paul.sylvester.utils.custom_exceptions;

public class InvalidReplyException extends RuntimeException {
    public InvalidReplyException() {
    }

    public InvalidReplyException(String message) {
        super(message);
    }

    public InvalidReplyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReplyException(Throwable cause) {
        super(cause);
    }

    public InvalidReplyException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
