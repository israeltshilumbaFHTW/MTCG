package at.fhtw.sampleapp.CustomExceptions;

public class PackageNotAvailableException extends Exception{
    public PackageNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
