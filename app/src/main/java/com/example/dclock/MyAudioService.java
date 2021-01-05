package com.example.dclock;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

public class MyAudioService extends Service {
    public static MediaPlayer mediaplayer;
    public static int musicChoose=R.raw.fire;

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
        // TODO Auto-generated method stub
        super.onStartCommand(intent, flags, startId);
        //String path = "sdcard/music/white.mp3";
        mediaplayer = MediaPlayer.create(this,musicChoose);
        //try {
            //mediaplayer.prepare();
            mediaplayer.start();
            mediaplayer.setLooping(true);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        return 1;
    }
    public static void stopPlay(){
        mediaplayer.stop();
    }
}

