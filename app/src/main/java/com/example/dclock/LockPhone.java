package com.example.dclock;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.dclock.shouye_fragment.forStartService;

import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

import static android.content.Context.WINDOW_SERVICE;

public class LockPhone extends Service {
    public static boolean isStarted = false;
    public View view;
    public int lockTime;

    public static String[] CN_CHARS = new String[] { "日", "一", "二", "三", "四",
            "五", "六"};
    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    private Button switchMusicButton;

    private Handler handler = new Handler();
    TextView time_now,time_year,window_text,this_time;

    private Runnable runnable = new Runnable()
    {

        public void run()
        {
            gettime();
            handler.postDelayed(this, 1000);
        }
    };
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("进入服务1", "进入服务1");

        isStarted = true;
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        layoutParams = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
        } else {
            layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        }
        layoutParams.format = PixelFormat.RGBA_8888;
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        DisplayMetrics dm = getResources().getDisplayMetrics();
        layoutParams.width = dm.widthPixels;
        layoutParams.height = dm.heightPixels;
        view = LayoutInflater.from(this).inflate(R.layout.floating_window,null);
        time_now=view.findViewById(R.id.floating_window_time);
        time_year=view.findViewById(R.id.floating_window_year);
        window_text=view.findViewById(R.id.floating_window_text);
        window_text.setText("你一定不会平凡");
        this_time=view.findViewById(R.id.floating_window_thistime);
        switchMusicButton=view.findViewById(R.id.floating_window_zaoButton);
        switchMusicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //进行噪声选取
            }
        });
        windowManager.addView(view,layoutParams);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.e("进入服务2", "进入服务2");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("进入服务3", "进入服务3");
        handler.post(runnable);
        lockTime=intent.getIntExtra("lockTime",1);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onDestroy() {
        windowManager.removeView(view);

        super.onDestroy();
    }

    public void gettime()
    {
        Calendar calendar = Calendar.getInstance();
        // 获取年,月，日；
        String year = calendar.get(Calendar.YEAR) + "";
        int month = calendar.get((Calendar.MONTH)) + 1;
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        int mWay = calendar.get(Calendar.DAY_OF_WEEK)-1;
        String yearString = year + "/" + (month) + "/" + day + "  星期"+CN_CHARS[mWay];
        time_year.setText(yearString);

        // 获取系统时间
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int miao = calendar.get(Calendar.SECOND);
        int nextMin,nextH,allTime;
        allTime=minute+lockTime;
        nextMin=allTime%60;
        nextH=hour+allTime/60;
        @SuppressLint("DefaultLocale") String timeString = String.format("%02d:%02d:%02d", hour, minute, miao);
        @SuppressLint("DefaultLocale") String nextTimeString = String.format("本次锁机时间\n%02d:%02d--%02d:%02d", hour, minute, nextH,nextMin);
        time_now.setText(timeString);
        this_time.setText(nextTimeString);
    }

}
