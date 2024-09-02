import java.io.*;
import java.net.*;


class Listener extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public Listener(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintWriter(socket.getOutputStream(), true);

            String message;
            while ((message = input.readLine()) != null) {
                System.out.println("Mensagem do cliente: " + message);

                // Responde ao cliente (eco)
                output.println("Servidor recebeu: " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                input.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}