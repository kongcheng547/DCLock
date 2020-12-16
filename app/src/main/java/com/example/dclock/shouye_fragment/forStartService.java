package com.example.dclock.shouye_fragment;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.dclock.LockPhone;

public class forStartService extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startFloatingButtonService();
    }

    public void startFloatingButtonService() {
        Log.e("测试流程", "测试流程");
        if (LockPhone.isStarted) {
            Log.e("测试流程2", "测试流程2");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {//判断系统版本
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
                Log.e("测试流程3", "测试流程3");
                startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
            } else {
                Log.e("测试流程4", "测试流程4");
                this.startService(new Intent(forStartService.this, LockPhone.class));
                Log.e("启动失败","错误");
            }
        } else {
            startService(new Intent(forStartService.this, LockPhone.class));
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
}
