package com.example.dclock.shouye_fragment;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dclock.R;
import com.example.dclock.alarmclass.Alarm;
import com.example.dclock.alarmclass.AlarmAdapter;
import com.example.dclock.alarmclass.AlarmReceiver;
import com.example.dclock.alarmclass.TPDiolog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Clock_Main extends Fragment {

    private List<Alarm> alarmList = new ArrayList<>();
    private AlarmManager alarmManager;
    private AlarmAdapter alarmAdapter;
    private ListView listView;
//    {
//        alarmList.add(new Alarm(10, 25));
//        alarmList.add(new Alarm(18, 23));
//    }

    Clock_Main(){

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_clock_main, container, false);
        alarmAdapter = new AlarmAdapter(getActivity(), R.layout.list_alarm_item, alarmList);

        listView = (ListView) rootView.findViewById(R.id.alarm_list_view);
        Button button = (Button) rootView.findViewById(R.id.add_alarm_button);
        listView.setAdapter(alarmAdapter);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
                                           final int position, long arg3) {

                new AlertDialog.Builder(getContext())
                        .setTitle("删除闹钟")
                        .setItems(new CharSequence[] { "确认", "取消" },
                                new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog,
                                                        int which) {
                                        switch (which) {
                                            case 0:
                                                deleteAlarm(position);
                                                break;

                                            default:
                                                break;
                                        }
                                    }
                                }).setNegativeButton("退出", null).show();
                return true;
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAlarm();
            }
        });
        return rootView;
    }

    private void deleteAlarm(int position){
        Alarm alarm = alarmAdapter.getItem(position);
        alarmAdapter.remove(alarm);
        listView.setAdapter(alarmAdapter);
    }

    private void addAlarm() {

        Calendar c = Calendar.getInstance();
        new TPDiolog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                Alarm alarm = new Alarm(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
                alarmList.add(alarm);
                alarmAdapter = new AlarmAdapter(getActivity(), R.layout.list_alarm_item, alarmList);
                listView.setAdapter(alarmAdapter);
//                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
//                        calendar.getTimeInMillis(), 5*60*1000, PendingIntent.getBroadcast(
//                                getContext(), alarm.getId(),
//                        new Intent(getContext(), AlarmReceiver.class), 0));
            }
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true).show();
    }

    public static Clock_Main newInstance(String s1) {

        Bundle args = new Bundle();
        Clock_Main fragment = new Clock_Main();
        args.putString("Text", s1);
        fragment.setArguments(args);
        return fragment;
    }

}
