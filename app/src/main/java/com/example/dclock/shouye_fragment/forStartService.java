package com.example.dclock.shouye_fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dclock.LockPhone;

import static java.lang.Thread.sleep;

public class forStartService extends Activity {
    public int lockTime;
    public Intent intentlock;
    public static boolean isLock=false;
    Handler handler=new Handler();
    Runnable runnable=new Runnable(){
        @SuppressLint("ShowToast")
        @Override
        public void run() {
            // TODO Auto-generated method stub
            // 要做的事情
            //intentlock.setAction("ITOP.MOBILE.SIMPLE.SERVICE.SENSORSERVICE");
            stopService(intentlock);
            handler.postDelayed(this, 2000);
            handler.removeCallbacks(runnable);
            finish();
            Toast.makeText(forStartService.this,"已结束"+lockTime+"分钟的锁机",Toast.LENGTH_LONG).show();
        }
    };
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        lockTime=bundle.getInt("lockTime");
        Log.e("锁机时间",lockTime+"分钟");
        intentlock=new Intent(forStartService.this,LockPhone.class);
        intentlock.putExtra("lockTime",lockTime);
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
                handler.postDelayed(runnable,lockTime*60*1000);
            } else {
                Log.e("测试流程4", "测试流程4");
                startService(intentlock);
                handler.postDelayed(runnable,lockTime*60*1000);
                Log.e("启动失败","错误");
            }
        } else {
            startService(intentlock);
            handler.postDelayed(runnable,lockTime*60*1000);
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Log.e("测试流程5", "测试流程5");
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Log.e("测试流程6", "测试流程6");
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(forStartService.this, LockPhone.class));
                Log.e("启动失败","m");
            }
        }
    }
    public static void openActivity(Context context,int time){
        Intent intent = new Intent(context, forStartService.class);
        intent.putExtra("lockTime", time);
        context.startActivity(intent);
    }
    public void stopLock(){
        stopService(intentlock);
        finish();
        Toast.makeText(this,"提前结束锁机",Toast.LENGTH_LONG);
    }
}
