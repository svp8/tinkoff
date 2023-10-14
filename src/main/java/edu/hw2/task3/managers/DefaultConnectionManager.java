package edu.hw2.task3.managers;

import edu.hw2.task3.connections.Connection;
import edu.hw2.task3.connections.FaultyConnection;
import edu.hw2.task3.connections.StableConnection;
import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        //Должен возвращать иногда faulty
        Random random = new Random();
        if (random.nextInt() % 2 == 0) {
            return new FaultyConnection();
        } else {
            return new StableConnection();
        }

    }
}
