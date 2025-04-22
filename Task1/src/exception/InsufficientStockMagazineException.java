package exception;

public class InsufficientStockMagazineException extends RuntimeException {
    public InsufficientStockMagazineException(String message) {
        super(message);
    }
}
