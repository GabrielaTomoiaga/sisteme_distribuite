/*

import java.net.*;
import java.io.*;

public class TCPEchoServer {
    public static void main(String[] args) {
        int port = 8910;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server created on port " + port);
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected from " + clientSocket.getInetAddress());

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("Welcome to the TCP Echo Server!");

            String input;
            while ((input = in.readUTF()) != null) {
                System.out.println("You typed: " + input);
                out.writeUTF(input);  // Trimite mesajul înapoi către client
            }
        } catch (IOException e) {
            System.out.println("Problems initializing server: " + e.getMessage());
        }
    }
}
 */

import java.net.*;
import java.io.*;

public class TCPEchoServer {
    public static void main(String[] args) {
        int port = 8910;  // P2 ascultă pe portul 8910
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("P2 created on port " + port);
            Socket clientSocket = serverSocket.accept();  // Așteaptă conexiunea de la P1
            System.out.println("Client (P1) connected from " + clientSocket.getInetAddress());

            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            out.writeUTF("Welcome to P2!");

            String input;
            while ((input = in.readUTF()) != null) {
                System.out.println("P2 a primit mesajul de la P1: " + input);

                // Conectează P2 la P3
                try (Socket socketToP3 = new Socket("localhost", 8912)) {  // P3 rulează pe portul 8912
                    DataOutputStream outToP3 = new DataOutputStream(socketToP3.getOutputStream());
                    DataInputStream inFromP3 = new DataInputStream(socketToP3.getInputStream());

                    // Trimite mesajul către P3
                    outToP3.writeUTF(input);
                    System.out.println("P2 a trimis mesajul către P3");

                    // Așteaptă răspunsul de la P3
                    String responseFromP3 = inFromP3.readUTF();
                    System.out.println("P2 a primit răspuns de la P3: " + responseFromP3);
                }
            }
        } catch (IOException e) {
            System.out.println("Problems initializing P2: " + e.getMessage());
        }
    }
}
