package edu.hw2.task3;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.managers.ConnectionManager;
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

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        Exception cause = null;
        for (int i = 0; i < maxAttempts; i++) {
            try (Connection connection = manager.getConnection()) {
                connection.execute(command);
                return;
            } catch (Exception e) {
                cause = e;
                LOGGER.warn(e.getMessage());
            }
        }
        throw new ConnectionException("Timeout", cause);
    }
}
