
import java.net.*;
import java.io.*;

public class P3Server {
    public static void main(String[] args) {
        int port = 8912;  // P3 ascultă pe portul 8912
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("P3 created on port " + port);
            Socket clientSocket = serverSocket.accept();  // Așteaptă conexiunea de la P2
            System.out.println("Client (P2) connected from " + clientSocket.getInetAddress());

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            String input;
            while ((input = in.readUTF()) != null) {
                System.out.println("P3 a primit mesajul de la P2: " + input);
                out.writeUTF("P3 îi trimite înapoi lui P1: " + input);
                System.out.println("P3 a trimis mesajul către P1");
            }
        } catch (IOException e) {
            System.out.println("Problems initializing P3: " + e.getMessage());
        }
    }
}
