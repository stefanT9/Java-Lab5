public class Board {
    SquareType[][] squares;
    public Board(){
        squares=new SquareType[15][15];
        for (int i=0;i<15;i++)
        {
            for (int j=0;j<15;j++)
            {
                squares[i][j]=SquareType.Free;
            }
        }
    }

    public void makeMove(int row, int col, SquareType type) {
        squares[row][col]=type;
    }

    @Override
    public String toString() {
        StringBuilder res= new StringBuilder();
        for(int i=0; i<15; i++)
        {
            for(int j=0;j<15;j++)
            {
                switch (squares[i][j])
                {
                    case Free:{
                        res.append("0");
                        break;
                    }
                    case Black:{
                        res.append("1");
                        break;
                    }
                    case White:{
                        res.append("2");
                        break;
                    }
                }
            }
        }
        return res.toString();
    }
}
