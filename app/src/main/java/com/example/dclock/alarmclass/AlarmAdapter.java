package com.example.dclock.alarmclass;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.dclock.R;

import java.util.List;

public class AlarmAdapter extends ArrayAdapter<Alarm> {

    private int resourceId;
    public AlarmAdapter(Context context, int textViewResourceId, List<Alarm> objects){

        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Alarm alarm = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
        TextView alarmTime = (TextView) view.findViewById(R.id.alarm_time_text);
        alarmTime.setText(String.format("%02d:%02d",alarm.getAlarmHour(),alarm.getAlarmMinite()));

        return view;
    }


}
