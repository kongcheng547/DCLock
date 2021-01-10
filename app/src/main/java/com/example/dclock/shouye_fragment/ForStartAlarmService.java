package com.example.dclock.shouye_fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.dclock.alarmclass.AlarmService;

public class ForStartAlarmService extends Activity {

    Intent alarmServiceIntent;

    public static void openActivity(Context context){
        context.startActivity(new Intent(context, ForStartAlarmService.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("ForStartAlarmService", "闹钟启动");
        Toast.makeText(this, "闹钟启动", Toast.LENGTH_LONG);
        alarmServiceIntent = new Intent(this, AlarmService.class);
        startFloatingButtonService();
    }

    public void startFloatingButtonService() {
        Log.e("测试流程", "测试流程");
//        if (LockPhone.isStarted) {
//            Log.e("测试流程2", "测试流程2");
//            return;
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断系统版本
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
                Log.e("测试流程3", "测试流程3");
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
            } else {
                Log.e("测试流程4", "测试流程4");
                startService(alarmServiceIntent);
                Log.e("启动失败","错误");
            }
        } else {
            startService(alarmServiceIntent);
        }

    }
}

