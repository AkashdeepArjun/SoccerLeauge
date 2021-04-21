package com.akkayameva.soccerLeauge.viewModel;

import android.app.Application;

import com.akkayameva.soccerLeauge.model.repository.TeamRepository;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class SoccerViewModelFactoryProvider implements ViewModelProvider.Factory {

    TeamRepository teamRepository;
    Application application;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new SoccerViewModel(teamRepository, application);
    }

    public SoccerViewModelFactoryProvider(TeamRepository teamRepository, Application application) {
         this.teamRepository = teamRepository;
        this.application = application;
    }



}
