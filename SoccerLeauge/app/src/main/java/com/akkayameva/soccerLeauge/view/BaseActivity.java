package com.akkayameva.soccerLeauge.view;

import android.app.Application;
import android.os.Bundle;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.databinding.ActivityBaseBinding;
import com.akkayameva.soccerLeauge.model.db.TeamDB;
import com.akkayameva.soccerLeauge.model.repository.TeamRepository;
import com.akkayameva.soccerLeauge.util.Util;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModelFactoryProvider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.NavController;
import kotlin.jvm.internal.Intrinsics;

public class BaseActivity extends AppCompatActivity {

    public SoccerViewModel soccerViewModel;
    ActivityBaseBinding binding;
    NavController controller;

    public final SoccerViewModel getSoccerViewModel() {
        SoccerViewModel soccerViewModel = this.soccerViewModel;
        return soccerViewModel;
    }

    public final void setSoccerViewModel(SoccerViewModel soccerViewModel) {
        this.soccerViewModel = soccerViewModel;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_SoccerLeauge );
        if (Util.isNetworkAvailable(this)){
            setContentView(R.layout.activity_base);
        } else {
            Util.showInternetDialog(this);
        }


        TeamRepository teamRepository = new TeamRepository(TeamDB.createDatabase(this));
        Application application = getApplication();
        ViewModel viewModel = new ViewModelProvider((ViewModelStoreOwner) this, (ViewModelProvider.Factory) new SoccerViewModelFactoryProvider(teamRepository, getApplication())).get(SoccerViewModel.class);

        SoccerViewModel soccerViewModel = (SoccerViewModel) viewModel;
        this.soccerViewModel = soccerViewModel;
        soccerViewModel.deleteAllFixture();
    }

    }

