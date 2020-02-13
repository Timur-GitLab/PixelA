package com.example.cours_work;


import java.util.ArrayList;

public class SnakeBot extends SnakeGame{

    int headX, headY;

    private ArrayList<position> botSnakeArr = new ArrayList<position>();
    private ArrayList<position> fastWay = new ArrayList<position>();
    private int specField[][] = new int[mFieldX][mFieldY];
    private int colGrowingBot = 1;
    int isGrowing2 = 0, numOfnum;


    public int botScore = 0;

    boolean flag1;

    SnakeBot(){
        flag1 = true;
        botSnakeArr.add(new position(6,24));
        botSnakeArr.add(new position(7, 24));
        botSnakeArr.add(new position(8, 24));
    }

    public boolean Alive(){

        headX = getBotSnake().get(getBotSnakeSize()-1).x;
        headY = getBotSnake().get(getBotSnakeSize()-1).y;

        if(flag1) {
            fastWay.clear();
            WavePropagation(headX, headY);
            numOfnum = fastWay.size();
            flag1 = false;
        }else {
            for (int i = 0; i < fastWay.size();i++) {
                if (mField[fastWay.get(i).x][fastWay.get(i).y] == -1) {
                    fastWay.clear();
                    WavePropagation(headX, headY);
                    numOfnum = fastWay.size();
                }
            }
        }

        numOfnum--;
        if ((headX >= 0 && headY >= 0) && (mField[headX][headY] == -2)) {
            if((headX >= 0 && headY >= 0) && headX == fruitX && headY == fruitY) {
                isGrowing2 += colGrowingBot ;
                botScore += 10;
                mField[headX][headY] = -2;
                flag1 = true;
                addFruite();
                return true;
            }
            if (isGrowing2 > 0) {
                isGrowing2--;
            } else {
                mField[botSnakeArr.get(0).x][botSnakeArr.get(0).y] = 0;
                botSnakeArr.remove(0);
            }
            botSnakeArr.add(new position(fastWay.get(numOfnum).x, fastWay.get(numOfnum).y));
            mField[fastWay.get(numOfnum).x][fastWay.get(numOfnum).y] = -2;
            return true;
        } else if((headX >= 0 && headY >= 0) && mField[headX][headY] == 1){
            return false;
        } else {
            return false;
        }
    }

    public void WavePropagation(int fromX, int fromY) {
        for(int i = 0; i < mFieldX; i++){
            for(int j = 0; j < mFieldY; j++) {
                specField[i][j] = mField[i][j];
            }
        }
        ArrayList<position> NextCordinate = new ArrayList<position>();
        NextCordinate.add(new position(fromX,fromY));
        boolean exit = false;
        int markNumber = 1, finNextcoord = 0;
        specField[fromX][fromY] = markNumber;         // инициализация стартовой клетки
        while (!exit) {
            int i = finNextcoord;
            finNextcoord = NextCordinate.size();
            for (; i < finNextcoord; i++) {
                if (specField[NextCordinate.get(i).x][NextCordinate.get(i).y] == markNumber) {
                    for (int j = 0; j < 4; j++) {
                        if((NearX(j,NextCordinate.get(i).x) >=0) && (NearY(j,NextCordinate.get(i).y) >= 0) && (NearX(j,NextCordinate.get(i).x) < 18) && (NearY(j,NextCordinate.get(i).y) < 32)) {
                            if (specField[NearX(j, NextCordinate.get(i).x)][NearY(j, NextCordinate.get(i).y)] == 0 || (NearX(j, NextCordinate.get(i).x) == fruitX) && (NearY(j, NextCordinate.get(i).y) == fruitY)) {   // если доступен или равен яблоку
                                specField[NearX(j, NextCordinate.get(i).x)][NearY(j, NextCordinate.get(i).y)] = markNumber + 1;
                                NextCordinate.add(new position(NearX(j, NextCordinate.get(i).x), NearY(j, NextCordinate.get(i).y)));
                                if ((NearX(j, NextCordinate.get(i).x) == fruitX) && (NearY(j, NextCordinate.get(i).y) == fruitY)) {
                                    exit = true;
                                }
                            }
                        }
                    }
                }
            }
            markNumber++;
        }
        PathRecovery(fruitX,fruitY,specField,markNumber);
    }

    public void PathRecovery(int toX,int toY, int[][]specField, int markNumber) {
                                                                        // массив, где хранится путь
        boolean once;                                                                // еще раз проверяем дошел ли алгоритм до финишного узла
        fastWay.add(new position(toX,toY));                             // добавляем финишный узел к пути
        int currentNode = specField[toX][toY];
        for(int i = 0; i < markNumber - 2; i++) {   // пока не дошли до стартового узла
            once = true;
            for (int j = 0; j < 4; j++) {
                if((NearX(j,fastWay.get(i).x) >=0) && (NearY(j,fastWay.get(i).y) >= 0) && (NearX(j,fastWay.get(i).x) < 18) && (NearY(j,fastWay.get(i).y) < 32)) {
                    if ((specField[NearX(j, fastWay.get(i).x)][NearY(j, fastWay.get(i).y)] == currentNode - 1) && once) { // если значение пометки узла на 1 меньше, чем у предыдущего узла
                        currentNode = specField[NearX(j, fastWay.get(i).x)][NearY(j, fastWay.get(i).y)]; //узел становится текущим
                        fastWay.add(new position(NearX(j, fastWay.get(i).x), NearY(j, fastWay.get(i).y)));// заносится в массив
                        once = false;
                    }
                }
            }
        }
    }


    public int NearX(int iteration,int fromX){
        switch (iteration) {
            case 0: fromX--; break;
            case 1: fromX++; break;
            default: return fromX;
        }
        return fromX;
    }

    public int NearY(int iteration, int fromY){
        switch (iteration) {
            case 2: fromY++;break;
            case 3: fromY--;break;
            default: return fromY;
        }
        return fromY;
    }

    public int getBotSnakeSize() {
        return  botSnakeArr.size();
    }
    public ArrayList<position> getBotSnake() {
        return botSnakeArr;
    }
    public int getFastArrSize() {
        return  fastWay.size();
    }
    public ArrayList<position> getFastArr() {
        return fastWay;
    }
}
