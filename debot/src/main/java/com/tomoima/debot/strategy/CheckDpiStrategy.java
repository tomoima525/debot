package com.tomoima.debot.strategy;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.tomoima.debot.util.DialogUtil;

public class CheckDpiStrategy extends DebotStrategy {
    private static final String TITLE = "Density";
    enum Density{
        MDPI(1.0f),
        HDPI(1.5f),
        XHDPI(2.0f),
        XXHDPI(3.0f),
        TVDPI(213f / 160f);

        private final float value;
        Density(final float value) {
            this.value = value;
        }

        public float getValue() {
            return value;
        }
    }
    @Override
    public void startAction(@NonNull Activity activity) {

        if (activity == null) return;
        float density = activity.getResources().getDisplayMetrics().density;
        String message;
        if (density == Density.MDPI.getValue()) {
            message = Density.MDPI.name() +": " + density;
        } else if (density == Density.HDPI.getValue()) {
            message = Density.HDPI.name() +": " + density;
        } else if (density == Density.XHDPI.getValue()) {
            message = Density.XHDPI.name() +": " + density;
        } else if (density == Density.XXHDPI.getValue()) {
            message = Density.XXHDPI.name() +": " + density;
        } else if (density == Density.TVDPI.getValue()) {
            message = Density.TVDPI.name() +": " + density;
        } else {
            message = String.valueOf(density);
        }

        DialogUtil.showDialog(activity, TITLE, message);

    }
}
