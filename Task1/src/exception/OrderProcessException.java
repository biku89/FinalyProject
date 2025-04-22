package exception;

public class OrderProcessException extends RuntimeException {
    public OrderProcessException(String message) {
        super(message);
    }
}
