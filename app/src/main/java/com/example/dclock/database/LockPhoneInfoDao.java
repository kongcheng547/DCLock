package com.example.dclock.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface LockPhoneInfoDao {
    @Insert
    public void insertInfo(LockPhoneInfo lockPhoneInfo);

    @Update
    public void updateInfo(LockPhoneInfo ... lockPhoneInfos);

    @Delete
    public void deleteInfo(LockPhoneInfo ... lockPhoneInfos);

    @Query("SELECT * FROM lock_phone_info")
    public LockPhoneInfo[] getAllInfo();

    @Query("SELECT count(*) FROM lock_phone_info")
    public int getInfoNum();

    @Query("SELECT sum(lasting_time) FROM lock_phone_info")
    public int getTotalTime();

    @Query("SELECT * FROM lock_phone_info WHERE id LIKE (SELECT max(id) FROM lock_phone_info)")
    public LockPhoneInfo getLatestInfo();

}
