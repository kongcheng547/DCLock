package com.example.dclock.shouye_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.dclock.R;
import com.example.dclock.database.LockPhoneInfo;
import com.example.dclock.database.LockPhoneInfoDao;
import com.example.dclock.database.LockPhoneInfoDataBase;

public class Data_Main extends Fragment {

    TextView numberTextView, timeTextView, latestDayTextView, latestTimeTextView;
    LockPhoneInfoDataBase lockPhoneInfoDataBase;
    LockPhoneInfoDao lockPhoneInfoDao;

    Data_Main(){ }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_data_main, container, false);

        lockPhoneInfoDataBase = LockPhoneInfoDataBase.getLockPhoneInfoDataBase(getContext());
        lockPhoneInfoDao = lockPhoneInfoDataBase.lockPhoneInfoDao();

        LockPhoneInfo lockPhoneInfo = lockPhoneInfoDao.getLatestInfo();

        numberTextView = (TextView) rootView.findViewById(R.id.lock_phone_cishu);
        timeTextView = (TextView) rootView.findViewById(R.id.lock_phone_time);

        latestDayTextView = (TextView) rootView.findViewById(R.id.lock_phone_latest_begin);
        latestTimeTextView = (TextView) rootView.findViewById(R.id.lock_phone_latest_last);

        numberTextView.setText(lockPhoneInfoDao.getInfoNum()+"次");
        timeTextView.setText(lockPhoneInfoDao.getTotalTime()+"分钟");
        LockPhoneInfo latestLockPhoneInfo = lockPhoneInfoDao.getLatestInfo();

        if (latestLockPhoneInfo != null){
            latestDayTextView.setText(latestLockPhoneInfo.getBeginMonth()+"月"+latestLockPhoneInfo.getBeginDay()+"日 "+latestLockPhoneInfo.getBeginHour()+":"+latestLockPhoneInfo.getBeginMinite());
            latestTimeTextView.setText(latestLockPhoneInfo.getLastingTime()+"分钟");
        }

        return rootView;
    }

    public static Data_Main newInstance(String s1) {

        Bundle args = new Bundle();
        Data_Main fragment = new Data_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }
}
