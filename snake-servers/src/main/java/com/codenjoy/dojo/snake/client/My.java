package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class My {

    public static String nextStep(PointMy pCur, PointMy pNext, PointMy pPrev, Path path, Board board) {
        Lee lee = new Lee();
//        Optional<String> os = lee.bottleNeck(lee.poinMyToPointLee(pCur), lee.poinMyToPointLee(pPrev), board, path);
        String s = null;

        if (pCur.getX() - pNext.getX() > 0) s = "LEFT";
        if (pCur.getX() - pNext.getX() < 0) s = "RIGHT";
        if (pCur.getY() - pNext.getY() > 0) s = "DOWN";
        if (pCur.getY() - pNext.getY() < 0) s = "UP";
        Optional<String> os = lee.bottleNeck(lee.poinMyToPointLee(pCur), lee.poinMyToPointLee(pPrev), board, path, s, 1);
        if (os.isPresent()) s = os.get();


        return s;
    }

    private static PointMy nextStepCoordinate(Turn t, PointMy pCur, PointMy pPrev) {

        int xn, yn;
        PointMy pNext = new PointMy();


        System.out.printf(" xCur = %d, yCur = %d, xPrev = %d, yPrev = %d\n", pCur.getX(), pCur.getY(), pPrev.getX(), pPrev.getY());
        if (pCur.getX() == pPrev.getX()) {
            if (pCur.getY() > pPrev.getY()) {
                System.out.println("1");
                yn = pCur.getY() + t.k1;
                xn = pCur.getX() + t.turn * t.k2;
            } else {
                System.out.println("2");
                yn = pCur.getY() - t.k1;
                xn = pCur.getX() - t.turn * t.k2;
         /*       System.out.println(xn + " " + yn);
                System.out.printf(" turn = %d, k1 = %d, k2 = %d\n", t.turn, t.k1, t.k2);*/
            }
        } else {
            if (pCur.getX() > pPrev.getX()) {
                System.out.println("3");
                xn = pCur.getX() + t.k1;
                yn = pCur.getY() - t.turn * t.k2;
            } else {
                System.out.println("4");
                xn = pCur.getX() - t.k1;
                yn = pCur.getY() + t.turn * t.k2;
            }
        }
        pNext.setX(xn);
        pNext.setY(yn);
        System.out.println(xn + " " + yn);
        return pNext;
    }


    public static String nextStepMov(Turn t, PointMy pCur, PointMy pPrev, Board board) {
        Lee lee = new Lee();
        System.out.println("******************************************************************************************");


        PointMy pNext = nextStepCoordinate(t, pCur, pPrev);
        int xn = pNext.getX();
        int yn = pNext.getY();
        int count1;

        System.out.printf(" pCurX = %d, pCurY = %d, pNextX = %d, pNextY = %d\n", pCur.getX(), pCur.getY(), pNext.getX(), pNext.getY());
        if ((xn > 0 && xn <= 13) && (yn > 0 && yn <= 13)) count1 = 0;
        else count1 = 1;
        long count2 = board.getSnake().stream().filter(b -> (b.getX() == xn) && (b.getY() == yn)).count();



        System.out.printf(" count1 = %d, count2 = %d\n", count1, count2);
        int count3 = 1;
        String s = "LEFT";
        while (count1 > 0 || count2 > 0) {
            System.out.println(" Сработало!!");
            if (t.k1 == 0) {
                t.k1 = 1;
                t.k2 = 0;
            } else {
                t.turn = t.turn * (-1);
                t.k1 = 0;
                t.k2 = 1;
            }
            pNext = nextStepCoordinate(t, pCur, pPrev);

            int xn1, yn1;
            xn1 = pNext.getX();
            yn1 = pNext.getY();
            if ((xn1 > 0 && xn1 <= 13) && (yn1 > 0 && yn1 <= 13)) count1 = 0;
            else count1 = 1;
            count2 = board.getSnake().stream().filter(b -> (b.getX() == xn1) && (b.getY() == yn1)).count();


            System.out.println(" Отработало");
            System.out.printf(" pCurX = %d, pCurY = %d, pNextX = %d, pNextY = %d\n", pCur.getX(), pCur.getY(), pNext.getX(), pNext.getY());
            System.out.printf("count1 = %d, count2 = %d\n", count1, count2);
            count3++;
            System.out.println(" count 3 = " + count3);
            if (count3 > 2) break;


        }

        System.out.println(" Выход //////////////////////////////////////////");
        System.out.printf(" pCurX = %d, pCurY = %d, pNextX = %d, pNextY = %d\n", pCur.getX(), pCur.getY(), pNext.getX(), pNext.getY());

        if (pCur.getX() - pNext.getX() > 0) s = "LEFT";
        if (pCur.getX() - pNext.getX() < 0) s = "RIGHT";
        if (pCur.getY() - pNext.getY() > 0) s = "DOWN";
        if (pCur.getY() - pNext.getY() < 0) s = "UP";
        System.out.println("  S =" + s);
        t.k1 = 0;
        t.k2 = 1;



        return s;
    }

    public static void printIntArr(int[][] arr) {
        for (int j = 14; j >= 0; j--) {
            for (int i = 0; i <= 14; i++)
                System.out.printf("%3s", arr[i][j]);
            System.out.println();
        }
    }
}
