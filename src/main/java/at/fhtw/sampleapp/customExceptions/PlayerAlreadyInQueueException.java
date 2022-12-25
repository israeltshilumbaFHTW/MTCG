package at.fhtw.sampleapp.customExceptions;

public class PlayerAlreadyInQueueException extends Exception{
    public PlayerAlreadyInQueueException(String errorMessage) {
        super(errorMessage);
    }
}
