import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ChatServer {
    private ServerSocket serverSocket;
    private ArrayList<Client> clients;
    public ChatServer() throws IOException {
        serverSocket = new ServerSocket(1234);
        clients = new ArrayList<>();
    }

    void sendAll(String message) {
        for (Client client : clients) {
            client.receive(message);
        }
    }


    public void run() {
        while (true) {
            System.out.println("Waiting...");
            try  {
                Socket socket = serverSocket.accept();
                clients.add(new Client(socket, this));
                System.out.println("Client connected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        new ChatServer().run();
    }
}

