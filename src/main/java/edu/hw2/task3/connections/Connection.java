package edu.hw2.task3.connections;

public interface Connection extends AutoCloseable {
    boolean execute(String command);
}
