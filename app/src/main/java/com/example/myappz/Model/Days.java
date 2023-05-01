package com.example.myappz.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "_days")
public class Days implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "dayID")
    private int dayID;
    private String dayName;
    private String date;

    public int getDayID() {
        return dayID;
    }

    public void setDayID(int dayID) {
        this.dayID = dayID;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.dayID);
        dest.writeString(this.dayName);
        dest.writeString(this.date);
    }

    public void readFromParcel(Parcel source) {
        this.dayID = source.readInt();
        this.dayName = source.readString();
        this.date = source.readString();
    }

    public Days() {
    }

    protected Days(Parcel in) {
        this.dayID = in.readInt();
        this.dayName = in.readString();
        this.date = in.readString();
    }

    public static final Parcelable.Creator<Days> CREATOR = new Parcelable.Creator<Days>() {
        @Override
        public Days createFromParcel(Parcel source) {
            return new Days(source);
        }

        @Override
        public Days[] newArray(int size) {
            return new Days[size];
        }
    };
}
