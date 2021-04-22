package com.akkayameva.soccerLeauge.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.adapter.TeamAdapter;
import com.akkayameva.soccerLeauge.databinding.FragmentMainBinding;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.view.BaseActivity;
import com.akkayameva.soccerLeauge.view.fragments.MainFragmentDirections;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.jvm.internal.Intrinsics;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private SoccerViewModel mViewModel;
    private TeamAdapter teamsAdapter;
    private List<Team> teamList;

    public MainFragment() {
        super(R.layout.fragment_main);
    }

    public static final  List teamsList (MainFragment mainFragment) {
        List<Team> list = mainFragment.teamList;
        return list;
    }

    public static final TeamAdapter teamAdapter (MainFragment mainFragment) {
        TeamAdapter teamAdapter = mainFragment.teamsAdapter;
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
            TeamAdapter teamAdapter = this.teamsAdapter;
            rw.setAdapter(teamAdapter);
            return;
        }



        }


    }

