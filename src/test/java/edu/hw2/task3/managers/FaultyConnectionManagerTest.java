package edu.hw2.task3.managers;

import edu.hw2.task3.connections.FaultyConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FaultyConnectionManagerTest {
    @Test
    void getConnection(){
        FaultyConnectionManager faultyConnectionManager=new FaultyConnectionManager();
        Assertions.assertTrue(faultyConnectionManager.getConnection() instanceof FaultyConnection);
    }
}
