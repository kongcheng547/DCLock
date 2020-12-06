package com.example.dclock.shouye_fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dclock.R;

public class Mine_Main extends Fragment {
    Mine_Main(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mine_main, container, false);
        return rootView;
    }

    public static Mine_Main newInstance(String s1) {
        
        Bundle args = new Bundle();
        Mine_Main fragment = new Mine_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }
}
