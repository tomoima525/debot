package com.tomoima.debot.strategy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.tomoima.debot.util.DialogUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class ShowActivityInfoStrategy extends DebotStrategy {
    @Override
    public void startAction(@NonNull Activity activity) {
        DialogUtil.showDialog(activity, activity.getClass().getSimpleName(), getJSONObject(activity.getIntent()).toString());
    }


    public static JSONObject getJSONObject(Intent intent) {
        JSONObject data = new JSONObject();
        Bundle bundle = intent.getExtras();
        if (bundle == null) return data;

        for (String key : bundle.keySet()) {
            Object value = bundle.get(key);
            if (value instanceof String) {
                JSONObject json = null;
                JSONArray jsonArray = null;
                String str = String.valueOf(value);
                try {
                    jsonArray = new JSONArray(str);
                    if (jsonArray == null) {
                        json = new JSONObject(str);
                        put(data, key, json);
                    } else {
                        put(data, key, jsonArray);
                    }
                } catch (JSONException e1) {
                    put(data, key, str);
                }
            } else {
                put(data, key, value);
            }
        }
        return data;
    }

    private static void put(JSONObject obj, String key, Object value) {
        try {
            obj.put(key, value);
        } catch (JSONException e) {
            throw new RuntimeException();
        }
    }

}
