package com.tomoima.debot;

import android.support.v7.app.AppCompatActivity;

public class Debot {

    private Debot(){
        //Do nothing
    }

    public static Debot getInstance(){
        return new Debot();
    }


    public void showDebugMenu(AppCompatActivity activity) {

    }

    public void dismissDebugMenu() {

    }

}
