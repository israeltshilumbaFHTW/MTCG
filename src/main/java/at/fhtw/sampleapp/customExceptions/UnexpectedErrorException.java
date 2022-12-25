package at.fhtw.sampleapp.customExceptions;

public class UnexpectedErrorException extends Exception{
    public UnexpectedErrorException(String errorMessage) {
        super(errorMessage);
    }
}
