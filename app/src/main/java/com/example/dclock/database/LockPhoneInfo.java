package com.example.dclock.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.dclock.LockPhone;

import java.util.Calendar;

@Entity(tableName = "lock_phone_info")
public class LockPhoneInfo {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "begin_month")
    public int beginMonth;

    @ColumnInfo(name = "begin_day")
    public int beginDay;

    @ColumnInfo(name = "begin_hour")
    public int beginHour;

    @ColumnInfo(name = "begin_minute")
    public int beginMinite;

    @ColumnInfo(name = "lasting_time")
    public int lastingTime;

    public LockPhoneInfo(int beginMonth, int beginDay, int beginHour, int beginMinite, int lastingTime){
        this.beginMonth = beginMonth;
        this.beginDay = beginDay;
        this.beginHour = beginHour;
        this.beginMinite = beginMinite;
        this.lastingTime = lastingTime;
    }

    @Ignore
    public LockPhoneInfo(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBeginDay() {
        return beginDay;
    }

    public void setBeginDay(int beginDay) {
        this.beginDay = beginDay;
    }

    public int getBeginHour() {
        return beginHour;
    }

    public void setBeginHour(int beginHour) {
        this.beginHour = beginHour;
    }

    public int getBeginMinite() {
        return beginMinite;
    }

    public void setBeginMinite(int beginMinite) {
        this.beginMinite = beginMinite;
    }

    public int getLastingTime() {
        return lastingTime;
    }

    public void setLastingTime(int lastingTime) {
        this.lastingTime = lastingTime;
    }

    public int getBeginMonth() {
        return beginMonth;
    }

    public void setBeginMonth(int beginMonth) {
        this.beginMonth = beginMonth;
    }
}
