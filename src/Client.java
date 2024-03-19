import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable {
    private Socket socket;
    private ChatServer server;
    Scanner in;
    PrintStream out;

    public Client(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
        new Thread(this).start();
    }

    void receive (String message) {
        out.println(message);
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            in = new Scanner(is);
            out = new PrintStream(os);

            out.println("Welcome to the ServerChat!");
            String input = in.nextLine();
            while (!input.equals("bye")) {
                server.sendAll(input);
                input = in.nextLine();
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}