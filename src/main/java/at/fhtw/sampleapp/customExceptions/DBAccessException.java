package at.fhtw.sampleapp.customExceptions;

public class DBAccessException extends Exception{
    public DBAccessException(String errorMessage) {
        super(errorMessage);
    }
}
