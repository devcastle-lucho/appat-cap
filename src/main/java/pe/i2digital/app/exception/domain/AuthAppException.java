package pe.i2digital.app.exception.domain;

public class AuthAppException extends Exception{
    public AuthAppException() {
        super();
    }

    public AuthAppException(final String message) {
        super(message);
    }

    public AuthAppException(final String message,Exception ex) {
        super(message, ex);
    }
}
