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

public class ServerImpl implements Runnable {
    public static final int PORT = 8080;
    private final Socket client;
    private final Map<String, String> quotes;

    private static final Logger LOGGER = LogManager.getLogger();

    public ServerImpl(Socket client, Map<String, String> quotes) {
        this.client = client;
        this.quotes = quotes;
    }

    public static void startServer(int poolNumber, Map<String, String> quotes, int maxRequests) {
        int requests = 0;
        try (ExecutorService executor = Executors.newFixedThreadPool(poolNumber)) {
            try (ServerSocket server = new ServerSocket(PORT)) {
                while (requests < maxRequests) {
                    Runnable worker = new ServerImpl(server.accept(), quotes);
                    executor.execute(worker);
                    requests++;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void run() {
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
}
