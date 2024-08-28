import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class SimpleHttpServer {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new Handler());

        server.setExecutor(null);
        server.start();

        System.out.println(server.getAddress());
    }

    static class Handler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            Account account = new Account("Edinho");
            System.out.println(exchange.getRequestMethod());
            exchange.getResponseHeaders().set("Content-Type", "application/x-java-serialized-object");
            exchange.sendResponseHeaders(200, 0);

            ObjectOutputStream oos = new ObjectOutputStream(exchange.getResponseBody());
            oos.writeObject(account);
            exchange.close();
        }
    }
}
