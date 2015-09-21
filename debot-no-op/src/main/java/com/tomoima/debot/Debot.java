package com.tomoima.debot;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;

import java.util.ArrayList;

public class Debot {

    private Debot(){
        //Do nothing
    }

    public static void initialize(ArrayList<DebotStrategy> strategies){

    }

    public static void onResume(Activity activity){

    }

    public static void onPause(Activity activity) {

    }

    public static void onCreateOptionsMenu(Menu menu){

    }

    public static boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

    public static void setVisibility(Menu menu, boolean isVisible){
    }
}
