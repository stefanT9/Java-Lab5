package com.company;

import java.util.LinkedList;
import java.util.List;

public class Game implements Runnable{

    private Board board;
    private Player p1,p2;
    private List<Token> tokenList1,tokenList2;
    private volatile Boolean gameOver;
    private double timeLimit;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getP1() {
        return p1;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public Player getP2() {
        return p2;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Game(Board board, Player p1, Player p2,Double timeLimit) {
        this.board = board;
        this.p1 = p1;
        this.p2 = p2;
        this.timeLimit=timeLimit;
        this.tokenList1=new LinkedList<>();
        this.tokenList2=new LinkedList<>();
        this.gameOver=false;
        this.p1.setGame(this);
        this.p2.setGame(this);
    }

    @Override
    public String toString() {
        return "Game{" +
                "board=" + board +
                ", p1=" + p1 +
                ", p2=" + p2 +
                ", tokenList1=" + tokenList1 +
                ", tokenList2=" + tokenList2 +
                ", gameOver=" + gameOver +
                ", timeLimit=" + timeLimit +
                '}';
    }

    public synchronized void selectRandomToken(Player player)
    {
           int randInt = (int) Math.floor(Math.random() * (board.getTokens().size()-1));
           var randToken = board.getTokens().get(randInt);
           board.getTokens().remove(randInt);

           if (player == p1) {
               tokenList1.add(randToken);
           } else {
               tokenList2.add(randToken);
           }

           if (this.board.getTokens().size() == 0) {
               gameOver = true;
           }

    }

    public List<Token> getTokenList1() {
        return tokenList1;
    }

    public void setTokenList1(List<Token> tokenList1) {
        this.tokenList1 = tokenList1;
    }

    public List<Token> getTokenList2() {
        return tokenList2;
    }

    public void setTokenList2(List<Token> tokenList2) {
        this.tokenList2 = tokenList2;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(double timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public void run() {
        var t1=new Thread(p1);
        t1.start();
        var t2=new Thread(p2);
        t2.start();

        new Thread(()->{
            try {
                Thread.sleep((long) timeLimit);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setGameOver(true);
        }).start();
        while (!gameOver) {
            Thread.onSpinWait();
        }
        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
    }
}
