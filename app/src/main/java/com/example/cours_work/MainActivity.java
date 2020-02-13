package com.example.cours_work;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int BEST_SCORE = 0;
    final String TEXT = "bestscore";
    public static String OUT = "";
    SharedPreferences sp;
    public static boolean flag = false;
    private  String bestcs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }

    @Override
    public void onStart() {
        super.onStart();

        TextView bestscore = (TextView)findViewById(R.id.best_score_textView);
        Button btn_play = (Button) findViewById(R.id.button_play);
        TextView out = (TextView)findViewById(R.id.outtext);

        if(flag) {
            bestscore.setText("Best score: " + BEST_SCORE);
            out.setText(OUT);
        } else {
                sp = getPreferences(MODE_PRIVATE);
                bestcs = sp.getString(TEXT, "0");
                bestscore.setText("Best score: " + bestcs);
                BEST_SCORE = Integer.valueOf(bestcs);
                flag = true;


        }
        btn_play.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
            Intent intent = new Intent(this,GameActivity.class);
            startActivity(intent);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        sp = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(TEXT, String.valueOf(BEST_SCORE));
        ed.commit();
    }
}
