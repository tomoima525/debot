package com.tomoima.debot;

import android.content.Context;

import com.tomoima.debot.strategy.DebotStrategy;

import java.util.ArrayList;

public class DebotStrategyBuilder {

    private final Context context;

    private final ArrayList<DebotStrategy> strategyList;

    public ArrayList<DebotStrategy> getStrategyList() {
        return strategyList;
    }

    DebotStrategyBuilder(Context context, ArrayList<DebotStrategy> strategyList){
        this.context = context;
        this.strategyList = strategyList;
    }

    public static class Builder {
        private Context context;
        private ArrayList<DebotStrategy> strategyList;

        public Builder(Context context) {
            this.context = context;
            strategyList = new ArrayList<>();
        }

        public Builder registerMenu(String strategyName, DebotStrategy strategy){
            strategy.setStrategyMenuName(strategyName);
            strategyList.add(strategy);
            return this;
        }

        public DebotStrategyBuilder build(){
            if(strategyList.isEmpty()){
                strategyList = new ArrayList<>();
            }

            return new DebotStrategyBuilder(context, strategyList);
        }
    }
}
