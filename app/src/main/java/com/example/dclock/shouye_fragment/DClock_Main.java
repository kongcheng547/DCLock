package com.example.dclock.shouye_fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.dclock.MainActivity;
import com.example.dclock.MyAudioService;
import com.example.dclock.R;

import java.util.Calendar;

public class DClock_Main extends Fragment {

    public Button lock1min,lock10min,lock30min,lock1h,lockchoose,lockontime,chooseMusic;
    public ImageButton helplock;
    public Context context;
    //public EditText numberChoose;
    public int lockTime=1;
    int startTimeHour=-1,startTimeMinute=-1;
    int hour,minute;
    String musicChoose;
    private Handler handler = new Handler();

    private Runnable runnable = new Runnable()
    {
        public void run()
        {
            int flag=gettime();
            if(flag==0)
                handler.postDelayed(this, 1000);
            else
            {
                startTimeHour=-1;startTimeMinute=-1;
                handler.removeCallbacks(runnable);
            }
        }
    };
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
        chooseMusic=rootView.findViewById(R.id.chooseMusic);
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
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                TimePicker timePicker=new TimePicker(context);
                EditText timeChoose=new EditText(context);
                AlertDialog.Builder showDialog=new AlertDialog.Builder(context);
                LinearLayout layout=new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.addView(timePicker);
                layout.addView(timeChoose);
                showDialog.setView(layout);
                showDialog.setTitle("输入定时锁机时间及时长(分钟)");
//                showDialog.setView(timePicker);
                timePicker.setDescendantFocusability(TimePicker.FOCUS_BLOCK_DESCENDANTS);  //设置点击事件不弹键盘

                timePicker.setIs24HourView(true);   //设置时间显示为24小时
                timePicker.setHour(8);  //设置当前小时
                timePicker.setMinute(59); //设置当前分（0-59）
                //timePicker.set
                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {  //获取当前选择的时间
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        startTimeHour=hourOfDay;
                        startTimeMinute=minute;
                    }
                });
//                showDialog.setView(timeChoose);
                timeChoose.setInputType(InputType.TYPE_CLASS_NUMBER);
                timeChoose.setHint("请输入锁机时长：");
                showDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!timeChoose.getText().toString().equals(""))
                        {
                            lockTime = Integer.parseInt(timeChoose.getText().toString());
                            Toast.makeText(context,"将会在"+startTimeHour+":"+startTimeMinute+"时锁机",Toast.LENGTH_LONG).show();
                            //forStartService.openActivity(getActivity(), lockTime);
                        }
                        else
                        {
                            lockTime=-1;
                            Toast.makeText(context,"请输入锁机时长！",Toast.LENGTH_LONG).show();
                        }
                    }
                });
                showDialog.setNegativeButton("取消", null);
                showDialog.show();
                handler.post(runnable);
                //forStartService.openActivity(getActivity(),lockTime);
            }
        });
        chooseMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String[] items = {"火焰", "下雨", "大雨", "大风"};
                //String musicChoose;
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
                alertBuilder.setTitle("选择锁机白噪声");
                alertBuilder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(context, items[i], Toast.LENGTH_SHORT).show();
                        switch (i)
                        {
                            case 0:MyAudioService.musicChoose=R.raw.fire;break;
                            case 1:MyAudioService.musicChoose=R.raw.rain;break;
                            case 2:MyAudioService.musicChoose=R.raw.strongrain;break;
                            case 3:MyAudioService.musicChoose=R.raw.wind;break;
                        }
                    }
                });
                alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //alertDialog2.dismiss();

                    }
                });
                alertBuilder.setNegativeButton("取消", null);
                AlertDialog alertDialog2 = alertBuilder.create();
                alertDialog2.show();
            }
        });
        helplock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("帮助")
                        .setMessage("点击锁机就会锁定手机哦，请注意使用！")
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
    public int gettime()
    {
        Calendar calendar = Calendar.getInstance();

        // 获取系统时间
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        if(hour==startTimeHour&&minute==startTimeMinute){
            if(lockTime!=-1)
                forStartService.openActivity(getActivity(),lockTime);
            return 1;
        }
        return 0;
    }

}
