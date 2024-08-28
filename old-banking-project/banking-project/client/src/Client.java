import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String hostname = "localhost"; // Endereço do servidor
        int port = 1234; // Porta usada pelo servidor

        try (Socket socket = new Socket("localhost", port);
             BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado ao servidor em " + hostname + ":" + port);

            String texto;
            while (true) {
                System.out.println("Insira um texto (ou 'exit' para sair):");
                texto = scanner.nextLine();

                // Envia a mensagem ao servidor
                output.println(texto);

                // Se o usuário digitar "exit", encerra o cliente
                if ("exit".equalsIgnoreCase(texto)) {
                    System.out.println("Saindo...");
                    break;
                }

                // Recebe a resposta do servidor
                String response = input.readLine();
                System.out.println("Resposta do servidor: " + response);
            }
        } catch (UnknownHostException e) {
            System.err.println("Host desconhecido: " + hostname);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Erro na comunicação: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
