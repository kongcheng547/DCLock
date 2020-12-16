package com.example.dclock;

import android.app.Service;
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

import java.util.Calendar;

import static android.content.Context.WINDOW_SERVICE;

public class LockPhone extends Service {
    public static boolean isStarted = false;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;

    private Button button;

    private Handler handler = new Handler();
    TextView tv_day,tv_time;

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
        View view = LayoutInflater.from(this).inflate(R.layout.floating_window,null);
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
        //handler.post(runnable);
        //showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showFloatingWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断系统版本
            if (Settings.canDrawOverlays(this)) {
                button = new Button(getApplicationContext());
                button.setText("关闭");
                button.setBackgroundColor(Color.WHITE);
                handler.post(runnable);
                windowManager.addView(button, layoutParams);
                windowManager.addView(tv_day,layoutParams);
                windowManager.addView(tv_time,layoutParams);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        stopSelf();
                    }
                });
            }
        } else {
            button = new Button(getApplicationContext());
            button.setText("关闭");
            button.setBackgroundColor(Color.WHITE);
            windowManager.addView(button, layoutParams);
            windowManager.addView(tv_day,layoutParams);
            windowManager.addView(tv_time,layoutParams);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void gettime()
    {
        Calendar calendar = Calendar.getInstance();
        // 获取年,月，日；
        String year = calendar.get(Calendar.YEAR) + "";
        int month = calendar.get((Calendar.MONTH)) + 1;
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        String yearString = year + "/" + (month) + "/" + day;
        tv_day.setText(yearString);
        // 获取系统时间
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        String minute = calendar.get(Calendar.MINUTE) + "";
        int miao = calendar.get(Calendar.SECOND);
        String timeString = hour + ":" + minute + ":" + miao;
        tv_time.setText(timeString);

    }
}
