package com.tomoima.debot;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.tomoima.debot.strategy.DebotStrategy;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Debot extends Fragment {
    private static final String TAG = "com.tomoima.debot.Debot";
    private static final String STRATEGIES = "strategies";
    private ArrayList<DebotStrategy> debotStrategyList;
    private final static int GROUP_ID = Integer.MAX_VALUE;
    private static WeakReference<Activity> weakRefActivity;

    public Debot(){
        //Do nothing
    }

    public static Debot getInstance(Activity activity){
        FragmentManager fm = activity.getFragmentManager();
        Debot debot = (Debot)fm.findFragmentByTag(TAG);
        if(debot == null){
            debot = new Debot();
            Bundle bundle = new Bundle();
            bundle.putSerializable(STRATEGIES, new DebotStrategies().getStrategies());
            debot.setArguments(bundle);
            fm.beginTransaction().add(debot,TAG).commit();
        }
        return debot;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        debotStrategyList = (ArrayList<DebotStrategy>) getArguments().getSerializable(STRATEGIES);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int i = 0;
        for(DebotStrategy strategy: debotStrategyList){
            menu.add(GROUP_ID, i, 0, strategy.getStrategyMenuName());
            i++;
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getGroupId() == GROUP_ID) {
            debotStrategyList.get(item.getItemId()).startAction(getActivity());
            return true;
        }
        return super.onOptionsItemSelected(item);
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
