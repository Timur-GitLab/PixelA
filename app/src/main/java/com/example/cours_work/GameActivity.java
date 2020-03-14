package com.example.cours_work;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;
import java.util.Timer;

public class GameActivity extends AppCompatActivity implements SensorEventListener {

    GameSurface surf;
    Timer t;
    //MediaPlayer mPlayer;

    private SensorManager mSensorManager;
    private Sensor mAccelerometerSensor;

    float SSX = 0, SSY = 0;
    float SX = 0, SY = 0;
    float dirX, dirY;
    boolean firstTime,winner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // mPlayer = MediaPlayer.create(this, R.raw.hotlinemiamicrystals);
       // mPlayer.start();

        surf = new GameSurface(this);
        setContentView(surf);

        t = new Timer();
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometerSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    }

    @Override
    public void onStart() {
        super.onStart();
        t.scheduleAtFixedRate(new StepUpdater(this), 0, 400);
        t.scheduleAtFixedRate(new GraphUpdater(surf), 0, 200);
        mSensorManager.registerListener(this, mAccelerometerSensor, SensorManager.SENSOR_DELAY_GAME);
        firstTime = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        //mPlayer.pause();
        mSensorManager.unregisterListener(this);
        t.cancel();
    }

    @Override
    protected void onResume() {
        super.onResume();
       //mPlayer.start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private int getExDirection(float x, float y) {
        if (Math.abs(x) > Math.abs(y)) {
            if (x > 0) {
                return SnakeGame.LEFT;
            } else {
                return SnakeGame.RIGHT;
            }
        } else {
            if (y > 0) {
                return SnakeGame.DOWN;
            } else {
                return SnakeGame.UP;
            }
        }
    }

    public void onSensorChanged(SensorEvent event) {

        Sensor mSensor = event.sensor;
        surf.setYourScore("your score: " + surf.mBot.Score);
        surf.setBotScore("bot score: " + surf.mBot.botScore);

        if (mSensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            SX = event.values[0];
            SY = event.values[1];

            if (!firstTime) {
                dirX = SX - SSX;
                dirY = SY - SSY;
                surf.mBot.setDirection(getExDirection(dirX, dirY));
            } else {
                firstTime = false;
                SSX = SX;
                SSY = SY;
            }
    }
    }

    public void Step() {
        winner = !surf.mBot.Step();
        if (winner || surf.mBot.Score ==  200 ||!surf.mBot.Alive() || surf.mBot.botScore == 200) {
            if(winner || surf.mBot.botScore == 200){
                MainActivity.OUT = "You lose! :)";
            }else{
                MainActivity.OUT = "You win! how? 0_0";
            }
            if (surf.mBot.Score > MainActivity.BEST_SCORE) {
                MainActivity.BEST_SCORE = surf.mBot.Score;
            }

            mSensorManager.unregisterListener(this);
            t.cancel();
           //mPlayer.stop();
            this.finish();
        }
    }
}
