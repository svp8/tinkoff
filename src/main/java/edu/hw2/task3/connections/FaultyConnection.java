package edu.hw2.task3.connections;

import edu.hw2.task3.ConnectionException;
import java.util.Random;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FaultyConnection implements Connection {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void execute(String command) {
        Random random = new Random();
        if (random.nextInt() % 2 == 0) {
            throw new ConnectionException("Faulty Connection");
        } else {
            LOGGER.info(command);
        }

    }

    @Override
    public void close() throws Exception {
        LOGGER.info("Connection closed");
    }
}
