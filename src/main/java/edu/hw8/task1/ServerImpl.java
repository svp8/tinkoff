package edu.hw8.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

public class ServerImpl implements Runnable {
    private final Socket client;
    private final Map<String, String> quotes;

    private static final Logger LOGGER= LogManager.getLogger();

    public ServerImpl(Socket client, Map<String, String> quotes) {
        this.client = client;
        this.quotes = quotes;
    }

    public static void main(String[] args) {
        Map<String, String> quotesExample = Map.of("личности", "Не переходи на личности там, где их нет"
            , "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами"
            , "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма."
            , "интеллект", "Чем ниже интеллект, тем громче оскорбления"
        );
        startServer(3, quotesExample, 5);
    }

    public static void startServer(int poolNumber, Map<String, String> quotes, int maxRequests) {
        int requests = 0;
        try (ExecutorService executor = Executors.newFixedThreadPool(poolNumber)) {
            try (ServerSocket server = new ServerSocket(8080)) {
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
            Thread.sleep(1000);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
