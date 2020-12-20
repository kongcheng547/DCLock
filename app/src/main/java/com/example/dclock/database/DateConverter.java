package com.example.dclock.database;

import androidx.room.TypeConverter;

import java.util.Date;


public class DateConverter {
    @TypeConverter
    public Date fromTimeStamp(long ts) {
        return new Date(ts);
    }

    @TypeConverter
    public long toTimeStamp(Date date) {
        return date.getTime();
    }
}
