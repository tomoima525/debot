package com.tomoima.debot;

import com.tomoima.debot.strategy.DebotStrategy;

import java.util.ArrayList;

public class DebotStrategies {
    private static ArrayList<DebotStrategy> strategies;

    public void initialize(ArrayList<DebotStrategy> strategies){
        this.strategies = strategies;
    }

    public ArrayList<DebotStrategy> getStrategies(){
        return strategies;
    }
}
