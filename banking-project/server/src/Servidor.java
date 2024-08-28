import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Servidor iniciado e aguardando conexões...");
            System.out.println("Criado no endereço: " + serverSocket.getChannel());
            System.out.println("Criado na porta: " + serverSocket.getLocalPort());

            while (true) {
                // Aguarda a conexão de um cliente
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado!");

                // Inicia uma nova thread para lidar com o cliente
                new Listener(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}