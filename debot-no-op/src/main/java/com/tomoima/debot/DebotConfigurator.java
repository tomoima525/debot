package com.tomoima.debot;

import android.content.Context;

import com.tomoima.debot.strategy.DebotStrategy;

import java.util.ArrayList;

public class DebotConfigurator {

    public static void configureWithDefault(Context context) {

    }

    public static void configureWithCustomizedMenu(Context context, ArrayList<DebotStrategy> customList){

    }

    private static ArrayList<DebotStrategy> createDefaultMenuConfig(Context context){
        return new ArrayList<DebotStrategy>();
    }
}
