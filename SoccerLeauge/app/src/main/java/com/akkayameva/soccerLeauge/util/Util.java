package com.akkayameva.soccerLeauge.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

import com.akkayameva.soccerLeauge.model.Fixture;

import java.util.ArrayList;
import java.util.Collections;

public class Util{


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public static void showInternetDialog(Context context){
        AlertDialog.Builder alertdialog=new AlertDialog.Builder(context);
        alertdialog.setMessage("Internet bağlantınız yok");
        alertdialog.setCancelable(false);
        alertdialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(Settings.ACTION_SETTINGS));
                ((Activity) context).finish();
            }
        });


        alertdialog.setNegativeButton("Kapat", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ((Activity) context).finish();
            }
        });

        alertdialog.create();
        alertdialog.show();

    }

    public static ArrayList<Fixture> generateFixtureForDual(int TeamCount){

         ArrayList<Fixture> fixtureList = null;

        int round = TeamCount-1;
        int matchCount= TeamCount/2;

        ArrayList<Integer> list = new ArrayList<Integer>();
        //Takım listesi oluşturuluyor

        for (int i = 0; i < TeamCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        int temp = TeamCount - 1;

        for (int i = 0; i < round; i++) {
            for (int j = 0; j < matchCount; j++) {
                int firsti = j;
                int secondi = (TeamCount - 1) - j;

                Fixture fixtureInstance = new Fixture();
                fixtureInstance.setHomeTeam(list.get(firsti));
                fixtureInstance.setAwayTeam(list.get(secondi));
                round = i;
                fixtureList.add(fixtureInstance);
            }

            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(list.get(0));
            newList.add(list.get(list.size() - 1));

            for (int k = 0; k < (list.size() - 1); k++) {
                newList.add(list.get(k));
            }
            list = newList;

        }



        return fixtureList;


    }


    public static ArrayList<Fixture> generateFixtureForSingle(int TeamCount){

        ArrayList<Fixture> fixtureList = null;

        ArrayList<Integer> passList = null;
        int teamCount= TeamCount + 1;
        int roundCount = TeamCount -1;
        int matchCount= TeamCount/2;



        ArrayList<Integer> list = new ArrayList<Integer>();
        //Takım listesi oluşturuluyor

        for (int i = 0; i < TeamCount; i++) {
            list.add(i);
        }
        Collections.shuffle(list);


        int temp = TeamCount - 1;


        for (int i = 0; i < roundCount; i++) {
            for (int j = 0; j < matchCount; j++) {
                int firsti = j;
                int secondi = (TeamCount - 1) - j;


                if (temp == list.get(firsti)){
                    passList.add(list.get(secondi));
                    continue;
                }

                if (temp == list.get(secondi)) {
                    passList.add(list.get(firsti));
                    continue;
                }

                Fixture fixtureInstance = new Fixture();
                fixtureInstance.setHomeTeam(list.get(firsti));
                fixtureInstance.setAwayTeam(list.get(secondi));
                roundCount = i;
                fixtureList.add(fixtureInstance);
            }


            ArrayList<Integer> newList = new ArrayList<Integer>();
            newList.add(list.get(0));
            newList.add(list.get(list.size() - 1));



            for (int k = 0; k < (list.size() - 1); k++) {
                newList.get(list.get(k));
            }
            list = newList;

        }

        for (Fixture f : fixtureList){

            f.setPassTeam(passList.get(roundCount));
        }



        return fixtureList;


    }

}

