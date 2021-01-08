package com.example.dclock.shouye_fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.dclock.LockPhone;
import com.example.dclock.R;

public class Mine_Main extends Fragment {
    public Button changeInfo,changeBackground,changeSaid,help_mine;
    public ImageButton headImg;
    public Context context;
    public TextView nicheng;
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
        nicheng=rootView.findViewById(R.id.nicheng);
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
                EditText nichengChoose;//这个view不能是全局的，应该是在用的时候再创建，否则会出现错误
                nichengChoose=new EditText(context);
                AlertDialog.Builder showDialog = new AlertDialog.Builder(context);
                showDialog.setTitle("输入昵称");
                //nichengChoose=(EditText)getLayoutInflater().inflate(nichengChoose,null);
                showDialog.setView(nichengChoose);
                showDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!nichengChoose.getText().toString().equals("")){
                            nicheng.setText(nichengChoose.getText().toString());
                        }
                        else
                            Toast.makeText(context,"请输入您的座右铭",Toast.LENGTH_LONG).show();
                    }
                });
                showDialog.setNegativeButton("取消", null);
                showDialog.show();
            }
        });
        changeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder showDialog=new AlertDialog.Builder(context);
                showDialog.setTitle("选择锁机背景图片");
                View view=LayoutInflater.from(context).inflate(R.layout.choose_lockbackground,null);
                showDialog.setView(view);
                ImageButton lockback1,lockback2,lockback3,lockback4,lockback5,lockback6;
                lockback1=view.findViewById(R.id.lockbackground1);
                lockback1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第1个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground1;
                    }
                });
                lockback2=view.findViewById(R.id.lockbackground2);
                lockback2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第2个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground2;
                    }
                });
                lockback3=view.findViewById(R.id.lockbackground3);
                lockback3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第3个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground3;
                    }
                });
                lockback4=view.findViewById(R.id.lockbackground4);
                lockback4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第4个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground4;
                    }
                });
                lockback5=view.findViewById(R.id.lockbackground5);
                lockback5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第5个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground5;
                    }
                });
                lockback6=view.findViewById(R.id.lockbackground6);
                lockback6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第6个背景",Toast.LENGTH_SHORT).show();
                        LockPhone.background=R.drawable.lockbackground6;
                    }
                });
                showDialog.setNegativeButton("完成",null);
                showDialog.show();
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
                //EditText nichengChoose;//这个view不能是全局的，应该是在用的时候再创建，否则会出现错误
                TextView textView=new TextView(context);

                AlertDialog.Builder showDialog = new AlertDialog.Builder(context);
                showDialog.setTitle("输入昵称");
                //nichengChoose=(EditText)getLayoutInflater().inflate(nichengChoose,null);
                textView.setText("这是帮助！");
                showDialog.setView(textView);
                showDialog.setNegativeButton("知道了", null);
                showDialog.show();
            }
        });
        headImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder showDialog=new AlertDialog.Builder(context);
                showDialog.setTitle("选择头像");
                View view=LayoutInflater.from(context).inflate(R.layout.choose_headimg,null);
                showDialog.setView(view);
                ImageButton headimg1,headimg2,headimg3,headimg4,headimg5,headimg6;
                headimg1=view.findViewById(R.id.headimg1);
                headimg1.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第1个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg1));
                    }
                });
                headimg2=view.findViewById(R.id.headimg2);
                headimg2.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第2个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg2));
                    }
                });
                headimg3=view.findViewById(R.id.headimg3);
                headimg3.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第3个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg3));
                    }
                });
                headimg4=view.findViewById(R.id.headimg4);
                headimg4.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第4个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg4));
                    }
                });
                headimg5=view.findViewById(R.id.headimg5);
                headimg5.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第5个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg5));
                    }
                });
                headimg6=view.findViewById(R.id.headimg6);
                headimg6.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("UseCompatLoadingForDrawables")
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context,"选择了第6个头像",Toast.LENGTH_SHORT).show();
                        headImg.setImageDrawable(getResources().getDrawable(R.drawable.headimg6));
                    }
                });
                showDialog.setNegativeButton("完成",null);
                showDialog.show();
            }
        });
    }
}
