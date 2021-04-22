package com.akkayameva.soccerLeauge.model.db;

import android.content.Context;

import com.akkayameva.soccerLeauge.model.Fixture;
import com.akkayameva.soccerLeauge.model.Team;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import kotlin.jvm.internal.Intrinsics;

@Database(
        entities = {Team.class, Fixture.class},
        version = 2,
        exportSchema = false
)

public abstract class TeamDB extends RoomDatabase{

    public static final Object LOCK = new Object();
    public Executor databaseWriteExecutor;


    public abstract TeamDAO getTeamDao();

    private static volatile TeamDB INSTANCE;



    public static final class Companion {
        private Companion() {
        }


        public static final TeamDB invoke(Context context) {
            TeamDB it;

            TeamDB access = TeamDB.INSTANCE;
            if (access != null) {
                return access;
            }
            synchronized (TeamDB.LOCK) {
                it = TeamDB.INSTANCE;
                if (it == null) {
                    it = TeamDB.Companion.createDatabase(context);
                    TeamDB.INSTANCE = it;
                }
            }
            return it;
        }

        private static final TeamDB createDatabase(Context context) {
            TeamDB build = Room.databaseBuilder(context, TeamDB.class, "team_db.db").allowMainThreadQueries().build();
            return build;
        }
    }


}
