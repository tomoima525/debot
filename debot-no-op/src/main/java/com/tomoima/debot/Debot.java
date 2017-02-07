package com.tomoima.debot;

import android.content.Context;
import android.support.v4.app.FragmentActivity;

public class Debot {

    private Debot(){
        //Do nothing
    }

    public static Debot getInstance(){
        return new Debot();
    }


    public void allowShake(Context context) {
    }

    public void startSensor(FragmentActivity activity) {
    }

    public void stopSensor() {
    }

    public void showDebugMenu(FragmentActivity activity) {
    }

}
