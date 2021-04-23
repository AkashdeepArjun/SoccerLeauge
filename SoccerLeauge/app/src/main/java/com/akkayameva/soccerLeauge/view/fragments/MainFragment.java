package com.akkayameva.soccerLeauge.view.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.adapter.TeamAdapter;
import com.akkayameva.soccerLeauge.databinding.FragmentMainBinding;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.util.NetworkResult;
import com.akkayameva.soccerLeauge.view.BaseActivity;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;


public class MainFragment extends Fragment  {

    private FragmentMainBinding binding;
    private SoccerViewModel mViewModel;
    private TeamAdapter teamsAdapter;
    private List<Team> teamList;
//     MainFragment mainFragment;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    public static final  List teamsList (MainFragment mainFragment) {
        List<Team> list = this.teamList;
        return list;
    }

    public static final TeamAdapter teamAdapter (MainFragment mainFragment) {
//         TeamAdapter teamAdapter = mainFragment.teamsAdapter;             YOUR CODE
        TeamAdapter teamAdapter = this.teamsAdapter;            
        
        return teamAdapter;
    }

    public final SoccerViewModel getMViewModel() {
        SoccerViewModel soccerViewModel = this.mViewModel;
        return soccerViewModel;
    }

    public final void setMViewModel(SoccerViewModel soccerViewModel) {
        this.mViewModel = soccerViewModel;
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        eventHandler();
        observeLiveData();
    }

    private final void observeLiveData() {
        NetworkResult<List<Team>> response = null;
        SoccerViewModel soccerViewModel = this.mViewModel;
        soccerViewModel.teams.observe(getViewLifecycleOwner(), new Observer<NetworkResult<List<Team>>>() {
            @Override
            public void onChanged(NetworkResult<List<Team>> response) {
                if (response instanceof NetworkResult.Success) {
                    //showButton();
                    //hideProgressBar();
                    NetworkResult.Success success = (NetworkResult.Success) response;
                    MainFragment.teamAdapter(mainFragment).getDiffer().submitList((List<Team>) response);
                } else if (response instanceof NetworkResult.Failure) {
                    //hideProgressBar();
                    Log.e("","fail");
                } else if (response instanceof NetworkResult.Loading) {
                   // showProgressBar();
                   // hideButton();
                    Log.e("","fail");
                }

            }
        });


        soccerViewModel.getSavedTeams().observe(getViewLifecycleOwner(), new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                teamList = teams;

            }
        });


    }





    private void eventHandler() {


        binding.btnDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mViewModel.createFixture(teamList.size())) {

                  MainFragmentDirections.ActionMainFragmentToFixtureFragment action;
                  action = MainFragmentDirections.actionMainFragmentToFixtureFragment((Team[]) teamList.toArray());

                  Navigation.findNavController(getView()).navigate(action);
                } else {
                    Toast.makeText(getContext(), "Bir Problem Oluştu", Toast.LENGTH_SHORT).show();
                }
                     Toast.makeText(getContext(), "Takım Listesine Erişilemiyor", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void init(View view) {


        FragmentActivity activity = getActivity();
        if (activity != null) {
            this.mViewModel = ((BaseActivity) activity).getMViewModel();
            FragmentMainBinding bind = FragmentMainBinding.bind(view);
            this.binding = bind;
            RecyclerView rw = bind.teamsRecycler;
            rw.setLayoutManager(new LinearLayoutManager(rw.getContext()));
            TeamAdapter teamAdapter=new TeamAdapter()
            rw.setAdapter(teamAdapter);
            return;
        }



        }


    }

