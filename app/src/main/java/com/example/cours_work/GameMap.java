package com.example.cours_work;

import java.util.Random;

public class GameMap {
    final Random random = new Random();
    final public int mFieldX = 18;
    final public int mFieldY = 32;
    protected int mField[][] = new int[mFieldX][mFieldY];

    public int fruitX;
    public int fruitY;

    private int[] bordersX = new int[]{1,1,1,1,1,1,1,1,1,1,1,1,2,2,3,3,4,4,5,5,5,5,5,5,5,5,6,6,6,6,7,7,10,10,11,11,11,11,12,12,12,12,12,12,12,12,13,13,14,14,15,15,16,16,16,16,16,16,16,16,16,16,16,16};
    private int[] bordersY = new int[]{1,2,3,4,5,6,25,26,27,28,29,30,1,30,1,30,1,30,1,12,13,14,17,18,19,30,1,12,19,30,12,19,12,19,1,12,19,30,1,12,13,14,17,18,19,30,1,30,1,30,1,30,1,2,3,4,5,6,25,26,27,28,29,30};

    public class position{
        int x;
        int y;

        position(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public GameMap(){
        int col = 0;
        for (int i = 0; i < mFieldX; i++) {
            for (int j = 0; j < mFieldY; j++) {
                if (col != 64) {
                    if (i == bordersX[col] && j == bordersY[col]) {
                        mField[i][j] = 1;
                        col++;
                    } else {
                        mField[i][j] = 0;
                    }
                } else {
                    mField[i][j] = 0;
                }
            }
        }
        mField[6][7] = -1;
        mField[7][7] = -1;
        mField[8][7] = -1;
        mField[6][24] = -2;
        mField[7][24] = -2;
        mField[8][24] = -2;
        addFruite();
    }

    public void addFruite(){
        boolean flag = false;
        while (!flag){
            int x = (int) (random.nextInt(mFieldX));
            int y = (int) (random.nextInt(mFieldY));
            if(mField[x][y] == 0){
                mField[x][y] = 2;
                System.out.println("X = " + x);
                System.out.println("Y = " + y);
                fruitX = x;
                fruitY = y;
                System.out.println("fruitXdo = " + fruitX);
                System.out.println("fruitYdo = " + fruitY);
                flag = true;
            }
        }
    }

    public int[][] getmField() {
        return mField;
    }
    public int[] getBordersY() {
        return bordersY;
    }
    public int[] getBordersX() {
        return bordersX;
    }
}
