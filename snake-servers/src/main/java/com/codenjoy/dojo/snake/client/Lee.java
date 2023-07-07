package com.codenjoy.dojo.snake.client;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.codenjoy.dojo.services.Point;

import javax.swing.text.html.Option;


public class Lee {


    int[][] boardLee = new int[15][15];

    private void obstruction(Board b) {
        b.getSnake().stream().forEach(p1 -> boardLee[p1.getX()][p1.getY()] = -9);
    }

    private void obstruction1(PointLee p) {
        boardLee[p.getX()][p.getY()] = -9;
    }

    private boolean isOnBoard(PointLee point) {
        return point.getX() > 0 && point.getY() > 0 && point.getX() < 14 && point.getY() < 14;
    }

    public PointLee poinMyToPointLee(PointMy p) {
        return new PointLee(p.getX(), p.getY());
    }

    private final Supplier<Stream<PointLee>> deltas = () -> Stream.of(
            new PointLee(-1, 0),
            new PointLee(1, 0),
            new PointLee(0, -1),
            new PointLee(0, 1)
    );

    public Stream<PointLee> neighbours(PointLee p, int value) {
        return deltas.get().
                map(d -> p.move(d.getX(), d.getY())).
                filter(p0 -> isOnBoard(p0)).
                filter(p0 -> boardLee[p0.getX()][p0.getY()] == value);
    }

    /*private Path volumeDdetermination(int[][] boardLee, int key) {
        if (key == 1) {


            Supplier<Stream<Integer>> s = () -> IntStream.range(0, 14).boxed();
            Supplier<Stream<PointLee>> stP = () -> s.get().flatMap((y -> s.get().map(x -> new PointLee(x, y, boardLee[x][y]))));
            long v1 = stP.get().filter(s1 -> (s1.getX() > 0 && s1.getX() <= 7) && (s1.getY() > 0 && s1.getY() <= 7) && s1.getValue() > 0).count();
            long v2 = stP.get().filter(s1 -> (s1.getX() > 0 && s1.getX() <= 7) && (s1.getY() > 7 && s1.getY() <= 14) && s1.getValue() > 0).count();
            long v3 = stP.get().filter(s1 -> (s1.getX() > 7 && s1.getX() <= 14) && (s1.getY() > 7 && s1.getY() <= 14) && s1.getValue() > 0).count();
            long v4 = stP.get().filter(s1 -> (s1.getX() > 7 && s1.getX() <= 14) && (s1.getY() > 0 && s1.getY() <= 7) && s1.getValue() > 0).count();

//        System.out.printf(" v1 = %d, v2 = %d, v3 = %d, v4 = %d\n", v1, v2, v3, v4);
            int vmax = IntStream.of((int) v1, (int) v2, (int) v3, (int) v4).reduce(Integer::max).getAsInt();

            System.out.println("vmax =" + vmax);
            PointLee pMax = new PointLee();
            if (vmax == (int) v1)
                pMax = stP.get().filter(s1 -> (s1.getX() > 0 && s1.getX() <= 7) && (s1.getY() > 0 && s1.getY() <= 7) && s1.getValue() > 0).
                        max(PointLee::compare).get();
            if (vmax == (int) v2)
                pMax = stP.get().filter(s1 -> (s1.getX() > 0 && s1.getX() <= 7) && (s1.getY() > 7 && s1.getY() <= 14) && s1.getValue() > 0).
                        max(PointLee::compare).get();
            if (vmax == (int) v3)
                pMax = stP.get().filter(s1 -> (s1.getX() > 7 && s1.getX() <= 14) && (s1.getY() > 7 && s1.getY() <= 14) && s1.getValue() > 0).
                        max(PointLee::compare).get();
            if (vmax == (int) v4)
                pMax = stP.get().filter(s1 -> (s1.getX() > 7 && s1.getX() <= 14) && (s1.getY() > 0 && s1.getY() <= 7) && s1.getValue() > 0).
                        max(PointLee::compare).get();

      *//*  System.out.printf(" pMax    x = %d, y=%d, value = %d\n", pMax.getX(), pMax.getY(), pMax.getValue());
        System.out.println("Finish");*//*
            return new Path(pMax, -100);
        } else
            return new Path(-100);


    }*/

    private void zeroArr() {
        for (int i = 0; i <= 14; i++)
            for (int j = 0; j <= 14; j++)
                boardLee[i][j] = 0;
    }


