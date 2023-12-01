package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServerImpl implements AutoCloseable {
    public final int port;
    private final Map<String, String> quotes;
    private final int poolNumber;
    private ServerSocket server;
    private final ExecutorService executorService; //нужен, чтобы можно было использовать в start и close

    private static final Logger LOGGER = LogManager.getLogger();

    public ServerImpl(Map<String, String> quotes, int poolNumber, int port) {
        this.quotes = quotes;
        this.poolNumber = poolNumber;
        this.executorService = Executors.newSingleThreadExecutor();
        this.port = port;
        start();
    }

    private void start() {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Runnable runnable = () -> {
            try (ExecutorService executor = Executors.newFixedThreadPool(poolNumber)) {
                while (true) {
                    Socket client = server.accept();
                    Runnable worker = () -> send(client);
                    executor.execute(worker);
                }
            } catch (IOException e) {
                LOGGER.warn(e.getMessage());
            }
        };
        executorService.execute(runnable);
    }

    private void send(Socket client) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        ) {
            LOGGER.info("Server got client");
            String word = in.readLine();
            out.write(quotes.getOrDefault(word, "No quote for the word"));
            out.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void close() throws Exception {
        server.close();
        executorService.shutdown();
    }
}
