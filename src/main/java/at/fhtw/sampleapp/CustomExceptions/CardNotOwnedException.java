package at.fhtw.sampleapp.CustomExceptions;

public class CardNotOwnedException extends Exception{
    public CardNotOwnedException(String errorMessage) {
        super(errorMessage);
    }
}
