package com.example.dclock;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

public class MyAlarmAudioService extends Service {
    public static MediaPlayer mediaplayer;
    public static int alarmMusicChoose = R.raw.asgore;

    public AudioManager audioManager;
    public Handler handler = new Handler();
    @Override
    public IBinder onBind(Intent argO) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        if (mediaplayer != null) {
            mediaplayer.release();
            mediaplayer = null;
        }
    }

    @SuppressLint("WrongConstant")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        Log.e("MyAlarmAudioService", "启动音乐服务");
        audioManager =(AudioManager) getSystemService(Context.AUDIO_SERVICE);
        mediaplayer = MediaPlayer.create(this,alarmMusicChoose);
        mediaplayer.start();
        mediaplayer.setLooping(true);
        handler.post(new Runnable() {
            @Override
            public void run() {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 15, 0);
                handler.postDelayed(this, 10);
            }
        });
        return 1;
    }


    public static void stopPlay(){
        mediaplayer.stop();
    }
}
