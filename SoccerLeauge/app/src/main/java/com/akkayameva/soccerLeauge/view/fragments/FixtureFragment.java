package com.akkayameva.soccerLeauge.view.fragments;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akkayameva.soccerLeauge.R;
import com.akkayameva.soccerLeauge.adapter.FixtureAdapter;
import com.akkayameva.soccerLeauge.databinding.FragmentFixtureBinding;
import com.akkayameva.soccerLeauge.view.BaseActivity;
import com.akkayameva.soccerLeauge.view.fragments.FixtureFragmentArgs;
import com.akkayameva.soccerLeauge.view.fragments.FixtureFragment;
import com.akkayameva.soccerLeauge.viewModel.SoccerViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class FixtureFragment extends Fragment implements LifecycleObserver {

     SoccerViewModel mViewModel;
     FragmentFixtureBinding binding;
     FixtureAdapter adapter;
     FixtureFragmentArgs nav;

    public static FixtureFragment newInstance() {
        return new FixtureFragment();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SoccerViewModel.class);

    }



    private void init(View view){

        binding= FragmentFixtureBinding.bind(view);
        FragmentActivity requireActivity = requireActivity();
            mViewModel = ((BaseActivity) requireActivity).soccerViewModel;
            FragmentActivity activity = getActivity();
            FragmentManager supportFragmentManager = activity != null ? activity.getSupportFragmentManager() : null;
            adapter = new FixtureAdapter(supportFragmentManager, new ArrayList(), nav.getTeamList());

            ViewPager viewPager = binding.fixtureViewPager;
            FixtureAdapter fixtureAdapter = this.adapter;
            viewPager.setAdapter(fixtureAdapter);

            TabLayout tabLayout = binding.fixtureTabLayout;
            tabLayout.setupWithViewPager(binding.fixtureViewPager, true);
            return;


    }

    public void onDestroy(){
        super.onDestroy();;
        mViewModel.deleteAllFixture();
    }

    public void onDestroyView(){
        super.onDestroyView();
        mViewModel.deleteAllFixture();
    }




}