/*
public class Main {
    public static void main(String[] args) {
        // Pornim serverul pentru P2
        new Thread(() -> {
            TCPEchoServer.main(null);  // P2 va fi server
        }).start();

        // Pornim clientul pentru P1
        new Thread(() -> {
            TCPEchoClient.main(null);  // P1 va fi client
        }).start();
    }
}
*/


public class Main {
    public static void main(String[] args) {
        // Pornim serverul pentru P2
        new Thread(() -> {
            TCPEchoServer.main(null);  // P2 va fi server
        }).start();

        // Pornim serverul pentru P3
        new Thread(() -> {
            P3Server.main(null);  // P3 va fi server
        }).start();

        // Pornim clientul pentru P1
        new Thread(() -> {
            TCPEchoClient.main(null);  // P1 va fi client
        }).start();
    }
}
