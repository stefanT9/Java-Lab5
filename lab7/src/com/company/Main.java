package com.company;

import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        var tokens=new LinkedList<Token>();
        Board board=new Board(tokens,10);

        Player p1=new Player("p1",null);
        Player p2=new Player("p2", null);

        for(int i=0;i<100000;i++)
        {
            int n= (int) (Math.random()*100);
            var token=new Token(n);
            tokens.add(token);
        }
        Game game=new Game(board,p1,p2,2000.0);

        game.run();
        System.out.println(game.getTokenList1().size());
        System.out.println(game.getTokenList2().size());
    }
}
