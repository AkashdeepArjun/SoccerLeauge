package com.akkayameva.soccerLeauge.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.view.fragments.FixtureListFragment;
import com.akkayameva.soccerLeauge.view.BaseActivity;

import java.util.ArrayList;


public class FixtureAdapter extends FragmentStatePagerAdapter {

    FragmentManager fm;
    ArrayList<Fixture> fixtureList;
    Team[] teamList;

    public FixtureAdapter(FragmentManager fm, ArrayList arrayList, Team[] teamList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.fm = fm;
        this.fixtureList=arrayList;
        this.teamList=teamList;
    }


    public void updateList(ArrayList<Fixture> newFixtureList){
        fixtureList.clear();
        fixtureList = newFixtureList;
        notifyDataSetChanged();
    }

    @Override
    public FixtureListFragment getItem(int position) {
       return new FixtureListFragment(position, teamList);
    }



    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position+ 1) + " . Hafta" ;
    }

    @Override
    public int getCount() {
        int count = 0;
        if ( !(fixtureList.isEmpty() )){
           count = (fixtureList.get(fixtureList.size() - 1).getCount());
           count = count*2 +2;

        } else {
            count = fixtureList.size();
        }
        return count;
    }




}
