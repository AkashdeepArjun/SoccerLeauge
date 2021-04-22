package com.akkayameva.soccerLeauge.model.db;

import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TeamDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     void insert(Team... teams);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveFixture(ArrayList<Fixture> fixtures);


    @Query("Select *FROM TeamTable")
    LiveData<List<Team>> getAllTeams();

    @Query("Select *FROM FixtureTable")
    LiveData<List<Fixture>> getAllFixture();

    @Query("DELETE FROM  FixtureTable")
    void deleteAllFixture();

    @Query("SELECT *  FROM FixtureTable WHERE count LIKE:roundCount")
    LiveData<List<Fixture>> getRoundList(Integer roundCount);


}
