package com.example.dclock.alarmclass;

public class Alarm {
    int alarmHour, alarmMinite;
    boolean alarmEnable;

    public Alarm(int alarmHour, int alarmMinite){
        this.alarmHour = alarmHour;
        this.alarmMinite = alarmMinite;
        alarmEnable = false;
    }

    public int getAlarmHour() {
        return alarmHour;
    }

    public void setAlarmHour(int alarmHour) {
        this.alarmHour = alarmHour;
    }

    public int getAlarmMinite() {
        return alarmMinite;
    }

    public void setAlarmMinite(int alarmMinite) {
        this.alarmMinite = alarmMinite;
    }

    public boolean isAlarmEnable() {
        return alarmEnable;
    }

    public void setAlarmEnable(boolean alarmEnable) {
        this.alarmEnable = alarmEnable;
    }

    public int getId(){
        return alarmHour*60+ alarmMinite;
    }
}
