import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class SimpleHttpClient {
    public static void main(String[] args) {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/"))
                .build();

        try {
            HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());
            ObjectInputStream ois = new ObjectInputStream(response.body());
            Account account = (Account) ois.readObject();
            System.out.println(account.getName());

            System.out.println("Response Code: " + response.statusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}