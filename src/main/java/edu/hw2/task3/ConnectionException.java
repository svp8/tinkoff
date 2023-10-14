package edu.hw2.task3;

public class ConnectionException extends RuntimeException {
    public ConnectionException(String cause) {
        super(cause);
    }

    public ConnectionException(String cause, Throwable throwable) {
        super(cause, throwable);
    }
}
