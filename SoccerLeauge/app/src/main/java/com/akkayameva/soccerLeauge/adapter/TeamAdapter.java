package com.akkayameva.soccerLeauge.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.akkayameva.soccerLeauge.databinding.TeamItemCardBinding;
import com.akkayameva.soccerLeauge.model.Team;

import org.jetbrains.annotations.NotNull;

import androidx.annotation.NonNull;
import kotlin.jvm.internal.Intrinsics;

public class TeamAdapter  extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    public AsyncListDiffer<Team> mDiff;
    public differCallback differCallback;


    public TeamAdapter() {
        differCallback teamAdapterDifferCallback = new differCallback();
        this.differCallback = teamAdapterDifferCallback;
        this.mDiff = new AsyncListDiffer((RecyclerView.Adapter) this, teamAdapterDifferCallback);

    }


    public class differCallback extends DiffUtil.ItemCallback<Team>{

        void differCallback() {
        }

        public boolean areItemsTheSame(Team oldItem, Team newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return oldItem.getId() == newItem.getId();
        }

        public boolean areContentsTheSame(Team oldItem, Team newItem) {
            Intrinsics.checkNotNullParameter(oldItem, "oldItem");
            Intrinsics.checkNotNullParameter(newItem, "newItem");
            return Intrinsics.areEqual((Object) oldItem, (Object) newItem);
        }
    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TeamItemCardBinding inflate = TeamItemCardBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(this, inflate);
    }




    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TextView textView = holder.getBinding().tvTeamName;
        textView.setText(this.mDiff.getCurrentList().get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mDiff.getCurrentList().size();
    }


    //ViewHolder

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TeamItemCardBinding binding;
        final TeamAdapter teamAdapter;


        public ViewHolder(TeamAdapter teamAdapter, TeamItemCardBinding binding) {
            super(binding.getRoot());
            this.teamAdapter = teamAdapter;
            this.binding = binding;
        }



        @NotNull
        public final TeamItemCardBinding getBinding() {
            return this.binding;
        }
    }

    public final AsyncListDiffer<Team> getDiffer() {
        return this.mDiff;
    }




}
