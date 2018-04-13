package Exceptions;

public class SFTPClientException extends Exception {

    public SFTPClientException() {
    }

    public SFTPClientException(String message) {
        super(message);
    }

    public SFTPClientException(String message, Throwable cause) {
        super(message, cause);
    }

    public SFTPClientException(Throwable cause) {
        super(cause);
    }
}
