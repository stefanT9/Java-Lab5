package com.company;

public class Token {
    int val;

    @Override
    public String toString() {
        return "Token{" +
                "val=" + val +
                '}';
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Token(int val) {
        this.val = val;
    }
}
