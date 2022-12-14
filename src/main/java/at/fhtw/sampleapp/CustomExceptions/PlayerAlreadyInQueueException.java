package at.fhtw.sampleapp.CustomExceptions;

public class PlayerAlreadyInQueueException extends Exception{
    public PlayerAlreadyInQueueException(String errorMessage) {
        super(errorMessage);
    }
}
