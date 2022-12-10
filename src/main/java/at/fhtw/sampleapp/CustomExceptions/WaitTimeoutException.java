package at.fhtw.sampleapp.CustomExceptions;


public class WaitTimeoutException extends Exception {
    public WaitTimeoutException(String errorMessage) {
        super(errorMessage);
    }
}
