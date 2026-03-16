package fr.boutique;

public class StockInsuffisantException extends RuntimeException {
    public StockInsuffisantException(String message) { super(message); }
}
