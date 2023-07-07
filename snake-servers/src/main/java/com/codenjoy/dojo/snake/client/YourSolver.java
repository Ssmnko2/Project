package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;

import java.awt.*;
import java.util.ArrayList;
import java.util.Optional;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public Turn t = new Turn();
    public PointMy pCurSnake = new PointMy();
    public PointMy pPrevSnake = new PointMy();

    String s;

    Optional<String> os;


    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
//        System.out.println(board.toString());


        if (!board.isGameOver()) {
            pCurSnake.setX(board.getHead().getX());
            pCurSnake.setY(board.getHead().getY());


            PointLee pHead = new PointLee(board.getHead().getX(), board.getHead().getY());
            PointLee pApple = new PointLee(board.getApples().get(0).getX(), board.getApples().get(0).getY());
            PointLee pStone = new PointLee(board.getStones().get(0).getX(), board.getStones().get(0).getY());


            Lee lee = new Lee();


            Path path = new Path();
            Path path1 = lee.trace(pHead, pApple, board, pStone);
            path = path1;
            Path path2 = lee.trace(pHead, pStone, board, pApple);
//            os = lee.bottleNeck(lee.poinMyToPointLee(pCurSnake), lee.poinMyToPointLee(pPrevSnake), board, path);
            System.out.printf(" xCur = %d, yCur = %d, xPrev = %d, yPrev = %d\n", pCurSnake.getX(), pCurSnake.getY(), pPrevSnake.getX(), pPrevSnake.getY());

            if (path1.getPath() == -100) {
                System.out.printf(" xCur = %d, yCur = %d, xPrev = %d, yPrev = %d\n", pCurSnake.getX(), pCurSnake.getY(), pPrevSnake.getX(), pPrevSnake.getY());
                s = My.nextStepMov(t, pCurSnake, pPrevSnake, board);
                os = lee.bottleNeck(lee.poinMyToPointLee(pCurSnake), lee.poinMyToPointLee(pPrevSnake), board, path, s, 0);
                if (os.isPresent()) s = os.get();
                pPrevSnake.setX(pCurSnake.getX());
                pPrevSnake.setY(pCurSnake.getY());
                return s;
            } else {

                if (board.getSnake().size() > 60)
                    if (path2.getPath() > 0 && path2.getPath() < 5)
                        path = path2;


                PointMy pCur = new PointMy(board.getHead().getX(), board.getHead().getY());
                PointMy pNext = new PointMy(path.getP().getX(), path.getP().getY());
                s = My.nextStep(pCur, pNext, pPrevSnake, path, board);
                pPrevSnake.setX(pCurSnake.getX());
                pPrevSnake.setY(pCurSnake.getY());
                return s;
            }
        } else return Direction.DOWN.toString();
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://206.81.21.158/codenjoy-contest/board/player/wfw657jgvcrcwmcstnxm?code=7945554915159148477",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
