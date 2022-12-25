package at.fhtw.sampleapp.customExceptions;

public class CardNotOwnedException extends Exception{
    public CardNotOwnedException(String errorMessage) {
        super(errorMessage);
    }
}
