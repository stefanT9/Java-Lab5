import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Player {
    private Socket socket;
    private SquareType type;
    private BufferedReader in;
    private PrintWriter out;

    public Player(Socket socket, SquareType type) throws IOException {
        this.socket = socket;
        this.type = type;
        this.in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
        this.out = new PrintWriter(socket.getOutputStream());
    }

    public SquareType getType() {
        return type;
    }

    public void setType(SquareType type) {
        this.type = type;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
    public String getRequest() throws IOException {
        return in.readLine();
    }
    public void sendResponse(String res)
    {
        out.println(res);
        out.flush();
    }

}
