package com.tomoima.debot.strategy;


import android.app.Activity;
import android.support.annotation.NonNull;

public abstract class DebotStrategy {

    private String strategyMenuName;

    public String getStrategyMenuName() {
        return strategyMenuName;
    }

    public void setStrategyMenuName(String strategyMenuName) {
        this.strategyMenuName = strategyMenuName;
    }

    public abstract void startAction(@NonNull Activity activity);
}
