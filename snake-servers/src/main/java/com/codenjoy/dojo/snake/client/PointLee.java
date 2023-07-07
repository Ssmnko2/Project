package com.codenjoy.dojo.snake.client;

import java.util.Objects;

public class PointLee {

    int x;
    int y;

    int value;

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static int compare (PointLee p1, PointLee p2) {
        if (p1.getValue() > p2.getValue())
            return 1;
        return -1;
    }

    public PointLee(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public PointLee(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }



    public PointLee() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointLee pointLee = (PointLee) o;
        return x == pointLee.x && y == pointLee.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public PointLee move(int dx, int dy) {
        return new PointLee(x + dx, y + dy);
    }


}
