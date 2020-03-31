package com.company;

import java.util.LinkedList;

public class Board {
    private LinkedList<Token> tokens;

    public LinkedList<Token> getTokens() {
        return tokens;
    }

    public void setTokens(LinkedList<Token> tokens) {
        this.tokens = tokens;
    }

    public Board(LinkedList<Token> tokens, int i) {
        this.tokens = tokens;
    }
}
