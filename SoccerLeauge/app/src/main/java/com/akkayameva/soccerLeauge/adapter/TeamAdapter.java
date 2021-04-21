package com.akkayameva.soccerLeauge.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akkayameva.soccerLeauge.databinding.TeamItemCardBinding;
import com.akkayameva.soccerLeauge.model.Team;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class TeamAdapter  extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {



    public class differCallback extends DiffUtil.ItemCallback<Team>{

        @Override
        public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {

            return oldItem.getName().equals(newItem.getName());
        }
    }

    public AsyncListDiffer<differCallback> mDiff;



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TeamAdapter.ViewHolder(TeamItemCardBinding.inflate(
                LayoutInflater.from(parent.getContext()),parent,false));
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //holder.binding.tvTeamName.text = differ.currentList[position].team_name
        holder.binding.tvTeamName.
                setText((CharSequence) mDiff.getCurrentList().get(position));

    }

    @Override
    public int getItemCount() {
        return mDiff.getCurrentList().size();
    }


    //ViewHolder

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TeamItemCardBinding binding;

        public ViewHolder(TeamItemCardBinding binding) {
            super(binding.getRoot());
        }

        @NotNull
        public final TeamItemCardBinding getBinding() {
            return this.binding;
        }
    }




}
