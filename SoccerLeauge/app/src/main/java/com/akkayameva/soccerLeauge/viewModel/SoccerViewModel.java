package com.akkayameva.soccerLeauge.viewModel;

import android.app.Application;
import android.util.Log;

import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.model.repository.TeamRepository;
import com.akkayameva.soccerLeauge.util.NetworkResult;
import com.akkayameva.soccerLeauge.util.Util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Response;

import static com.akkayameva.soccerLeauge.util.NetworkResult.*;

//Live
public class SoccerViewModel extends ViewModel {

    private TeamRepository teamRepository;
    Application application;

    public SoccerViewModel(TeamRepository teamRepository, Application application) {
        this.teamRepository = teamRepository;
        this.application =application;
    }


    ArrayList<Fixture> fixtureList = new ArrayList<Fixture>();
    public MutableLiveData<NetworkResult<List<Team>>> teams = new MutableLiveData();


    public void getTeams(){

        Response<List<Team>> response = teamRepository.getTeams();
        teams.postValue(handleTeamsResponse(response));


    }

    private NetworkResult<List<Team>> handleTeamsResponse(Response<List<Team>> response) {

        if (response.isSuccessful()) {
            Collection body = response.body();
            if (!(body == null || body.isEmpty())) {
                List<Team> body2 = response.body();
                Log.d("Response","success");
            }
        } else if (response.code() == 402){
            Log.d("Response","code 402");
        } else if (response.body().isEmpty()){
            Log.d("Response","No Data");
        } else if (response.message().toString().contains("timeout")){
            Log.d("Response","Time Out");
        }
        return (NetworkResult<List<Team>>) response.body();

    }


    public void deleteAllFixture() {
        teamRepository.deleteAllFixture();
    }

    public LiveData<List<Fixture>> getSavedFixture() {

        return teamRepository.getSavedFixture();
    }

    public LiveData<List<Team>> getSavedTeams(){
        return teamRepository.getSavedTeams();
    }

    public void getRoundList(int count){
        teamRepository.getRoundList(count);
    }

    public  Boolean createFixture (int count){

        if (count % 2 == 0){
            fixtureList = Util.generateFixtureForDual(count);
        } else {
            fixtureList = Util.generateFixtureForSingle(count);
        }
        saveFixture(fixtureList);
        return true;
    }

    private void saveFixture(ArrayList<Fixture> fixtureList) {
        teamRepository.saveFixture(fixtureList);

    }




}