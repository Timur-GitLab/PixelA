package com.example.cours_work;

import java.util.TimerTask;

import android.graphics.Canvas;
import android.graphics.Color;

public class GraphUpdater extends TimerTask {
    GameSurface surf;

    GraphUpdater(GameSurface surf){
        this.surf = surf;
    }

    @Override
    public void run() {
        Canvas canvas = surf.getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            surf.drawSnake(canvas);
            surf.getHolder().unlockCanvasAndPost(canvas);
        }
    }

    }
