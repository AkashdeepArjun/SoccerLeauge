package com.akkayameva.soccerLeauge.model;


import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "TeamTable")
public class Team implements Parcelable
{
    @PrimaryKey(autoGenerate = false)
   @ColumnInfo(name = "id")
    @SerializedName("id")
    public Integer id;

    @ColumnInfo(name = "team_name")
    @SerializedName("team_name")
    public String team_name;


    public final static Creator<Team> CREATOR = new Creator<Team>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Team createFromParcel(android.os.Parcel in) {
            return new Team(in);
        }

        public Team[] newArray(int size) {
            return (new Team[size]);
        }

    }
            ;

    protected Team(android.os.Parcel in) {
        this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.team_name = ((String) in.readValue((String.class.getClassLoader())));
    }


    public Team() {
    }


    public Team(Integer id, String name) {
        super();
        this.id = id;
        this.team_name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return team_name;
    }

    public void setName(String name) {
        this.team_name = name;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(team_name);
    }

    public int describeContents() {
        return 0;
    }

}