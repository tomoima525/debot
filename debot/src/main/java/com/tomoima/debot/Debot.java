package com.tomoima.debot;


import android.content.Context;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;

import com.squareup.seismic.ShakeDetector;

import java.lang.ref.WeakReference;

public class Debot {
    private DebotDialog debotDialog;
    private ShakeDetector sd;
    private SensorManager sensorManager;
    private WeakReference<FragmentActivity> activityWeakRef;
    private boolean canShake;

    private static Debot instance = new Debot();

    private Debot(){
        debotDialog = DebotDialog.getInstance();
    }

    public static Debot getInstance() {
        return instance;
    }

    public void allowShake(Context context) {
        canShake = true;
        setupSensor(context);
    }

    public void startSensor(FragmentActivity activity) {
        if(!canShake) return;
        activityWeakRef = new WeakReference<>(activity);
        if(sd != null) {
            sd.start(sensorManager);
        }
    }

    public void stopSensor() {
        if (!canShake) return;
        if (activityWeakRef != null) {
            activityWeakRef.clear();
        }
        if (sd != null) {
            sd.stop();
        }
    }

    public void showDebugMenu(FragmentActivity activity) {
        debotDialog.showDebugMenu(activity);
    }

    private void setupSensor(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        sd = new ShakeDetector(new ShakeDetector.Listener() {
            @Override
            public void hearShake() {
                if(activityWeakRef.get() != null) {
                    showDebugMenu(activityWeakRef.get());
                }
            }
        });
        sd.setSensitivity(ShakeDetector.SENSITIVITY_LIGHT);
    }

}
