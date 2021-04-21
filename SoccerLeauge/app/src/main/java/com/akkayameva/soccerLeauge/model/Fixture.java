package com.akkayameva.soccerLeauge.model;


        import android.os.Parcelable;
        import androidx.room.ColumnInfo;
        import androidx.room.Entity;
        import androidx.room.PrimaryKey;

@Entity(tableName = "FixtureTable")
public class Fixture implements Parcelable
{

    @ColumnInfo(name="count")
    private Integer count;

    @ColumnInfo(name="homeTeam")
    private Integer homeTeam;

    @ColumnInfo(name="awayTeam")
    private Integer awayTeam;

    //Eşleşme dışında kalan 21.takım
    @ColumnInfo(name="passTeam")
    private Integer passTeam;

    @PrimaryKey(autoGenerate = true)
    private Integer uuid = 0;


    public final static Creator<Fixture> CREATOR = new Creator<Fixture>() {

        @SuppressWarnings({
                "unchecked"
        })
        public Fixture createFromParcel(android.os.Parcel in) {
            return new Fixture(in);
        }

        public Fixture[] newArray(int size) {
            return (new Fixture[size]);
        }

    };

    protected Fixture(android.os.Parcel in) {
        this.count = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.homeTeam = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.awayTeam = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.passTeam = ((Integer) in.readValue((Integer.class.getClassLoader())));
        this.uuid = ((Integer) in.readValue((Integer.class.getClassLoader())));
    }


    public Fixture(Integer count, Integer homeTeam, Integer awayTeam, Integer passTeam, Integer uuid) {
        super();
        this.count = count;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.passTeam = passTeam;
        this.uuid = uuid;
    }

    public Fixture() {

    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Integer homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Integer getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Integer awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Integer getPassTeam() {
        return passTeam;
    }

    public void setPassTeam(Integer passTeam) {
        this.passTeam = passTeam;
    }

    public Integer getUuid() {
        return uuid;
    }

    public void setUuid(Integer uuid) {
        this.uuid = uuid;
    }

    public void writeToParcel(android.os.Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(homeTeam);
        dest.writeValue(awayTeam);
        dest.writeValue(passTeam);
        dest.writeValue(uuid);
    }

    public int describeContents() {
        return 0;
    }



}