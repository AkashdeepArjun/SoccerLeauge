package com.akkayameva.soccerLeauge.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;
import com.akkayameva.soccerLeauge.databinding.FixtureListItemRowBinding;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

public class FixtureListAdapter extends RecyclerView.Adapter<FixtureListAdapter.ViewHolder>{


    private ArrayList<Fixture> fixtureList;
    private int halfCount = 1;
    private Team[] teamList;

    public FixtureListAdapter(Team[] teamList, ArrayList<Fixture> fixtureList) {
        this.teamList=teamList;
        this.fixtureList = fixtureList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private  FixtureListItemRowBinding binding;

        public ViewHolder(FixtureListItemRowBinding binding) {
            super(binding.getRoot());
        }

        @NotNull
        public final FixtureListItemRowBinding getBinding() {
            return this.binding;
        }

        public final void setBinding(FixtureListItemRowBinding fixtureListItemRowBinding) {
            this.binding = fixtureListItemRowBinding;
        }
    }




    @NonNull
    @Override
    public FixtureListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(FixtureListItemRowBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false));
    }

    public void updateList (List<Fixture> newFixtureList, int position){
        if (teamList.length % 2 == 0) {
            halfCount = (position < teamList.length - 1) ? 2 : 1;
        }else {
            halfCount =( position < teamList.length) ? 2 : 1;
        }
        fixtureList.clear();
        fixtureList.addAll(newFixtureList);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull FixtureListAdapter.ViewHolder holder, int position) {
        TextView tv_home, tv_away;
        Integer home, away;
        if (!fixtureList.isEmpty()){
            if (halfCount == 1){
                tv_home = holder.getBinding().tvTeamHome;
                Team[] teamArr = this.teamList;
                home = fixtureList.get(position).getHomeTeam();
                tv_home.setText(teamArr[home.intValue()].getName());

                tv_away = holder.getBinding().tvTeamAway;
                away = fixtureList.get(position).getAwayTeam();
                tv_away.setText(teamArr[away.intValue()].getName());

            } else {
                tv_home = holder.getBinding().tvTeamAway;
                Team[] teamArr = this.teamList;
                home = fixtureList.get(position).getHomeTeam();
                tv_home.setText(teamArr[home.intValue()].getName());

                tv_away = holder.getBinding().tvTeamHome;
                away = fixtureList.get(position).getAwayTeam();
                tv_away.setText(teamArr[away.intValue()].getName());


            }
        }
    }

    @Override
    public int getItemCount() {
        return fixtureList.size();
    }





}
