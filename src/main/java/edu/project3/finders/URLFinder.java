package edu.project3.finders;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class URLFinder implements Finder {
    @Override
    public Map<String, List<String>> find(String path, Path root) {
        URI uri = null;
        try {
            uri = new URI(path);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .GET()
                .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String body = response.body();
            String[] arr = body.split("\n");
            HashMap<String, List<String>> files = new HashMap<>();
            files.put(request.uri().getPath(), Arrays.stream(arr).toList());
            return files;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
