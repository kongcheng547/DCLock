package com.example.dclock;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.RadioGroup;

import com.example.dclock.shouye_fragment.Clock_Main;
import com.example.dclock.shouye_fragment.DClock_Main;
import com.example.dclock.shouye_fragment.Data_Main;
import com.example.dclock.shouye_fragment.Mine_Main;

public class MainActivity extends AppCompatActivity {

    private RadioGroup mTabRadioGroup;
    private SparseArray<Fragment> mFragmentSparseArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRatioGroup();
    }

    private void initRatioGroup(){
        mTabRadioGroup = findViewById(R.id.rg);
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