    public Path trace(PointLee start, PointLee finish, Board board, PointLee appleOrStone) {
        int v = 0;
        zeroArr();
        Path path1 = new Path();
        obstruction(board);
        obstruction1(appleOrStone);
        boardLee[start.getX()][start.getY()] = 1;
        boolean found = false;
        int counter[] = {1};

        Set<PointLee> currArr = new HashSet<>();
        currArr.add(start);
        while (!(found || currArr.isEmpty())) {
            counter[0]++;
            Set<PointLee> next = currArr.stream().
                    flatMap(p -> neighbours(p, 0)).
                    collect(Collectors.toSet());
            next.forEach(p -> boardLee[p.getX()][p.getY()] = counter[0]);
            v += next.size();

            currArr = next;
            if (next.stream().filter(n -> n.getX() == finish.getX() && n.getY() == finish.getY()).count() > 0)
                found = true;
        }


        if (currArr.isEmpty()) {
            path1.setPath(-100);
            return path1;
        }


        LinkedList<PointLee> path = new LinkedList<>();
        path.add(finish);
        boardLee[finish.getX()][finish.getY()] = -8;
        PointLee curr = finish;
        while (counter[0] > 2) {
            counter[0]--;
            PointLee prev = neighbours(curr, counter[0]).collect(Collectors.toList()).get(0);
            boardLee[prev.getX()][prev.getY()] = -8;
            if (prev == start) break;
            path.addFirst(prev);
            curr = prev;
        }
        path1.setPath(path.size());
        path1.setP(path.getFirst());
        System.out.println();
        path1.setV(v);
        System.out.println("VVVVVVVVV = " + v);

//        boardLee[path1.getP().getX()][path1.getP().getY()] = -8;

        return path1;

//        return Optional.empty();
    }

    private boolean free(PointLee p, Board b) {
        boolean b1, b2;
        if (((p.getX() > 0) && (p.getX() <= 13)) && ((p.getY() > 0) && (p.getY() <= 13))) b1 = true;
        else b1 = false;
        if (b.getSnake().stream().filter(p1 -> p.getX() == p1.getX() && p.getY() == p1.getY()).count() == 0) b2 = true;
        else b2 = false;
        return (b1 && b2) ? true : false;
    }

    private int scopeFreedom(PointLee start, Board b) {
        int v = 0;
        if (b.getSnake().stream().filter(p1 -> p1.getX() == start.getX() && p1.getY() == start.getY()).count() == 0) {
            zeroArr();
            obstruction(b);
            boardLee[start.getX()][start.getY()] = 1;
            int counter[] = {1};
            Set<PointLee> currArr = new HashSet<>();
            currArr.add(start);
            while (!currArr.isEmpty()) {
                counter[0]++;
                Set<PointLee> next = currArr.stream().
                        flatMap(p -> neighbours(p, 0)).
                        collect(Collectors.toSet());
                next.forEach(p -> boardLee[p.getX()][p.getY()] = counter[0]);
                v += next.size();
//                System.out.println(" scoreFreedom v = " + v);
                currArr = next;
            }
        }
        return v;
    }

