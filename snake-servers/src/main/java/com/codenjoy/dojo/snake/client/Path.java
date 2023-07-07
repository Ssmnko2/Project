package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;

public class Path {
    PointLee p;

    int path;
    int v;




    public Path(PointLee p, int path) {
        this.p = p;
        this.path = path;
    }



    public Path() {
    }

    public int getV() {
        return v;
    }

    public void setV(int v) {
        this.v = v;
    }

    public Path(int path) {
        this.path = path;
    }

    public PointLee getP() {
        return p;
    }

    public void setP(PointLee p) {
        this.p = p;
    }

    public int getPath() {
        return path;
    }

    public void setPath(int path) {
        this.path = path;
    }

}
