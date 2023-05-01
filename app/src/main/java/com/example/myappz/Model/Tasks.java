package com.example.myappz.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "_tasks",foreignKeys = {
        @ForeignKey(entity = Days.class,
                    childColumns = "dayID",
                    parentColumns = "dayID",
                    onUpdate = ForeignKey.CASCADE,
                    onDelete = ForeignKey.CASCADE)
}

)
public class Tasks implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int taskID;
    @ColumnInfo(name = "dayID",index = true)
    private int dayID;
    private String title;
    private String isDone;


    public int getTaskID() {
        return taskID;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsDone() {
        return isDone;
    }

    public void setIsDone(String isDone) {
        this.isDone = isDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.taskID);
        dest.writeInt(this.dayID);
        dest.writeString(this.title);
        dest.writeString(this.isDone);
    }

    public void readFromParcel(Parcel source) {
        this.taskID = source.readInt();
        this.dayID = source.readInt();
        this.title = source.readString();
        this.isDone = source.readString();
    }

    public Tasks() {
    }

    protected Tasks(Parcel in) {
        this.taskID = in.readInt();
        this.dayID = in.readInt();
        this.title = in.readString();
        this.isDone = in.readString();
    }

    public static final Parcelable.Creator<Tasks> CREATOR = new Parcelable.Creator<Tasks>() {
        @Override
        public Tasks createFromParcel(Parcel source) {
            return new Tasks(source);
        }

        @Override
        public Tasks[] newArray(int size) {
            return new Tasks[size];
        }
    };
}
