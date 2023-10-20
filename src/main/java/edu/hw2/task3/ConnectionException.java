package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
