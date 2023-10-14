package edu.hw2;

import edu.hw2.task3.PopularCommandExecutor;
import edu.hw2.task3.managers.DefaultConnectionManager;
import edu.hw2.task3.managers.FaultyConnectionManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.TimeoutException;

public class Task3Test {

    @Test
    @DisplayName("Test of Faulty Connection")
    void testFaultyConnectionManager() {
        PopularCommandExecutor cmd = new PopularCommandExecutor(new FaultyConnectionManager(), 1);
        try {
            boolean flag = cmd.updatePackages();
            Assertions.assertTrue(flag);
        } catch (Exception e) {
            Assertions.assertEquals(TimeoutException.class, e.getCause().getClass());
        }
    }

    @Test
    @DisplayName("Test of Default Connection")
    void testDefaultConnectionManager() {
        PopularCommandExecutor cmd = new PopularCommandExecutor(new DefaultConnectionManager(), 30);
        try {
            boolean flag = cmd.updatePackages();
            Assertions.assertTrue(flag);
        } catch (Exception e) {
            Assertions.assertEquals(TimeoutException.class, e.getCause().getClass());
        }
    }
}
