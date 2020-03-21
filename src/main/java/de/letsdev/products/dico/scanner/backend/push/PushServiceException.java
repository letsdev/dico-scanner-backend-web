package de.letsdev.products.dico.scanner.backend.push;

public class PushServiceException extends Exception {

    public PushServiceException() {
    }

    public PushServiceException(String message) {
        super(message);
    }

    public PushServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PushServiceException(Throwable cause) {
        super(cause);
    }

    public PushServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
