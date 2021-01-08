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

    TextView textView;
    LockPhoneInfoDataBase lockPhoneInfoDataBase;
    LockPhoneInfoDao lockPhoneInfoDao;

    Data_Main(){ }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_data_main, container, false);
        textView = (TextView) rootView.findViewById(R.id.test_text);

        lockPhoneInfoDataBase = LockPhoneInfoDataBase.getLockPhoneInfoDataBase(getContext());
        lockPhoneInfoDao = lockPhoneInfoDataBase.lockPhoneInfoDao();

        LockPhoneInfo lockPhoneInfo = lockPhoneInfoDao.getLatestInfo();

        //textView.setText(lockPhoneInfoDao.getInfoNum()+"");
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
