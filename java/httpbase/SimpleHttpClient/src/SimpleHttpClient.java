import java.net.*;
import java.net.http.*;

public class SimpleHttpClient {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Response Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}