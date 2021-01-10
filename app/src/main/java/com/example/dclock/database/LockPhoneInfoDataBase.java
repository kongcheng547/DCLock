package com.example.dclock.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LockPhoneInfo.class}, version = 1)
public abstract class LockPhoneInfoDataBase extends RoomDatabase {
    private static LockPhoneInfoDataBase lockPhoneInfoDataBase;
    public abstract LockPhoneInfoDao lockPhoneInfoDao();

    public static LockPhoneInfoDataBase getLockPhoneInfoDataBase(Context context){
        if (lockPhoneInfoDataBase == null){
            synchronized (LockPhoneInfoDataBase.class) {
                if (lockPhoneInfoDataBase == null) {
                    lockPhoneInfoDataBase = Room.databaseBuilder(context.getApplicationContext(), LockPhoneInfoDataBase.class, "lock_phone_info_db_test5").allowMainThreadQueries().build();
                }
            }
        }
        return lockPhoneInfoDataBase;
    }
}
