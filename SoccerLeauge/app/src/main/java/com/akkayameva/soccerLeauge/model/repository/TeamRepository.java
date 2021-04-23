package com.akkayameva.soccerLeauge.model.repository;

import com.akkayameva.soccerLeauge.model.db.TeamDB;
import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.model.service.TeamAPIService;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import retrofit2.Response;

public class TeamRepository {

    private TeamDB teamdb;



     public TeamRepository(TeamDB teamdb) {
        this.teamdb = teamdb;

    }

    public final TeamDB getDb() {
        return this.teamdb;
    }


     public Response<List<Team>> getTeams() {
         return TeamAPIService.api.getTeams();
     }


    public void insert(Team... teams) {
        teamdb.databaseWriteExecutor.execute(() -> {
            teamdb.getTeamDao().insert(teams);
        });

    }

    public void saveFixture(ArrayList<Fixture> fixtures) {
        teamdb.databaseWriteExecutor.execute(() -> {
            teamdb.getTeamDao().saveFixture(fixtures);
        });

    }


    public LiveData<List<Team>> getSavedTeams() {
        return teamdb.getTeamDao().getAllTeams();
    }



    public LiveData<List<Fixture>> getSavedFixture(){
         return teamdb.getTeamDao().getAllFixture();
    }

    public void deleteAllFixture(){
        teamdb.getTeamDao().deleteAllFixture();
    }

    public LiveData<List<Fixture>> getRoundList(Integer roundCount){
       return  teamdb.getTeamDao().getRoundList(roundCount);
    }



}

