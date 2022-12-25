package at.fhtw.sampleapp.customExceptions;


public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String errorMessage) {
        super(errorMessage);
    }
}
