package at.fhtw.sampleapp.customExceptions;

public class PackageNotAvailableException extends Exception{
    public PackageNotAvailableException(String errorMessage) {
        super(errorMessage);
    }
}
