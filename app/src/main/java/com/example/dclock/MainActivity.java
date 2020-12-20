package com.example.dclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.StatusBarManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.SparseArray;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.example.dclock.shouye_fragment.Clock_Main;
import com.example.dclock.shouye_fragment.DClock_Main;
import com.example.dclock.shouye_fragment.Data_Main;
import com.example.dclock.shouye_fragment.Mine_Main;

public class MainActivity extends AppCompatActivity {

    private SparseArray<Fragment> mFragmentSparseArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            //| View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.GRAY);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_main);
        initRatioGroup();
    }
    private void initRatioGroup(){
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        RadioGroup mTabRadioGroup = findViewById(R.id.rg);
        mFragmentSparseArray = new SparseArray<>();
        mFragmentSparseArray.append(R.id.suoji_tab, DClock_Main.newInstance("锁机"));
        mFragmentSparseArray.append(R.id.naozhong_tab, Clock_Main.newInstance("搜索"));
        mFragmentSparseArray.append(R.id.shuju_tab, Data_Main.newInstance("消息"));
        mFragmentSparseArray.append(R.id.wode_tab, Mine_Main.newInstance("我的"));
        mTabRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,mFragmentSparseArray.get(checkedId)).commit();
            }
        });
        //默认显示第一个
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, mFragmentSparseArray.get(R.id.suoji_tab)).commit();
    }
}