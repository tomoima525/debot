package com.tomoima.debot;


import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Debot {
    private final static int GROUP_ID = Integer.MAX_VALUE;
    private static ArrayList<DebotStrategy> debugStrategies;
    private static WeakReference<Activity> weakRefActivity;

    private Debot(){
        //Do nothing
    }

    public static void initialize(ArrayList<DebotStrategy> strategies){
        debugStrategies = strategies;
    }

    public static void onResume(Activity activity){
        weakRefActivity = new WeakReference<>(activity);
    }

    public static void onPause(Activity activity) {
        Activity refActivity = weakRefActivity.get();
        if(refActivity != null && refActivity == activity) {
            weakRefActivity = null;
        } else {
            throw new IllegalStateException("Can't find activity. Check if Mace.onResume() is called at onResume()");
        }
    }

    public static void onCreateOptionsMenu(Menu menu){
        int i = 0;
        for(DebotStrategy strategy: debugStrategies){
            menu.add(GROUP_ID, i, 0, strategy.getStrategyMenuName());
            i++;
        }
    }

    public static boolean onOptionsItemSelected(MenuItem item) {
        Activity activity = weakRefActivity.get();
        if(activity == null) {
            throw new IllegalStateException("Activity is not set");
        }

        if(item.getGroupId() == GROUP_ID) {
            debugStrategies.get(item.getItemId()).startAction(activity);
            return true;
        }

        return false;
    }

    public static void setVisibility(Menu menu, boolean isVisible){
        for(int i = 0, length = menu.size(); i < length; i++ ){
            MenuItem menuItem = menu.getItem(i);
            if(menuItem.getGroupId() == GROUP_ID){
                menuItem.setVisible(isVisible);
            }
        }
    }
}
