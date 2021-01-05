package com.example.dclock.shouye_fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dclock.LockPhone;
import com.example.dclock.R;

public class Mine_Main extends Fragment {
    public Button changeInfo,changeBackground,changeSaid,help_mine;
    public ImageButton headImg;
    public Context context;
    Mine_Main(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mine_main, container, false);
        changeInfo=rootView.findViewById(R.id.changeInfo);
        changeBackground=rootView.findViewById(R.id.changeBackground);
        changeSaid=rootView.findViewById(R.id.changeSaid);
        help_mine=rootView.findViewById(R.id.mine_help);
        headImg=rootView.findViewById(R.id.headImg);
        context=this.getContext();
        initBtns();
        return rootView;
    }

    public static Mine_Main newInstance(String s1) {
        Bundle args = new Bundle();
        Mine_Main fragment = new Mine_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }
    public void initBtns(){
        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        changeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        changeSaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText saidChoose;//这个view不能是全局的，应该是在用的时候再创建，否则会出现错误
                saidChoose=new EditText(context);
                AlertDialog.Builder showDialog = new AlertDialog.Builder(context);
                showDialog.setTitle("输入您的座右铭");
                //saidChoose=(EditText)getLayoutInflater().inflate(saidChoose,null);
                showDialog.setView(saidChoose);
                showDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(saidChoose.getText().toString()!=null){
                            LockPhone.saidText=saidChoose.getText().toString();
                        }
                        else
                            Toast.makeText(context,"请输入您的座右铭",Toast.LENGTH_LONG).show();
                    }
                });
                showDialog.setNegativeButton("取消", null);
                showDialog.show();
            }
        });
        help_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
