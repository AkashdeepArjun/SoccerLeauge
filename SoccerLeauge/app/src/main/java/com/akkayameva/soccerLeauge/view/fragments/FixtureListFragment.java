package com.akkayameva.soccerLeauge.view.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.adapter.FixtureAdapter;
import com.akkayameva.soccerLeauge.adapter.FixtureListAdapter;
import com.akkayameva.soccerLeauge.databinding.FragmentFixtureBinding;
import com.akkayameva.soccerLeauge.databinding.FragmentFixtureListBinding;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.view.BaseActivity;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FixtureListFragment extends Fragment implements LifecycleObserver {
    FragmentFixtureListBinding binding;
    SoccerViewModel soccerViewModel;
    FixtureListAdapter adapter;

    int position;
    Team[] teamList;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_fixture_list, container, false);
    }

    public FixtureListFragment(int position, Team[] teamList) {
        this.position = position;
        this.teamList = teamList;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        init(view);
       // observerLiveData();
    }

    private final void init(View view) {
        FragmentActivity requireActivity = requireActivity();
        if (requireActivity != null) {
            soccerViewModel = ((BaseActivity) requireActivity).soccerViewModel;
            binding = FragmentFixtureListBinding.bind(view);
            adapter = new FixtureListAdapter(teamList, new ArrayList());

            RecyclerView recyclerView = binding.fixtureListRecycler;
            FixtureListAdapter fixtureListAdapter = this.adapter;
            recyclerView.setAdapter(fixtureListAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
            return;

    }
}

    private final void observerLiveData() {
        SoccerViewModel soccerViewModel = this.soccerViewModel;
        soccerViewModel.getRoundList(calcPosition(this.position));
    //observe(getViewLifecycleOwner(), new FixtureListFragment(this));

    }

    public int calcPosition (int position){
        if (teamList.length %2 == 0) {
            return position % ((teamList.length) - 1);
        } else {
            return position % (teamList.length);
        }

    }



}
