import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server {
    // Define the port on which the server is listening
    public static final int PORT = 8100;
    public static Boolean serverIsOn=Boolean.TRUE;

    public static void main ( String [] args ) throws IOException {
        try {
            ThreadPoolExecutor pool = new ThreadPoolExecutor(10,20,10, TimeUnit.SECONDS,new LinkedBlockingQueue<>());
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (serverIsOn) {
                System.out.println("Waiting for a client ...");
                Socket socket1 = serverSocket.accept();
                Socket socket2 = serverSocket.accept();
                pool.execute(new GameThread(socket1,socket2));
            }
        } catch (IOException e) {
            System.err.println("Ooops... " + e);
        }
    }
}