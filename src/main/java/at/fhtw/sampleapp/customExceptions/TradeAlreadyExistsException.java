package at.fhtw.sampleapp.customExceptions;

public class TradeAlreadyExistsException extends Exception {
    public TradeAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
