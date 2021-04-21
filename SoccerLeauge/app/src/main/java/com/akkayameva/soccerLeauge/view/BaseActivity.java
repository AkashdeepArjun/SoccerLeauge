package com.akkayameva.soccerLeauge.view;

import android.os.Bundle;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.databinding.ActivityBaseBinding;
import com.akkayameva.soccerLeauge.model.db.TeamDB;
import com.akkayameva.soccerLeauge.model.repository.TeamRepository;
import com.akkayameva.soccerLeauge.util.Util;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModelFactoryProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

public class BaseActivity extends AppCompatActivity {

    public SoccerViewModel soccerViewModel;
    ActivityBaseBinding binding;
    NavController controller;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Util.isNetworkAvailable(this)){
            setContentView(R.layout.activity_base);
        } else {
            Util.showInternetDialog(this);
        }



        TeamRepository teamRepository = new TeamRepository(TeamDB.createDatabase(this));
        SoccerViewModelFactoryProvider viewModelProviderFactory = new SoccerViewModelFactoryProvider(teamRepository, getApplication());


        soccerViewModel = new ViewModelProvider(this, viewModelProviderFactory).get(SoccerViewModel.class);
        soccerViewModel.deleteAllFixture();

    }



}