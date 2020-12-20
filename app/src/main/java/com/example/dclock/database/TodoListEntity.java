package com.example.dclock.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "todo")
public class TodoListEntity {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private Long mId;

    @ColumnInfo(name = "content")
    private String mContent;

    @ColumnInfo(name = "time")
    private Date mTime;

    @ColumnInfo(name="isFinished")
    private  boolean isFinished;

    public TodoListEntity(String mContent, Date mTime) {
        this.mContent = mContent;
        this.mTime = mTime;
        this.isFinished=false;
    }

    public String getContent() {
        return mContent;
    }

    public void setContent(String mContent) {
        this.mContent = mContent;
    }

    public Date getTime() {
        return mTime;
    }

    public void setTime(Date mTime) {
        this.mTime = mTime;
    }

    public Long getId() {
        return mId;
    }

    public void setId(Long mId) {
        this.mId = mId;
    }
    public boolean getIsFinished(){
        return this.isFinished;
    }
    public void setIsFinished(boolean isFinished)
    {
        this.isFinished=isFinished;
    }

}
