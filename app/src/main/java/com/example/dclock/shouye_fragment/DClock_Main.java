package com.example.dclock.shouye_fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dclock.MainActivity;
import com.example.dclock.R;

public class DClock_Main extends Fragment {

    public Button lock1min,lock10min,lock30min,lock1h,lockchoose,lockontime;
    public ImageButton helplock;
    public Context context;
    //public EditText numberChoose;
    public int lockTime=1;
    DClock_Main(){

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        context=this.getContext();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dclock_main, container, false);
        lock1min=rootView.findViewById(R.id.lock1min);
        lock10min=rootView.findViewById(R.id.lock10min);
        lock30min=rootView.findViewById(R.id.lock30min);
        lock1h=rootView.findViewById(R.id.lock1h);
        lockchoose=rootView.findViewById(R.id.lockfor);
        lockontime=rootView.findViewById(R.id.lockontime);
        helplock=rootView.findViewById(R.id.helplcok);

        initBtns();
        return rootView;
    }

    public static DClock_Main newInstance(String s1) {

        Bundle args = new Bundle();
        DClock_Main fragment = new DClock_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }
    public void initBtns(){
        lock1min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forStartService.openActivity(getActivity(),1);
            }
        });
        lock10min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forStartService.openActivity(getActivity(),10);
            }
        });
        lock30min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forStartService.openActivity(getActivity(),30);
            }
        });
        lock1h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forStartService.openActivity(getActivity(),60);
            }
        });
        lockchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText numberChoose;//这个view不能是全局的，应该是在用的时候再创建，否则会出现错误
                numberChoose=new EditText(context);
                numberChoose.setInputType(InputType.TYPE_CLASS_NUMBER);
                AlertDialog.Builder showDialog = new AlertDialog.Builder(context);
                showDialog.setTitle("输入锁机时长(分钟)");
                //numberChoose=(EditText)getLayoutInflater().inflate(numberChoose,null);
                showDialog.setView(numberChoose);
                showDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        lockTime = Integer.parseInt(numberChoose.getText().toString());
                        forStartService.openActivity(getActivity(), lockTime);
                    }
                });
                showDialog.setNegativeButton("取消", null);
                showDialog.show();

            }
        });
        lockontime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forStartService.openActivity(getActivity(),1);
            }
        });
        helplock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("帮助")
                        .setMessage("这是帮助")
                        .setPositiveButton("知道了",null)
                        .show();
                //forStartService.openActivity(getActivity(),1);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

    }

}
