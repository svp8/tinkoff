package edu.hw2.task3.connections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StableConnectionTest {

    @Test
    void execute() {
        StableConnection stableConnection=new StableConnection();
        Assertions.assertDoesNotThrow(()->stableConnection.execute("abc"));
    }
}