    public Optional<String> bottleNeck(PointLee pCur, PointLee pPrev, Board board, Path path, String sTurn, int key) {
        System.out.println(" *************************************START BOTTLE**************************************");
        System.out.printf(" xCur = %d, yCur = %d, xPrev = %d, yPrev = %d\n", pCur.getX(), pCur.getY(), pPrev.getX(), pPrev.getY());
        String s = sTurn;
        PointLee p1 = new PointLee();
        PointLee p2 = new PointLee();
        PointLee p3 = new PointLee();
        boolean free1, free2, free3;
        int v1, v2, v3;
        String sv1 = null;
        String sv2 = null;
        String sv3 = null;

        if (pCur.getX() > pPrev.getX()) {
            p1.setX(pCur.getX());
            p1.setY(pCur.getY() + 1);

            p2.setX(pCur.getX() + 1);
            p2.setY(pCur.getY());

            p3.setX(pCur.getX());
            p3.setY(pCur.getY() - 1);

            sv1 = "UP";
            sv3 = "DOWN";
            sv2 = "RIGHT";

        }
        if (pCur.getX() < pPrev.getX()) {
            p1.setX(pCur.getX());
            p1.setY(pCur.getY() + 1);

            p2.setX(pCur.getX() - 1);
            p2.setY(pCur.getY());

            p3.setX(pCur.getX());
            p3.setY(pCur.getY() - 1);
            sv1 = "UP";
            sv3 = "DOWN";
            sv2 = "LEFT";
        }
        if (pCur.getY() > pPrev.getY()) {
            p1.setX(pCur.getX() - 1);
            p1.setY(pCur.getY());

            p2.setX(pCur.getX());
            p2.setY(pCur.getY() + 1);

            p3.setX(pCur.getX() + 1);
            p3.setY(pCur.getY());
            sv1 = "LEFT";
            sv3 = "RIGHT";
            sv2 = "UP";
        }
        if (pCur.getY() < pPrev.getY()) {
            p1.setX(pCur.getX() - 1);
            p1.setY(pCur.getY());

            p2.setX(pCur.getX());
            p2.setY(pCur.getY() - 1);

            p3.setX(pCur.getX() + 1);
            p3.setY(pCur.getY());

            sv1 = "LEFT";
            sv3 = "RIGHT";
            sv2 = "DOWN";
        }





       /* if (pCur.getY() == pPrev.getY()) {
            p1.setX(pCur.getX() - 1);
            p1.setY(pCur.getY());

            p3.setX(pCur.getX() + 1);
            p3.setY(pCur.getY());

            if (pCur.getX() > pPrev.getY()) {
                p2.setX(pCur.getX());
                p2.setY(pCur.getY() + 1);
            } else {
                p2.setX(pCur.getX());
                p2.setY(pCur.getY() - 1);
            }
        } else {
            p1.setX(pCur.getX());
            p1.setY(pCur.getY() + 1);

            p3.setX(pCur.getX());
            p3.setY(pCur.getY() - 1);

            if (pCur.getY() > pPrev.getY()) {
                p2.setX(pCur.getX());
                p2.setY(pCur.getY() + 1);
            } else {
                p2.setX(pCur.getX());
                p2.setY(pCur.getY() - 1);
            }
        }*/
        /*free1 = free(p1, board);
        free2 = free(p2, board);
        free3 = free(p3, board);*/

        System.out.printf(" p1x = %d, p1y = %d, p2x = %d, p2y = %d, p3x = %d, p3y = %d\n", p1.getX(), p1.getY(), p2.getX(), p2.getY(), p3.getX(), p3.getY());

        v1 = scopeFreedom(p1, board);
        v2 = scopeFreedom(p2, board);
        v3 = scopeFreedom(p3, board);

        Supplier<Stream<Integer>> st = () -> IntStream.of(v1, v2, v3).filter(v -> v > 0).boxed();
        int vmin;
        int vmax;
        int st0 = (int) IntStream.of(v1, v2, v3).filter(v -> v == 0).count();
        if (st0 == 3) {
            vmin = 0;
            vmax = 0;
        }
        else {
            vmin = st.get().reduce(Integer::min).get();
            vmax = st.get().reduce(Integer::max).get();

        }
        System.out.println("Vmin =========== " + vmin);
        System.out.println("Vmax =========== " + vmax);
        /*long st1 = st.get().filter(v -> v == v1).count();
        long st2 = st.get().filter(v -> v == v2).count();
        long st3 = st.get().filter(v -> v == v3).count();*/
        String smin = null;
        String smax = null;
        if (vmin == v1) smin = sv1;
        if (vmin == v2) smin = sv2;
        if (vmin == v3) smin = sv3;
        if (vmax == v1) smax = sv1;
        if (vmax == v2) smax = sv2;
        if (vmax == v3) smax = sv3;

        System.out.printf(" v1 = %d, v2 = %d, v3 = %s\n", v1, v2, v3);
//        System.out.printf(" st1 = %d, st2 = %d, st3 =%d\n", st1, st2, st3);

        System.out.println(" sTurn    ====== ===  " + sTurn);
        System.out.println(" smin     ====== ===  " + smin);

        if (key == 0) {
//            if (vmin == vmax) s = smax;
            if (((v1 != v2) && (v1 != v3) && (v2 != v3)) || (st0 == 2)) {
                if (v1 > v2 && v1 > v3) {
                    if (pCur.getX() == pPrev.getX()) s = "LEFT";
                    else s = "UP";
                }
                if (v2 > v1 && v2 > v3) {
                    if (pCur.getX() == pPrev.getX() && pCur.getY() > pPrev.getY()) s = "UP";
                    if (pCur.getX() == pPrev.getX() && pCur.getY() < pPrev.getY()) s = "DOWN";
                    if (pCur.getY() == pPrev.getY() && pCur.getX() > pPrev.getX()) s = "RIGHT";
                    if (pCur.getY() == pPrev.getY() && pCur.getX() < pPrev.getX()) s = "LEFT";
                }
                if (v3 > v1 && v3 > v2) {
                    if (pCur.getX() == pPrev.getX()) s = "RIGHT";
                    else s = "DOWN";
                }
                System.out.println(" TURN = " + s);

            }
        } else {
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.printf(" vmin = %d, size/3 = %d\n", vmin, board.getSnake().size()/3);
            System.out.println("Turn = " + sTurn);
            System.out.println(" smin = " + smin);
            System.out.println(" smax = " + smax);
            if (sTurn.equals(smin) && (vmin < board.getSnake().size()/3)) {
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.println("-----------------------------------------------------------------------------------------");
                System.out.printf(" vmin = %d, size/3 = %d\n", vmin, board.getSnake().size()/3);
                System.out.println("Turn = " + sTurn);
                System.out.println(" smin = " + smin);
                System.out.println(" smax = " + smax);
                s = smax;
            }
        }


        return Optional.of(s);

       /* if (free1 && free3 && !free2) {
            v1 = scopeFreedom(p1, board);
            v3 = scopeFreedom(p3, board);

            System.out.printf(" v1 = %d, v3 = %d\n", v1, v3);

            if ((v1 > v3) && (pCur.getY() == pPrev.getY())) s = "Left"; else s = "RIGHT";
            if ((v1 > v3) && (pCur.getX() == pPrev.getX())) s = "UP"; else s = "DOWN";
            return Optional.of(s);
        } else return Optional.empty();*/
    }
}

