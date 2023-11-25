package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ClientImpl implements Callable<String> {
    private final String question;
    private static final Logger LOGGER= LogManager.getLogger();

    public ClientImpl(String question) {
        this.question = question;
    }

    public static void main(String[] args) {
       LOGGER.info(getQuote("оскорбления"));
    }

    public static String getQuote(String word) {
        try (ExecutorService executorService = Executors.newSingleThreadExecutor()) {
            Future<String> quotes = executorService.submit(new ClientImpl(word));
            return quotes.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> getQuotes(List<String> words) {
        try (ExecutorService executorService = Executors.newFixedThreadPool(words.size())) {
            List<ClientImpl> clients = new ArrayList<>();
            for (String word : words) {
                clients.add(new ClientImpl(word));
            }
            List<Future<String>> quotes = executorService.invokeAll(clients);
            List<String> result = new ArrayList<>();
            for (Future<String> i : quotes) {
                result.add(i.get());
            }
            return result;
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String call() throws Exception {
        Socket clientSocket = null;
        while (true) {
            try {
                clientSocket = new Socket("localhost", 8080);
                if (clientSocket != null) {
                    break;
                }
            } catch (IOException e) {
                Thread.sleep(1000);
            }
        }

        try (
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))
        ) {
            LOGGER.info("Client connected");
            out.write(question+"\n");
            out.flush();
            String quote = in.readLine();
            clientSocket.close();
            return quote;
        }
    }
}
