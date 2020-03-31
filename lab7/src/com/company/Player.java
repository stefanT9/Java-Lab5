package com.company;

public class Player implements Runnable {

    String name;
    Game game;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    Player(String name, Game game)
    {
        this.name=name;
        this.game=game;
    }
    private void makeMove()
    {
        try {
            game.selectRandomToken(this);
        }
        catch (Exception ignored){
            System.out.println("exceptie la player "+name);
        }
    }
    @Override
    public void run() {
        while (!game.getGameOver())
        {
            makeMove();
        }
    }
}
