package com.codenjoy.dojo.snake.client;

public class Turn {
    int turn;
    int k1;
    int k2;

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public int getK1() {
        return k1;
    }

    public void setK1(int k1) {
        this.k1 = k1;
    }

    public int getK2() {
        return k2;
    }

    public void setK2(int k2) {
        this.k2 = k2;
    }

    public Turn() {
        this.turn = -1;
        this.k1 = 0;
        this.k2 = 1;
    }
}
