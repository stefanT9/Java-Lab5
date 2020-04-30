import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class GameClient {

    static int PORT = 8100;
    static String serverAddress = "127.0.0.1"; // The server's IP address

    public static void main(String[] args) throws IOException {
        try {
            Socket socket = new Socket(serverAddress, PORT);
            PrintWriter out =
                    new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            // Send a request to the server

            BufferedReader consoleInput = new BufferedReader( new InputStreamReader(System.in));

            while(true) {
                String request = consoleInput.readLine();
                Integer row,col;

                try {
                    row= Integer.parseInt(request.split(",")[0]);
                    col= Integer.parseInt(request.split(",")[1]);
                }
                catch (Exception e)
                {
                    System.out.println("formatul ar trebui sa fie %d,$d");
                    continue;
                }

                out.println(request);
                String response = in.readLine();
                System.out.println(response.length());
                if(response.length()==15*15)
                {
                    for(int i=0;i<response.length();i++)
                    {
                        if((i%15)==0) {
                            System.out.print("\n");
                        }
                        switch (response.charAt(i))
                        {
                            case 0:{
                                System.out.print(" ");
                                break;
                            }
                            case 1:{
                                System.out.print("⚪");
                                break;
                            }
                            case 2:{
                                System.out.print("⚫");
                                break;
                            }
                        }
                        System.out.print(response.charAt(i));
                    }
                }
                if(response.contains("winner"))
                {
                    break;
                }
            }
        } catch (
                UnknownHostException e) {
            System.err.println("No server listening... " + e);
        }
    }
}
