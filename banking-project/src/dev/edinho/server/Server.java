package dev.edinho.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.logging.Handler;

public class Server {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost",8080),0);
        server.createContext("/get", new ServerGetHandler());
        server.setExecutor(null);
        server.start();
    }
}

class ServerGetHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {
        System.out.println(t.getRequestMethod());
        System.out.println(t.getProtocol());
        InputStream is = t.getRequestBody();
        System.out.println(is.toString());
    }
}