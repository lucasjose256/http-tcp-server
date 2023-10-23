import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(54321);
        System.out.println("A porta 54321 foi aberta");

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("Cliente " + socket.getInetAddress().getHostAddress() + " conectado");
            ThreadSockets thread = new ThreadSockets(socket);
            thread.start();
        }
    }
}