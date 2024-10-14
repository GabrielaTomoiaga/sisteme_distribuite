/*

import java.net.*;
import java.io.*;

public class TCPEchoClient {
    public static void main(String[] args) {
        String address = "localhost";  // Schimbă adresa dacă ai servere distribuite
        int port = 8910;

        try (Socket socket = new Socket(address, port)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            TCPEchoReader reader = new TCPEchoReader(in);
            reader.setDaemon(true);  // Pornim un fir de execuție pentru a citi mesajele de la server
            reader.start();

            String input;
            while ((input = keyboardReader.readLine()) != null) {
                out.writeUTF(input);  // Trimite mesajul către server
            }
        } catch (IOException e) {
            System.out.println("Problems initializing: " + e.getMessage());
        }
    }
}

class TCPEchoReader extends Thread {
    private DataInputStream in;
    private boolean active = true;

    public TCPEchoReader(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (active) {
                String message = in.readUTF();
                System.out.println("Received from server: " + message);
            }
        } catch (IOException e) {
            active = false;
        }
    }
}
 */


import java.net.*;
import java.io.*;

public class TCPEchoClient {
    public static void main(String[] args) {
        String address = "localhost";  // Schimbă adresa dacă ai servere distribuite
        int port = 8910;  // Conectare la P2

        try (Socket socket = new Socket(address, port)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader keyboardReader = new BufferedReader(new InputStreamReader(System.in));

            TCPEchoReader reader = new TCPEchoReader(in);
            reader.setDaemon(true);  // Pornim un fir de execuție pentru a citi mesajele de la server
            reader.start();

            String input;
            while ((input = keyboardReader.readLine()) != null) {
                out.writeUTF(input);  // Trimite mesajul către P2
                System.out.println("P1 a trimis mesajul către P2: " + input);
            }
        } catch (IOException e) {
            System.out.println("Problems initializing P1: " + e.getMessage());
        }
    }
}

class TCPEchoReader extends Thread {
    private DataInputStream in;
    private boolean active = true;

    public TCPEchoReader(DataInputStream in) {
        this.in = in;
    }

    @Override
    public void run() {
        try {
            while (active) {
                String message = in.readUTF();
                System.out.println("Mesaj primit de la server: " + message);
            }
        } catch (IOException e) {
            active = false;
        }
    }
}
