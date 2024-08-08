import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(1234)) {
            System.out.println("Servidor iniciado.");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Um Cliente Conectou " + socket.getRemoteSocketAddress());
            }
        }catch (Exception e) {
            e.printStackTrace();
        };


    }
}
