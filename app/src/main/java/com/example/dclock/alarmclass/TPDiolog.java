package com.example.dclock.alarmclass;

import android.app.TimePickerDialog;
import android.content.Context;

public class TPDiolog extends TimePickerDialog {

    public TPDiolog(Context context, OnTimeSetListener callBack, int hourOfDay,
                    int minute, boolean is24HourView) {
        super(context, callBack, hourOfDay, minute, is24HourView);
    }


    @Override
    protected void onStop() {
        //super.onStop();
    }
}
