package dev.edinho.client;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;

public class Client {
    public static void main(String[] args) {
        try (
                HttpClient client = HttpClient.newHttpClient();
                ) {
            HttpRequest request = HttpRequest.newBuilder()
                    .GET()
                    .uri(new URI("http://localhost:8080"))
                    .build();

            HttpResponse<InputStream> response = client.send(request,HttpResponse.BodyHandlers.ofInputStream());
            System.out.println(response.statusCode());

            int readedbyte;
            while ((readedbyte = response.body().read()) != -1) {
                System.out.print((char) readedbyte);
            }
        } catch (URISyntaxException | InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
