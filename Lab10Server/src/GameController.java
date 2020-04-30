public class GameController {
    private Player player1;
    private Player player2;
    private Board board;
    private int turn;

    public GameController(Player player1, Player player2, Board board) {
        this.player1=player1;
        this.player2=player2;
        this.board=board;
        this.turn=1;
    }

    public void makeMove(int row, int col, Player player)
    {
        if(!isValidMove(row,col))
        {
            player.sendResponse("the move is invalid!");
        }
        if(player==player1 && turn==1)
        {
            board.makeMove(row,col,player.getType());
            turn=2;
            player.sendResponse(board.toString());
        }
        else if (player==player2 && turn==2)
        {
            board.makeMove(row,col,player.getType());
            turn=1;
            player.sendResponse(board.toString());
        }
        else
        {
            player.sendResponse("its not your turn!");
        }
    }

    public Player getWinner()
    {
        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                int c1=0;
                int c2=0;

                for(int k=0;k<5;k++)
                {
                    try{
                        if(board.squares[i][j+k]==player1.getType())
                        {
                            c1++;
                        }
                        if(board.squares[i][j+k]==player2.getType())
                        {
                            c2++;
                        }
                    }
                    catch (Exception ignored)
                    {}
                    if(c1==5) return player1;
                    if(c2==5) return player2;
                }
                c1=0;
                c2=0;

                for(int k=0;k<5;k++)
                {
                    try{
                        if(board.squares[i+k][j]==player1.getType())
                        {
                            c1++;
                        }
                        if(board.squares[i+k][j]==player2.getType())
                        {
                            c2++;
                        }
                    }
                    catch (Exception ignored)
                    {}
                    if(c1==5) return player1;
                    if(c2==5) return player2;
                }
            }
        }

        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                int c1=0;
                int c2=0;

                for(int k=0;k<5;k++)
                {
                    try{
                        if(board.squares[i+k][j+k]==player1.getType())
                        {
                            c1++;
                        }
                        if(board.squares[i+k][j+k]==player2.getType())
                        {
                            c2++;
                        }
                    }
                    catch (Exception ignored)
                    {}
                    if(c1==5) return player1;
                    if(c2==5) return player2;
                }
                c1=0;
                c2=0;

                for(int k=0;k<5;k++)
                {
                    try{
                        if(board.squares[i-k][j+k]==player1.getType())
                        {
                            c1++;
                        }
                        if(board.squares[i-k][j+k]==player2.getType())
                        {
                            c2++;
                        }
                    }
                    catch (Exception ignored)
                    {}
                    if(c1==5) return player1;
                    if(c2==5) return player2;
                }
            }
        }
        return  null;
    }

    private boolean isValidMove(int row, int col) {
        return (0<=row&&row<15&&0<=col&&col<15&& board.squares[row][col]==SquareType.Free);
    }
}
