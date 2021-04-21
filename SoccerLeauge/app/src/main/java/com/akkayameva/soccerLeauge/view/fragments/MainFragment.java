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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

public class MainFragment extends Fragment {

    private FragmentMainBinding binding;
    private SoccerViewModel mViewModel;
    private TeamAdapter teamAdapter;
    private List<Team> teamList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fixture,container,false);

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

      teamAdapter = new TeamAdapter();
        BaseActivity main = new BaseActivity();
        mViewModel = main.soccerViewModel;
        binding = FragmentMainBinding.bind(view);
        binding.teamsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.teamsRecycler.setAdapter(teamAdapter);

        }


    }

