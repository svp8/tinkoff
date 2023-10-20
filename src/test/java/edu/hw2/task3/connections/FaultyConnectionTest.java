package edu.hw2.task3.connections;

import edu.hw2.task3.ConnectionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FaultyConnectionTest {

    @Test
    void execute() {
        FaultyConnection faultyConnection=new FaultyConnection();
        try{
            faultyConnection.execute("abc");
        }catch (Exception e){
            Assertions.assertEquals(ConnectionException.class, e.getClass());;
        }
    }
}
