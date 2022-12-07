package at.fhtw.sampleapp.CustomExceptions;

public class UnexpectedErrorException extends Exception{
    public UnexpectedErrorException(String errorMessage) {
        super(errorMessage);
    }
}
