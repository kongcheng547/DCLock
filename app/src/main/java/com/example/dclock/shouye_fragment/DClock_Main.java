package com.example.dclock.shouye_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dclock.R;

public class DClock_Main extends Fragment {
    DClock_Main(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dclock_main, container, false);
        return rootView;
    }

    public static DClock_Main newInstance(String s1) {

        Bundle args = new Bundle();
        DClock_Main fragment = new DClock_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }
}
