package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mplayer;
    AudioManager audioManager;
    public void playaudio(View view){
        mplayer.start();
    }

    public void pauseaudio(View view){
        mplayer.pause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mplayer= MediaPlayer.create(this, R.raw.song);
        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxvolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curvolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        SeekBar volumecontrol= (SeekBar) findViewById(R.id.seekBar);
        volumecontrol.setMax(maxvolume);
        volumecontrol.setProgress(curvolume);
        volumecontrol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()

        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("SeekBar value",Integer.toString(progress));
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        SeekBar scrubber =(SeekBar) findViewById(R.id.scrubber);
        scrubber.setMax(mplayer.getDuration());

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrubber.setProgress(mplayer.getCurrentPosition());

            }
        },0,1000);
        scrubber.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.i("scrubber value",Integer.toString(progress));
                //mplayer.seekTo(progress);
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