package com.example.cours_work;



import java.util.ArrayList;

public class SnakeGame extends GameMap{

    final static public int UP = 1;
    final static public int RIGHT = 2;
    final static public int DOWN = 3;
    final static public int LEFT = 4;

    public int Score = 0;

    private ArrayList<position> mSnakeArr = new ArrayList<position>();

    private static int mDirection;
    private int colGrowing = 1;
    private int isGrowing = 0;

    SnakeGame(){
        mDirection = RIGHT;
        mSnakeArr.add(new position(6,7));
        mSnakeArr.add(new position(7,7));
        mSnakeArr.add(new position(8,7));
    }

    public boolean nextStep(){
        switch (mDirection) {
            case UP:
                int nextX = mSnakeArr.get(mSnakeArr.size() - 1).x;
                int nextY = mSnakeArr.get(mSnakeArr.size() - 1).y - 1;

                if ((nextY >= 0) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnakeArr.get(0).x][mSnakeArr.get(0).y] = 0;
                        mSnakeArr.remove(0);
                    }
                    mSnakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if((nextY >= 0) && mField[nextX][nextY] == 1){
                    return false;
                } else if((nextY >= 0) && mField[nextX][nextY] > 1){
                    isGrowing += colGrowing;
                    Score += 10;
                    mField[nextX][nextY] = 0;
                    addFruite();
                    return true;
                } else if(nextY >= 0 && mSnakeArr.get(mSnakeArr.size() - 2).x == nextX &&  mSnakeArr.get(mSnakeArr.size() - 2).y == nextY){
                return true;
            }else {
                    return false;
                }

            case RIGHT:
                nextX = mSnakeArr.get(mSnakeArr.size() - 1).x + 1;
                nextY = mSnakeArr.get(mSnakeArr.size() - 1).y;

                if ((nextX < mFieldX) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnakeArr.get(0).x][mSnakeArr.get(0).y] = 0;
                        mSnakeArr.remove(0);
                    }
                    mSnakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX < mFieldX) && mField[nextX][nextY] > 1) {
                    isGrowing += colGrowing;
                    Score += 10;
                    mField[nextX][nextY] = 0;
                    addFruite();
                    return true;
                } else if(nextX < mFieldX && mSnakeArr.get(mSnakeArr.size() - 2).x == nextX &&  mSnakeArr.get(mSnakeArr.size() - 2).y == nextY){
                    return true;
                }else {
                    return false;
                }

            case DOWN:
                nextX = mSnakeArr.get(mSnakeArr.size() - 1).x;
                nextY = mSnakeArr.get(mSnakeArr.size() - 1).y + 1;

                if ((nextY < mFieldY) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnakeArr.get(0).x][mSnakeArr.get(0).y] = 0;
                        mSnakeArr.remove(0);
                    }
                    mSnakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextY < mFieldY) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextY < mFieldY) && mField[nextX][nextY] > 1) {
                    isGrowing += colGrowing;
                    Score += 10;
                    mField[nextX][nextY] = 0;
                    addFruite();
                    return true;
                } else if(nextY < mFieldY && mSnakeArr.get(mSnakeArr.size() - 2).x == nextX &&  mSnakeArr.get(mSnakeArr.size() - 2).y == nextY){
                    return true;
                }else {
                    return false;
                }
            case LEFT:
                nextX = mSnakeArr.get(mSnakeArr.size() - 1).x - 1;
                nextY = mSnakeArr.get(mSnakeArr.size() - 1).y;

                if ((nextX >= 0) && mField[nextX][nextY] == 0) {
                    if (isGrowing > 0) {
                        isGrowing--;
                    } else {
                        mField[mSnakeArr.get(0).x][mSnakeArr.get(0).y] = 0;
                        mSnakeArr.remove(0);
                    }
                    mSnakeArr.add(new position(nextX, nextY));
                    mField[nextX][nextY] = -1;
                    return true;
                } else if ((nextX >= 0) && mField[nextX][nextY] == 1) {
                    return false;
                } else if ((nextX >= 0) && mField[nextX][nextY] > 1) {
                    isGrowing += colGrowing;
                    Score += 10;
                    mField[nextX][nextY] = 0;
                    addFruite();
                    return true;
                } else if(nextX >= 0 && mSnakeArr.get(mSnakeArr.size() - 2).x == nextX &&  mSnakeArr.get(mSnakeArr.size() - 2).y == nextY){
                    return true;
                }else {
                    return false;
                }
        }
        return false;
    }

    public void setDirection(int direction) {
            SnakeGame.mDirection = direction;
    }

    public int getDirection(){
        return mDirection;
    }

    public int getSnakeSize() {
        return  mSnakeArr.size();
    }

    public ArrayList<position> getmSnake() {
        return mSnakeArr;
    }

}
