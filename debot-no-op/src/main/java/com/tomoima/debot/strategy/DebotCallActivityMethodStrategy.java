package com.tomoima.debot.strategy;


import android.app.Activity;
import android.support.annotation.NonNull;


public class DebotCallActivityMethodStrategy extends DebotStrategy{

    final String methodName;

    /**
     * Constructor. A parameter should be same as a method name you want to call from Activity
     * @param methodName
     */
    public DebotCallActivityMethodStrategy(@NonNull String methodName){
        this.methodName = methodName;
    }

    @Override
    public void startAction(@NonNull Activity activity) {
    }
}
