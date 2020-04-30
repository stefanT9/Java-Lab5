import java.io.IOException;
import java.net.Socket;

public class GameThread implements Runnable {
    Player player1;
    Player player2;
    public GameThread(Socket socket1, Socket socket2) throws IOException {
        player1 = new Player(socket1, SquareType.White);
        player2 = new Player(socket2, SquareType.Black);
    }

    @Override
    public void run() {
        Board board = new Board();
        GameController game = new GameController(player1,player2,board);
        PlayerThread playerThread1=new PlayerThread(player1,game);
        PlayerThread playerThread2=new PlayerThread(player2,game);
        var t1=new Thread(playerThread1);
        var t2=new Thread(playerThread2);

        t1.start();
        t2.start();
        System.out.println("am dat startul la joc");
    }
}
