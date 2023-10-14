package edu.hw2.task3;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.managers.ConnectionManager;
import java.util.concurrent.TimeoutException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PopularCommandExecutor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public boolean updatePackages() {
        return tryExecute("apt update && apt upgrade -y");
    }

    boolean tryExecute(String command) {
        boolean success = false;
        for (int i = 0; i < maxAttempts && !success; i++) {
            try (Connection connection = manager.getConnection()) {
                success = connection.execute(command);
            } catch (Exception e) {
                LOGGER.info(e.getMessage());
            }
        }
        if (!success) {
            throw new ConnectionException("Timeout", new TimeoutException());
        }
        return true;

    }
}
