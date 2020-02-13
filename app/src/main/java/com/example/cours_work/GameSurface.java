package com.example.cours_work;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

public class GameSurface extends SurfaceView {
    SnakeBot mBot;

    Bitmap mHead, mTill, mBody, mFruite, mborders ,mBothead, mWay;

    String yourScore = "123";
    String botScore = "123";
      int col;

    public GameSurface(Context context){
        super(context);
        mBot = new SnakeBot();
        mHead = BitmapFactory.decodeResource(context.getResources(),R.drawable.head);
        mBody = BitmapFactory.decodeResource(context.getResources(),R.drawable.body);
        mTill = BitmapFactory.decodeResource(context.getResources(),R.drawable.till);
        mFruite  = BitmapFactory.decodeResource(context.getResources(),R.drawable.fruite);
        mborders = BitmapFactory.decodeResource(context.getResources(),R.drawable.borders);
        mBothead = BitmapFactory.decodeResource(context.getResources(),R.drawable.bothead);
        //mWay = BitmapFactory.decodeResource(context.getResources(),R.drawable.waypicture);
    }

    public void setYourScore(String yourScore) {
        this.yourScore = yourScore;
    }
    public void setBotScore(String botScore) {
        this.botScore = botScore;
    }

    void drawSnake(Canvas c) {
        int width = c.getWidth();
        int height = c.getHeight();
        int mx = width / mBot.mFieldX;
        int my = height / mBot.mFieldY;

        Bitmap head = Bitmap.createScaledBitmap(mHead, mx, my, true);
        Bitmap body = Bitmap.createScaledBitmap(mBody, mx, my, true);
        Bitmap till = Bitmap.createScaledBitmap(mTill, mx, my, true);
        Bitmap fruite = Bitmap.createScaledBitmap(mFruite, mx, my, true);
        Bitmap borders = Bitmap.createScaledBitmap(mborders, mx, my, true);
        Bitmap botHead = Bitmap.createScaledBitmap(mBothead, mx, my, true);
        //Bitmap way = Bitmap.createScaledBitmap(mWay, mx, my, true);

        Paint paint = new Paint();

//        for (int i = 0; i < mBot.getFastArrSize() ; i++) {
//            c.drawBitmap(way,  mBot.getFastArr().get(i).x * mx,  mBot.getFastArr().get(i).y * my, new Paint());
//        }

        col = 0;
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(15);
        for(int i = 0; i < mBot.mFieldX; i++){
            for(int j = 0; j < mBot.mFieldY; j++){
                if(col != 64) {
                    if (mBot.getBordersX()[col] == i && mBot.getBordersY()[col] == j) {
                        c.drawBitmap(borders, mx * i, my * j, paint);
                        col++;
                    }
                }
                    if (mBot.getmField()[i][j] > 1) {
                        c.drawBitmap(fruite, mx * i, my * j, new Paint());
                    }
            }
        }


        for (int i = 0; i < mBot.getSnakeSize(); i++) {
            c.drawBitmap(body, mBot.getmSnake().get(i).x * mx, mBot.getmSnake().get(i).y * my, new Paint());
            if (i == 0) {
                c.drawBitmap(till, mBot.getmSnake().get(i).x * mx, mBot.getmSnake().get(i).y * my, new Paint());
            }
            if (i == mBot.getSnakeSize() - 1) {
                c.drawBitmap(head, mBot.getmSnake().get(i).x * mx, mBot.getmSnake().get(i).y * my, new Paint());
            }
        }


        for (int i = 0; i < mBot.getBotSnakeSize(); i++) {
            c.drawBitmap(body, mBot.getBotSnake().get(i).x * mx,  mBot.getBotSnake().get(i).y * my, new Paint());
            if (i == 0) {
                c.drawBitmap(till,  mBot.getBotSnake().get(i).x * mx,  mBot.getBotSnake().get(i).y * my, new Paint());
            }
            if (i == mBot.getBotSnakeSize() - 1) {
                c.drawBitmap(botHead,  mBot.getBotSnake().get(i).x * mx,  mBot.getBotSnake().get(i).y * my, new Paint());
            }
        }


        paint.setColor(Color.WHITE);
        paint.setAlpha(255);
        paint.setTextSize(50);
        c.drawText(yourScore, 75, 105,  paint);
        c.drawText(botScore, 690, 105,  paint);

    }
}
