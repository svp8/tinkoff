package edu.hw6.task5;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Task5 {
    private Task5() {

    }

    public static long[] hackerNewsTopStories() throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        body = body.replace("[", "");
        body = body.replace("]", "");
        String[] bodyArr = body.split(",");
        long[] result = new long[bodyArr.length];
        for (int i = 0; i < bodyArr.length; i++) {
            result[i] = Long.parseLong(bodyArr[i]);
        }
        return result;
    }

    public static String news(long id) throws URISyntaxException, IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI("https://hacker-news.firebaseio.com/v0/item/" + id + ".json"))
            .GET()
            .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        Pattern pattern = Pattern.compile(".*\"title\":\"(.+)\",\"type\".*");
        Matcher matcher = pattern.matcher(body);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }
}
