package com.akkayameva.soccerLeauge.model.service;
import com.akkayameva.soccerLeauge.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;


public interface TeamAPI {

    @GET("/akkayameva/SoccerLeauge/main/DataSets/soccer_teams.json")
    Response<List<Team>> getTeams();


}
