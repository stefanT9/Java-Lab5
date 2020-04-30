import java.io.IOException;

public class PlayerThread implements  Runnable{
    Player player;
    GameController gameController;

    public PlayerThread(Player player, GameController game) {
        this.player=player;
        this.gameController=game;
    }

    @Override
    public void run() {

        while(gameController.getWinner()==null)
        {
            try {
                String request = player.getRequest();
                try {
                    int row = Integer.parseInt(request.split(",")[0]);
                    int col = Integer.parseInt(request.split(",")[1]);
                    System.out.println(request);
                    gameController.makeMove(row,col,player);
                }
                catch (Exception e)
                {
                    System.out.println(e);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
