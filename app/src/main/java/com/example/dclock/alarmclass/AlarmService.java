package com.example.dclock.alarmclass;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dclock.MyAlarmAudioService;
import com.example.dclock.MyAudioService;
import com.example.dclock.R;
import com.example.dclock.shouye_fragment.ForStartAlarmService;
import com.example.dclock.shouye_fragment.forStartService;

import java.util.Calendar;

public class AlarmService extends Service {

    public static boolean isStarted = false;
    public View view;
    public Context context=this;

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    public static int myClickTime=10;

    private Intent playIntent;

    TextView dayText, timeText, needStringText, inputStringText;
    Button subscribeButton;

    public static String[] CN_CHARS = new String[] { "日", "一", "二", "三", "四",
            "五", "六"};

    int clickTime = 0;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable()
    {

        public void run()
        {
            gettime();
            handler.postDelayed(this, 1000);
        }
    };

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("AlarmService", "Begin Service");
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
        view = LayoutInflater.from(this).inflate(R.layout.alarm_floating_window,null);

        dayText = view.findViewById(R.id.date_text_in_alarm_floating);
        timeText = view.findViewById(R.id.time_text_in_alarm_floating);
        TextView alarm_click_time=view.findViewById(R.id.alarm_click_time);
        alarm_click_time.setText("您设定的时间到了\n如想关闭闹钟\n请单击按钮"+myClickTime+"次");
        //needStringText = view.findViewById(R.id.need_string_text);
        //inputStringText = view.findViewById(R.id.input_string_text);
        subscribeButton = view.findViewById(R.id.subscribe_button);

        windowManager.addView(view,layoutParams);

        Log.e("Alarm Service", "开始播放音乐");
        playIntent=new Intent(context, MyAlarmAudioService.class);

        startService(playIntent);
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
        subscribeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double x=Math.random()*layoutParams.width*0.8,y=Math.random()*layoutParams.height*0.8;
                clickTime++;
                subscribeButton.setX((float)x);
                subscribeButton.setY((float)y);
                if (clickTime == myClickTime){
                    ForStartAlarmService forStartAlarmService = new ForStartAlarmService();
                    stopSelf();
                    forStartAlarmService.finish();
                    Toast.makeText(context,"闹钟已关闭，请按返回键返回",Toast.LENGTH_LONG).show();
                }
                else{
//                    Toast.makeText(context,"请继续点击",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        windowManager.removeView(view);
//        MyAudioService.mediaplayer.stop();
        stopService(playIntent);
        super.onDestroy();
    }

    public void gettime(){
        Calendar calendar = Calendar.getInstance();
        // 获取年,月，日；
        String year = calendar.get(Calendar.YEAR) + "";
        int month = calendar.get((Calendar.MONTH)) + 1;
        String day = calendar.get(Calendar.DAY_OF_MONTH) + "";
        int mWay = calendar.get(Calendar.DAY_OF_WEEK)-1;
        String yearString = year + "/" + (month) + "/" + day + "  星期"+CN_CHARS[mWay];

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        dayText.setText(yearString);
        timeText.setText(String.format("%02d:%02d", hour, minute));
    }
}
