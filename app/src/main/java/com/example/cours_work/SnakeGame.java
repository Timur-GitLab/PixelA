package com.example.cours_work;



import java.util.ArrayList;

public class SnakeGame extends GameMap{

    final static public int UP = 1;
    final static public int RIGHT = 2;
    final static public int DOWN = 3;
    final static public int LEFT = 4;

    int headX,headY;

    public int Score = 0;

    private ArrayList<position> snakeArr = new ArrayList<position>();

    private static int Direction;
    private int colGrowing = 1;
    private int isGrowing = 0;

    SnakeGame(){
        Direction = RIGHT;
        snakeArr.add(new position(6,7));
        snakeArr.add(new position(7,7));
        snakeArr.add(new position(8,7));
    }

    public boolean Step(){

        headX = getSnake().get(getSnakeSize()-1).x;
        headY = getSnake().get(getSnakeSize()-1).y;

        switch (getDirection()) {

            case UP:
                int nextX = snakeArr.get(snakeArr.size() - 1).x;
                int nextY = snakeArr.get(snakeArr.size() - 1).y - 1;


                if(headY >= 0 && nextY != -1) {
                    if (mField[nextX][nextY] == 1) {
                        return false;
                    }
                    if (nextX == snakeArr.get(snakeArr.size() - 2).x && nextY == snakeArr.get(snakeArr.size() - 2).y) {
                        return true;
                    }else if(mField[nextX][nextY] == -1 || mField[nextX][nextY] == -2){
                        return false;
                    }

                    if (headX == fruitX && headY == fruitY) {
                        isGrowing += colGrowing;
                        Score += 10;
                        addFruite();
                        return true;
                    }

                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[snakeArr.get(0).x][snakeArr.get(0).y] = 0;
                        snakeArr.remove(0);
                    }
                    snakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;

                    return true;
                }else{
                    return false;
                }

            case RIGHT:
                nextX = snakeArr.get(snakeArr.size() - 1).x + 1;
                nextY = snakeArr.get(snakeArr.size() - 1).y;

                if(headX < mFieldX && nextX != 18) {
                    if (mField[nextX][nextY] == 1) {
                        return false;
                    }
                    if (nextX == snakeArr.get(snakeArr.size() - 2).x && nextY == snakeArr.get(snakeArr.size() - 2).y) {
                        return true;
                    }else if(mField[nextX][nextY] == -1 || mField[nextX][nextY] == -2){
                        return false;
                    }

                    if (headX == fruitX && headY == fruitY) {
                        isGrowing += colGrowing;
                        Score += 10;
                        addFruite();
                        return true;
                    }

                        if (isGrowing > 0) {
                            isGrowing--;
                        } else {
                            mField[snakeArr.get(0).x][snakeArr.get(0).y] = 0;
                            snakeArr.remove(0);
                        }
                        snakeArr.add(new position(nextX, nextY));
                        mField[nextX][nextY] = -1;

                    return true;
                }else{
                    return false;
                }

            case DOWN:
                nextX = snakeArr.get(snakeArr.size() - 1).x;
                nextY = snakeArr.get(snakeArr.size() - 1).y + 1;

                if(headY < mFieldY && nextY != 32) {
                    if (mField[nextX][nextY] == 1) {
                        return false;
                    }
                    if (nextX == snakeArr.get(snakeArr.size() - 2).x && nextY == snakeArr.get(snakeArr.size() - 2).y) {
                        return true;
                    }else if(mField[nextX][nextY] == -1 || mField[nextX][nextY] == -2){
                        return false;
                    }

                    if (headX == fruitX && headY == fruitY) {
                        isGrowing += colGrowing;
                        Score += 10;
                        addFruite();
                        return true;
                    }

                        if (isGrowing > 0) {
                            isGrowing--;
                        } else {
                            mField[snakeArr.get(0).x][snakeArr.get(0).y] = 0;
                            snakeArr.remove(0);
                        }
                        snakeArr.add(new position(nextX, nextY));
                        mField[nextX][nextY] = -1;

                    return true;
                }else{
                    return false;
                }

            case LEFT:
                nextX = snakeArr.get(snakeArr.size() - 1).x - 1;
                nextY = snakeArr.get(snakeArr.size() - 1).y;

                if(headX >= 0 && nextX != -1) {
                    if (mField[nextX][nextY] == 1) {
                        return false;
                    }
                    if (nextX == snakeArr.get(snakeArr.size() - 2).x && nextY == snakeArr.get(snakeArr.size() - 2).y) {
                        return true;
                    }else if(mField[nextX][nextY] == -1 || mField[nextX][nextY] == -2){
                        return false;
                    }

                    if (headX == fruitX && headY == fruitY) {
                        isGrowing += colGrowing;
                        Score += 10;
                        addFruite();
                        return true;
                    }

                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[snakeArr.get(0).x][snakeArr.get(0).y] = 0;
                        snakeArr.remove(0);
                    }
                    snakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;

                    return true;
                }else{
                    return false;
                }
        }
        return false;
    }

    public void setDirection(int direction) {
            SnakeGame.Direction = direction;
    }

    public int getDirection(){
        return Direction;
    }

    public int getSnakeSize() {
        return  snakeArr.size();
    }

    public ArrayList<position> getSnake() {
        return snakeArr;
    }

}
