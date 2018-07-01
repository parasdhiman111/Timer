package com.example.paras.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Boolean counterActive=false;
SeekBar seekBarTimer;
    TextView textViewTimer;
CountDownTimer countDownTimer;
    Button btnStart;


    public void resetTimer()
    {
        textViewTimer.setText("0:30");
        seekBarTimer.setProgress(30);
        countDownTimer.cancel();
        btnStart.setText("Start");
        seekBarTimer.setEnabled(true);
        counterActive=false;
    }



    public void updateTimer(int secondsLeft)
    {
        int minutes=(int)secondsLeft/60;
        int seconds=secondsLeft-minutes*60;


        String secondString=Integer.toString(seconds);

        if(secondString== "0")
        {
            secondString =  "00";
        }
        else if(seconds<=9)
        {
secondString="0"+secondString;
        }


        textViewTimer.setText(Integer.toString(minutes)+":"+secondString);
    }

public void counterUpdate(View view) {
    if (counterActive == false) {

        counterActive = true;
        seekBarTimer.setEnabled(false);
        btnStart.setText("Stop");


        countDownTimer=new CountDownTimer(seekBarTimer.getProgress() * 1000 + 100, 1000) {


            @Override
            public void onTick(long l) {
                updateTimer((int) l / 1000);

            }

            @Override
            public void onFinish() {
                resetTimer();
             //   Log.i("on finish", "timer done!");
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                mediaPlayer.start();
            }
        }.start();
    }
else
    {
      resetTimer();
    }
}



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       seekBarTimer=(SeekBar)findViewById(R.id.seekBarTimer);
        ImageView imageViewEgg=(ImageView)findViewById(R.id.imageViewEgg);
         textViewTimer=(TextView)findViewById(R.id.textViewTimer);
        btnStart=(Button)findViewById(R.id.btnStart);

        seekBarTimer.setMax(600);
        seekBarTimer.setProgress(30);





        seekBarTimer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
